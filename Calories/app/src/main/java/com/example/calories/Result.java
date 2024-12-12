package com.example.calories;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Result extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        // Retrieve calculated calories from the intent
        double calculatedCalories = getIntent().getDoubleExtra("calories", 0.0);

        // Find the TextView in the layout
        TextView txtCalories = findViewById(R.id.txtCalories);
        TextView txtSuggestions = findViewById(R.id.txtSuggestions);

        // Display the calculated calories in the TextView
        txtCalories.setText("Calories: " + String.format("%.2f", calculatedCalories));

        String suggestions = generateSuggestions(calculatedCalories);
        txtSuggestions.setText(suggestions);

    }

    private String generateSuggestions(double calculatedCalories) {
        // Implement your logic to generate suggestions based on calculated calories
        // You can use if-else statements or switch cases to tailor the suggestions

        String suggestions = "";

        if (calculatedCalories < 1500) {
            suggestions = "Consider adding more nutrient-rich foods to meet your calorie needs!";
        } else if (calculatedCalories > 2500) {
            suggestions = "Focus on portion control to manage your calorie intake!";
        } else {
            suggestions = "Maintain a balanced diet and stay active to achieve your goals!";
        }

        return suggestions;
    }
}