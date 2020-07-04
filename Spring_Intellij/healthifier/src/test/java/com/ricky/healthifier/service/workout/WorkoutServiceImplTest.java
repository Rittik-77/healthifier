package com.ricky.healthifier.service.workout;

import com.ricky.healthifier.dao.FoodDAO;
import com.ricky.healthifier.dao.WorkoutDAO;
import com.ricky.healthifier.datamodel.workout.Workout;
import com.ricky.healthifier.entity.workout.WorkoutDTO;
import com.ricky.healthifier.utils.exception.AppException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.*;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class WorkoutServiceImplTest {

    @Autowired
    private WorkoutService workoutService;

    @MockBean
    private WorkoutDAO workoutDAO;

    /**
     * This will stop the postconstruct function from feeding the database again with the json file
     */
    @MockBean
    private FoodDAO foodDAO;

    @Before
    public void setUp() throws Exception {

        // Mocking the DAO
        WorkoutDTO w1 = new WorkoutDTO("abc", 56.7);
        WorkoutDTO w2 = new WorkoutDTO("xyz", 98);
        Mockito.when(workoutDAO.findAll()).thenReturn(Arrays.asList(w1,w2));
    }

    @Test
    public void testGetAllWorkouts() throws AppException {

        List<Workout> workoutList = workoutService.getAllWorkouts();
        ListIterator<Workout> workoutListIterator = workoutList.listIterator();

        Set<String> workoutNames = new HashSet<>();
        while (workoutListIterator.hasNext()) {
            Workout workout = workoutListIterator.next();
            WorkoutServiceImplTestUtil.assertNotNull(workout);
            workoutNames.add(workout.getName());
        }

        Assert.assertEquals(workoutNames.size(), workoutList.size());
    }
}