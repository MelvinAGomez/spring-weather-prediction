package com.example.demo.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.ui.Model;
import com.example.demo.service.WeatherPredictionService;

public class WeatherControllerTest {
    
    private WeatherController weatherController;
    private WeatherPredictionService weatherServiceMock;
    private Model modelMock;

    @BeforeEach
    void setUp() {
        // Mock dependencies
        weatherServiceMock = Mockito.mock(WeatherPredictionService.class);
        modelMock = Mockito.mock(Model.class);

        // Inject mocks into the controller
        weatherController = new WeatherController(weatherServiceMock);
    }


    @Test
    void testGetHomePage(){
        String viewName = weatherController.getHomePage();
        assertEquals("home", viewName, "Home page should return the 'home' view");
    }
    @Test
    void testSubmitWeather(){
        when(weatherServiceMock.getPrediction(1.0, 30.0, 15.0, 5.0)).thenReturn("Sunny");
        String viewName = weatherController.submitWeather(1.0, 30.0, 15.0, 5.0, modelMock);
        System.out.println(viewName);
        verify(modelMock).addAttribute("prediction", "Sunny");
        verify(weatherServiceMock).getPrediction(1.0, 30.0, 15.0, 5.0);
        assertEquals("result", viewName, "Submit weather should return the 'result' view");
    }
    @Test
    void getPredictionSunny(){
    // Define the mock behavior
        when(weatherServiceMock.getPrediction(1.0, 30.0, 15.0, 5.0)).thenReturn("Sunny");

        // Call the method
        String prediction = weatherServiceMock.getPrediction(1.0, 30.0, 15.0, 5.0);

        // Assert the result
        assertEquals("Sunny", prediction, "Expected prediction to be 'Sunny' for the given inputs");
    }

}
