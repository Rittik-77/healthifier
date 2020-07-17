package com.ricky.healthifier.service.food;

import com.ricky.healthifier.controller.food.FoodController;
import com.ricky.healthifier.dao.FoodDAO;
import com.ricky.healthifier.dao.WorkoutDAO;
import com.ricky.healthifier.datamodel.food.Food;
import com.ricky.healthifier.entity.food.FoodDTO;
import com.ricky.healthifier.entity.food.QuantityEnumDTO;
import com.ricky.healthifier.utils.exception.AppException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class FoodServiceImplTest {

    @Autowired
    private FoodService foodService;

    @MockBean
    private FoodDAO foodDAO;

    /**
     * WorkoutDAO is not needed here, but when used as a MockBean, it also mocks the init service which uses this
     * workoutDAO to feed values to DataBase.
     * Basically, it helps in omitting the PostConstruct for this table
     */
    @MockBean
    private WorkoutDAO workoutDAO;

    @Before
    public void setUp() {

        // Mocking the DAO
        List<FoodDTO> mockFoodList = new ArrayList<>();
        FoodDTO f1 = new FoodDTO("abc", new QuantityEnumDTO("GRAM"), 12, 13, "blah.jpg");
        FoodDTO f2 = new FoodDTO("xyz", new QuantityEnumDTO("ML"), 10, 15, "blah.jpg");
        mockFoodList.add(f1);
        mockFoodList.add(f2);
        Mockito.when(foodDAO.findAll()).thenReturn(Stream.of(f1,f2).collect(Collectors.toList()));
    }

    /**
     * Check that no fields are null and name is unique
     */
    @Test
    public void getAllFoods() throws AppException {

        List<Food> foodList = foodService.getAllFoods();
        ListIterator<Food> foodListIterator = foodList.listIterator();

        // Store all the food names in this Set and compare the size to check uniqueness
        Set<String> foodNames = new HashSet<>();

        while(foodListIterator.hasNext()) {
            Food food = foodListIterator.next();
            foodNames.add(food.getName());
            FoodServiceImplTestUtil.assertNotNull(food);
        }

        Assert.assertTrue(foodNames.size() == foodList.size());
    }
}