package com.ricky.healthifier.service.tracker;

import com.ricky.healthifier.dao.UserDAO;
import com.ricky.healthifier.dao.WorkoutDAO;
import com.ricky.healthifier.dao.WorkoutTrackerDAO;
import com.ricky.healthifier.datamodel.tracker.WorkoutTracker;
import com.ricky.healthifier.entity.tracker.WorkoutTrackerDTO;
import com.ricky.healthifier.entity.tracker.WorkoutTrackerDTOTransformer;
import com.ricky.healthifier.entity.user.UserDTO;
import com.ricky.healthifier.entity.workout.WorkoutDTO;
import com.ricky.healthifier.utils.commons.BaseValidator;
import com.ricky.healthifier.utils.exception.AppException;
import com.ricky.healthifier.utils.exception.ExceptionLevel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

@Service
public class WorkoutTrackerServiceImpl implements WorkoutTrackerService {

    @Autowired
    private WorkoutTrackerDAO workoutTrackerDAO;

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private WorkoutDAO workoutDAO;

    private final Logger logger = LoggerFactory.getLogger(WorkoutTrackerServiceImpl.class);
    private final WorkoutTrackerDTOTransformer transformer = new WorkoutTrackerDTOTransformer();

    @Override
    public boolean addWorkoutToTracker(WorkoutTracker workoutTracker) throws AppException {

        logger.info("Service: Add workout to tracker");

        // Verify the payload
        BaseValidator.checkObjectIsNull(workoutTracker.getId(), "Id should be null");
        BaseValidator.checkObjectIsNotNull(workoutTracker.getWorkoutName(), "Workout name cannot be null");
        BaseValidator.checkObjectIsNull(workoutTracker.getDate(), "Date should be null");
        BaseValidator.checkObjectIsNotNull(workoutTracker.getDuration(), "Duration should not be null");
        // TODO: make email null and will be calculated from token
        BaseValidator.checkObjectIsNotNull(workoutTracker.getEmail(), "User Email cannot be null");

        // Verify that user exists in the Database
        Optional<UserDTO> userDTOByEmail = userDAO.findById(workoutTracker.getEmail());
        if(userDTOByEmail.isEmpty()) {
            throw new AppException("User does not exists in the database", ExceptionLevel.VALIDATION, "Malicious attack. Alert!");
        }

        // Verify that food exists in the Database
        Optional<WorkoutDTO> workoutDTOByName = workoutDAO.findById(workoutTracker.getWorkoutName());
        if(workoutDTOByName.isEmpty()) {
            throw new AppException("Workout not present in Database", ExceptionLevel.VALIDATION, "Malicious attack. Alert!");
        }

        // Set the Date
        workoutTracker.setDate(new Date(System.currentTimeMillis()));

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
}
