package com.example.demo.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Service
public class WeatherPredictionService {

    @Value("${weather.python.script}")
    private String pythonScriptPath;

    public String getPrediction(double precipitation, double tempMax, double tempMin, double wind) {
        // Prepare input data for Python script
        List<String> command = new ArrayList<>();
        command.add("python");
        command.add(pythonScriptPath);
        command.add(String.valueOf(precipitation));
        command.add(String.valueOf(tempMax));
        command.add(String.valueOf(tempMin));
        command.add(String.valueOf(wind));

        String prediction = "Error: Unable to get prediction"; // Default message in case of errors
        try {
            ProcessBuilder processBuilder = new ProcessBuilder(command);
            processBuilder.redirectErrorStream(true);
            Process process = processBuilder.start();

            // Read the output of the Python script
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            StringBuilder output = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                output.append(line);
            }
            prediction = output.toString();

            int exitCode = process.waitFor();
            if (exitCode != 0) {
                throw new RuntimeException("Python script exited with error code: " + exitCode);
            }

        } catch (Exception e) {
            e.printStackTrace();
            prediction = "Error: " + e.getMessage();
        }

        return prediction;
    }
}
