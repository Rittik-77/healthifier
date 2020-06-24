package com.ricky.healthifier.service.food;

import com.ricky.healthifier.dao.FoodDAO;
import com.ricky.healthifier.datamodel.food.Food;
import com.ricky.healthifier.entity.food.FoodDTO;
import com.ricky.healthifier.entity.food.QuantityEnumDTO;
import com.ricky.healthifier.utils.exception.AppException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class FoodServiceImplTest {

    @Autowired
    private FoodService foodService;

    @MockBean
    private FoodDAO foodDAO;

    @Before
    public void mock() {
        List<FoodDTO> mockFoodList = new ArrayList<>();
        FoodDTO f1 = new FoodDTO("abc", new QuantityEnumDTO("GRAM"), 12, 13, "blah.jpg");
        f1.setId(1);
        FoodDTO f2 = new FoodDTO("xyz", new QuantityEnumDTO("ML"), 10, 15, "blah.jpg");
        f2.setId(2);
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

        System.out.println(foodList);

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