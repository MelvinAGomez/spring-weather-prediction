package com.example.demo.controller;

import com.example.demo.service.WeatherPredictionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class WeatherController {
    @Autowired
    private final WeatherPredictionService weatherPredictionService;

    // Inject the WeatherPredictionService
    public WeatherController(WeatherPredictionService weatherPredictionService) {
        this.weatherPredictionService = weatherPredictionService;
    }

    @GetMapping("/home")
    public String getHomePage() {
        // Return the name of the HTML template (home.html)
        return "home";
    }

    @PostMapping("/submit-weather")
    public String submitWeather(
            @RequestParam("precipitation") double precipitation,
            @RequestParam("temp_max") double tempMax,
            @RequestParam("temp_min") double tempMin,
            @RequestParam("wind") double wind,
            Model model) {

        // Get prediction from the service
        String prediction = weatherPredictionService.getPrediction(precipitation, tempMax, tempMin, wind);

        // Add prediction to the model
        model.addAttribute("prediction", prediction);
        return "result"; // Redirect to a result.html page
    }
}
