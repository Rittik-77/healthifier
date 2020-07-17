package com.ricky.healthifier.controller.food;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ricky.healthifier.datamodel.food.Food;
import com.ricky.healthifier.datamodel.food.QuantityEnum;
import com.ricky.healthifier.service.food.FoodService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.Arrays;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = FoodController.class)
@ActiveProfiles("test")
public class FoodControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FoodService foodService;

    @Before
    public void setUp() throws Exception {

        // Mock the service
        Food f1 = new Food("food1", QuantityEnum.GRAM, 78.9, 98.6, "five");
        Food f2 = new Food("food2", QuantityEnum.ML, 75.9, 28.6, "fi4ve");
        Mockito.when(foodService.getAllFoods()).thenReturn(new ArrayList<>(Arrays.asList(f1,f2)));
    }

    @Test
    public void testGetAllFood() throws Exception {

        // For converting to json
        ObjectMapper objectMapper = new ObjectMapper();

        // Prepare the expected response
        FoodVO f1 = new FoodVO("food1", QuantityEnum.GRAM, 78.9, 98.6, "five");
        FoodVO f2 = new FoodVO("food2", QuantityEnum.ML, 75.9, 28.6, "fi4ve");
        String json = objectMapper.writeValueAsString(Arrays.asList(f1,f2));

        // Call the API to get the actual response
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/api/foods")).andReturn();
        String res = result.getResponse().getContentAsString();

        // Assert the response
        Assert.assertEquals(json, res);
    }
}