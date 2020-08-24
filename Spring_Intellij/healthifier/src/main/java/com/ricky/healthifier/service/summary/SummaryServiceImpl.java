package com.ricky.healthifier.service.summary;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.ricky.healthifier.datamodel.food.Food;
import com.ricky.healthifier.datamodel.summary.Summary;
import com.ricky.healthifier.datamodel.tracker.FoodTracker;
import com.ricky.healthifier.datamodel.tracker.WorkoutTracker;
import com.ricky.healthifier.datamodel.workout.Workout;
import com.ricky.healthifier.service.auth.JwtService;
import com.ricky.healthifier.service.food.FoodService;
import com.ricky.healthifier.service.tracker.FoodTrackerService;
import com.ricky.healthifier.service.tracker.WorkoutTrackerService;
import com.ricky.healthifier.service.user.UserService;
import com.ricky.healthifier.service.workout.WorkoutService;
import com.ricky.healthifier.utils.commons.BaseValidator;
import com.ricky.healthifier.utils.exception.AppException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;
import java.util.List;
import java.util.stream.Stream;

@Service
public class SummaryServiceImpl implements SummaryService {

    @Autowired
    private FoodService foodService;

    @Autowired
    private WorkoutService workoutService;

    @Autowired
    private FoodTrackerService foodTrackerService;

    @Autowired
    private WorkoutTrackerService workoutTrackerService;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserService userService;

    private final Logger logger = LoggerFactory.getLogger(SummaryServiceImpl.class);
    private static final double REFERENCE_WEIGHT = 70.0;

    @Override
    public List<Summary> getSummary(String token) throws AppException {

        logger.info("Service: Start fetching summary list");
        List<Summary> summaryList = new ArrayList<>();

        // Calculate email from token
        String email = jwtService.extractEmail(token);
        BaseValidator.checkObjectIsNotNull(email, "Token Invalid");

        // Get the tracker information for logged user
        List<FoodTracker> foodTrackerList = foodTrackerService.getFoodTracker(token);
        List<WorkoutTracker> workoutTrackerList = workoutTrackerService.getWorkoutTracker(token);

        // Group the tracker lists by date
        Map<Date, List<FoodTracker>> groupFoodTrackerByDateMap = new HashMap<>();
        for (FoodTracker foodTracker : foodTrackerList) {
            Date date = foodTracker.getDate();
            if (!groupFoodTrackerByDateMap.containsKey(date)) {
                groupFoodTrackerByDateMap.put(date, new ArrayList<>());
            }
            groupFoodTrackerByDateMap.get(date).add(foodTracker);
        }

        Map<Date, List<WorkoutTracker>> groupWorkoutTrackerByDateMap = new HashMap<>();
        for (WorkoutTracker workoutTracker : workoutTrackerList) {
            Date date = workoutTracker.getDate();
            if (!groupWorkoutTrackerByDateMap.containsKey(date)) {
                groupWorkoutTrackerByDateMap.put(date, new ArrayList<>());
            }
            groupWorkoutTrackerByDateMap.get(date).add(workoutTracker);
        }

        // Calculate calories gained on date basis
        for (Map.Entry<Date, List<FoodTracker>> listEntry : groupFoodTrackerByDateMap.entrySet()) {

            double caloriesGained = 0.0;

            // For each of the food items consumed on the particular date, calculate the calories
            for (FoodTracker foodTracker : listEntry.getValue()) {
                String foodName = foodTracker.getFoodName();
                double amount = foodTracker.getAmount();
                Food food = foodService.getFoodByName(foodName);
                BaseValidator.checkObjectIsNotNull(food, "Food not found in Database");
                caloriesGained += (food.getCalories() / food.getQty()) * amount;
            }

            Summary summary = new Summary();
            summary.setDate(LocalDate.ofInstant(listEntry.getKey().toInstant(), ZoneId.systemDefault()));
            summary.setCalories(caloriesGained);
            summaryList.add(summary);
        }

        // Calculate calories burned on date basis
        for (Map.Entry<Date, List<WorkoutTracker>> listEntry : groupWorkoutTrackerByDateMap.entrySet()) {

            double caloriesBurned = 0.0;

            // For each workout done on the particular date, calculate the calories
            for (WorkoutTracker workoutTracker : listEntry.getValue()) {
                String workoutName = workoutTracker.getWorkoutName();
                double durationInMins = workoutTracker.getDuration();
                double weight = userService.getWeightByEmail(email);
                Workout workout = workoutService.getWorkoutByName(workoutName);
                caloriesBurned -= (weight / REFERENCE_WEIGHT) * (workout.getCaloriesBurntPerHour() / 60.0) * durationInMins;
            }

            LocalDate date = LocalDate.ofInstant(listEntry.getKey().toInstant(), ZoneId.systemDefault());
            // Search if already a object with this date exist in list
            Optional<Summary> optionalSummary = summaryList.stream().filter(list -> list.getDate().equals(date)).findFirst();
            if (optionalSummary.isPresent()) {
                Summary existingSummary = optionalSummary.get();
                if (!summaryList.remove(existingSummary))
                    throw new AppException("Some error in calculation");
                Summary summary = new Summary();
                summary.setDate(date);
                summary.setCalories(existingSummary.getCalories() + caloriesBurned);
                summaryList.add(summary);
            } else {
                Summary summary = new Summary();
                summary.setDate(date);
                summary.setCalories(caloriesBurned);
                summaryList.add(summary);
            }
        }

        // Sort the list by Date
        Collections.sort(summaryList, new SummarySorter().reversed());

        logger.info("Service: Successfully fetched summary list");
        return summaryList;
    }

