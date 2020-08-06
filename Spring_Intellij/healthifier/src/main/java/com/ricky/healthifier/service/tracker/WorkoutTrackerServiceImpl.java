package com.ricky.healthifier.service.tracker;

import com.ricky.healthifier.dao.UserDAO;
import com.ricky.healthifier.dao.WorkoutDAO;
import com.ricky.healthifier.dao.WorkoutTrackerDAO;
import com.ricky.healthifier.datamodel.tracker.WorkoutTracker;
import com.ricky.healthifier.entity.tracker.WorkoutTrackerDTO;
import com.ricky.healthifier.entity.tracker.WorkoutTrackerDTOTransformer;
import com.ricky.healthifier.entity.user.UserDTO;
import com.ricky.healthifier.entity.workout.WorkoutDTO;
import com.ricky.healthifier.service.auth.JwtService;
import com.ricky.healthifier.utils.commons.BaseValidator;
import com.ricky.healthifier.utils.exception.AppException;
import com.ricky.healthifier.utils.exception.ExceptionLevel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class WorkoutTrackerServiceImpl implements WorkoutTrackerService {

    @Autowired
    private WorkoutTrackerDAO workoutTrackerDAO;

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private WorkoutDAO workoutDAO;

    @Autowired
    private JwtService jwtService;

    private final Logger logger = LoggerFactory.getLogger(WorkoutTrackerServiceImpl.class);
    private final WorkoutTrackerDTOTransformer transformer = new WorkoutTrackerDTOTransformer();

    @Override
    public boolean addWorkoutToTracker(WorkoutTracker workoutTracker, String token) throws AppException {

        logger.info("Service: Add workout to tracker");

        // Verify the payload
        BaseValidator.checkObjectIsNull(workoutTracker.getId(), "Id should be null");
        BaseValidator.checkObjectIsNotNull(workoutTracker.getWorkoutName(), "Workout name cannot be null");
        BaseValidator.checkObjectIsNotNull(workoutTracker.getDate(), "Date should not be null");
        BaseValidator.checkObjectIsNotNull(workoutTracker.getDuration(), "Duration should not be null");
        BaseValidator.checkObjectIsNull(workoutTracker.getEmail(), "Email should be null");

        // Calculate email from token
        String email = jwtService.extractEmail(token);
        BaseValidator.checkObjectIsNotNull(email, "Token invalid");

        // Verify that user exists in the Database
        Optional<UserDTO> userDTOByEmail = userDAO.findById(email);
        if(userDTOByEmail.isEmpty()) {
            throw new AppException("User does not exists in the database", ExceptionLevel.VALIDATION, "Malicious attack. Alert!");
        }

        // Verify that workout exists in the Database
        Optional<WorkoutDTO> workoutDTOByName = workoutDAO.findById(workoutTracker.getWorkoutName());
        if(workoutDTOByName.isEmpty()) {
            throw new AppException("Workout not present in Database", ExceptionLevel.VALIDATION, "Malicious attack. Alert!");
        }

        // Transform to DTO
        WorkoutTrackerDTO workoutTrackerDTO = transformer.transformToDTO(workoutTracker);

        // Workout name and email has to be converted here as they are dependent on services
        workoutTrackerDTO.setWorkoutDTO(workoutDTOByName.get());
        workoutTrackerDTO.setUserDTO(userDTOByEmail.get());

        // Save to DataBase
        workoutTrackerDAO.saveAndFlush(workoutTrackerDTO);
        logger.info("Service: Workout added successfully to tracker");

        return true;
    }

    @Override
    public List<WorkoutTracker> getWorkoutTracker(String token) throws AppException {

        logger.info("Service: Start fetching Workout Tracker");
        List<WorkoutTracker> workoutTrackerList = new ArrayList<>();

        // Find email from token
        String email = jwtService.extractEmail(token);

        // Find and filter the workout tracker items
        List<WorkoutTrackerDTO> workoutTrackerDTOList = workoutTrackerDAO.findAll();
        List<WorkoutTrackerDTO> workoutTrackerDTOListForLoggedUser = workoutTrackerDTOList.stream().filter(dto ->
                dto.getUserDTO().getEmail().equals(email)).collect(Collectors.toList());

        if(workoutTrackerDTOListForLoggedUser.isEmpty()) {
            throw new AppException("Your workout list is empty", ExceptionLevel.INFO, "No workouts added by user in tracker");
        }

        // Transform to model
        for (WorkoutTrackerDTO workoutTrackerDTO : workoutTrackerDTOListForLoggedUser) {
            workoutTrackerList.add(transformer.transformToModel(workoutTrackerDTO));
        }

        logger.info("Service: Success fetching workout tracker");
        return workoutTrackerList;
    }

    @Override
    public boolean updateWorkoutInTracker(Integer id, WorkoutTracker workoutTracker, String token) throws AppException {

        logger.info("Service: Start updating workout in tracker");

        // Verify the payload
        BaseValidator.checkObjectIsNotNull(workoutTracker.getId(), "Id should not be null");
        BaseValidator.checkObjectIsNotNull(workoutTracker.getWorkoutName(), "Workout name cannot be null");
        BaseValidator.checkObjectIsNotNull(workoutTracker.getDate(), "Date should not be null");
        BaseValidator.checkObjectIsNotNull(workoutTracker.getDuration(), "Duration should not be null");
        BaseValidator.checkObjectIsNull(workoutTracker.getEmail(), "Email should be null");

        // Validate that id and workoutTracker id should match
        if(!workoutTracker.getId().equals(id)) {
            throw new AppException("Path Id and object id should match", ExceptionLevel.VALIDATION, "Malicious attack");
        }

        // Validate that Food Tracker already contained an item with this id
        if(workoutTrackerDAO.findById(id).isEmpty()) {
            throw new AppException("Object with this id never existed in the database");
        }

        // Calculate email from token
        String email = jwtService.extractEmail(token);
        BaseValidator.checkObjectIsNotNull(email, "Token invalid");

        // Verify that user exists in the Database
        Optional<UserDTO> userDTOByEmail = userDAO.findById(email);
        if(userDTOByEmail.isEmpty()) {
            throw new AppException("User does not exists in the database", ExceptionLevel.VALIDATION, "Malicious attack. Alert!");
        }

        // Verify that workout exists in the Database
        Optional<WorkoutDTO> workoutDTOByName = workoutDAO.findById(workoutTracker.getWorkoutName());
        if(workoutDTOByName.isEmpty()) {
            throw new AppException("Workout not present in Database", ExceptionLevel.VALIDATION, "Malicious attack. Alert!");
        }

        // Transform to DTO
        WorkoutTrackerDTO workoutTrackerDTO = transformer.transformToDTO(workoutTracker);

        // Workout name and email has to be converted here as they are dependent on services
        workoutTrackerDTO.setWorkoutDTO(workoutDTOByName.get());
        workoutTrackerDTO.setUserDTO(userDTOByEmail.get());

        // Save to DataBase
        workoutTrackerDAO.saveAndFlush(workoutTrackerDTO);
        logger.info("Service: Workout successfully updated to tracker");

        return true;
    }

    @Override
    public boolean deleteWorkoutFromTracker(Integer id, String token) throws AppException {

        logger.info("Service: Start deleting Workout from Tracker");

        // Validate that Food Tracker already contained an item with this id
        if(workoutTrackerDAO.findById(id).isEmpty()) {
            throw new AppException("Object with this id never existed in the database");
        }

        // Calculate email from token
        String email = jwtService.extractEmail(token);
        BaseValidator.checkObjectIsNotNull(email, "Token invalid");

        // Verify that user exists in the Database
        Optional<UserDTO> userDTOByEmail = userDAO.findById(email);
        if(userDTOByEmail.isEmpty()) {
            throw new AppException("User does not exists in the database", ExceptionLevel.VALIDATION, "Malicious attack. Alert!");
        }

        // Delete from Database
        workoutTrackerDAO.deleteById(id);

        // Check delete was successful
        if(!workoutTrackerDAO.findById(id).isEmpty())
            throw new AppException("Failed to delete from Database");

        logger.info("Service: Successfully deleted Workout from Tracker");

        return true;
    }
}