    @Override
    public boolean  generateReport(String token) throws AppException, IOException, DocumentException {
        logger.info("Service: Start report generation for logged user");

        // Check if Report folder exists else create it
        Path reportPath = Paths.get("D:\\CodeRepo\\PROJECTS\\GitHubProjects\\HealthifierApp\\Spring_Intellij\\healthifier\\src\\main\\resources\\report");

        DirectoryStream<Path> reportDirectoryStream = Files.newDirectoryStream(reportPath);
        // Delete any existing file in this Directory
        for (Path path : reportDirectoryStream) {
            Files.deleteIfExists(path);
        }

        List<Summary> summaryList = getSummary(token);
        if (summaryList.isEmpty())
            throw new AppException("Report cannot br generated due to lack of information");

        Document document = new Document();
        File file = new File("D:\\CodeRepo\\PROJECTS\\GitHubProjects\\HealthifierApp\\Spring_Intellij\\healthifier\\src\\main\\resources\\report\\Report.pdf");
        PdfWriter.getInstance(document, new FileOutputStream(file));
        document.open();

        PdfPTable table = new PdfPTable(2);
        addTableHeader(table);
        addRows(table, summaryList);

        document.add(table);
        document.close();

        logger.info("Service: Successfully generated report");
        return true;
    }

    private void addRows(PdfPTable table, List<Summary> summaryList) {
        for (Summary summary : summaryList) {
            PdfPCell row = new PdfPCell();
            if(summary.getCalories() >= 0)
                row.setBackgroundColor(BaseColor.GREEN);
            else
                row.setBackgroundColor(BaseColor.RED);
            row.setPhrase(new Phrase(summary.getDate().toString()));
            table.addCell(row);
            row.setPhrase(new Phrase(Math.abs(summary.getCalories()) + ""));
            table.addCell(row);
        }
    }

    private void addTableHeader(PdfPTable table) {
        Stream.of("Date", "Calories gained/burned")
                .forEach(columnTitle -> {
                    PdfPCell header = new PdfPCell();
                    header.setBackgroundColor(BaseColor.LIGHT_GRAY);
                    header.setBorderWidth(2);
                    header.setPhrase(new Phrase(columnTitle));
                    table.addCell(header);
                });
    }
}
