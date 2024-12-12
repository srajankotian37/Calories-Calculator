package com.example.calories;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

public class Calories2 extends AppCompatActivity {
    private EditText editAge, editHeight, editWeight;
    private RadioGroup radioGroupGender;
    private Spinner spinnerActivityLevel;
    private Button btnCalculate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calories2);

        editAge = findViewById(R.id.editAge);
        editHeight = findViewById(R.id.editHeight);
        editWeight = findViewById(R.id.editWeight);
        radioGroupGender = findViewById(R.id.radioGroupGender);
        spinnerActivityLevel = findViewById(R.id.spinnerActivityLevel);
        btnCalculate = findViewById(R.id.btnCalculate);
        spinnerActivityLevel = findViewById(R.id.spinnerActivityLevel);

        btnCalculate=findViewById(R.id.btnCalculate);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.activity_levels_array, android.R.layout.simple_spinner_item);

// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

// Apply the adapter to the spinner
        spinnerActivityLevel.setAdapter(adapter);

        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Retrieve user inputs

                String ageStr = editAge.getText().toString();
                String heightStr = editHeight.getText().toString();
                String weightStr = editWeight.getText().toString();

                if (ageStr.isEmpty() || heightStr.isEmpty() || weightStr.isEmpty()) {
                    // Display a toast message if any field is empty
                    Toast.makeText(Calories2.this, "Please enter all the fields!!", Toast.LENGTH_SHORT).show();
                    return;
                }
              //  btnCalculate();
                int age = Integer.parseInt(editAge.getText().toString());
                int heightCm = Integer.parseInt(editHeight.getText().toString());
                int weightKg = Integer.parseInt(editWeight.getText().toString());
                String gender = ((RadioButton) findViewById(radioGroupGender.getCheckedRadioButtonId())).getText().toString();
                String activityLevel = spinnerActivityLevel.getSelectedItem().toString();

                double calculatedCalories = calculateCalories(age, heightCm, weightKg, gender, activityLevel);



                Intent intent = new Intent(Calories2.this, Result.class);
                intent.putExtra("calories", calculatedCalories);
                startActivity(intent);
            }
        });
    }

    //public void btnCalculate() {
      //  Intent intent = new Intent(Calories2.this, Result.class);
        //startActivity(intent);
    //}


    private double calculateCalories(int age, int heightCm, int weightKg, String gender, String activityLevel) {
        double bmr;
        // Calculate BMR (Basal Metabolic Rate) based on gender
        if (gender.equals("Male")) {
           // bmr = 88.362 + (13.397 * weight) + (4.799 * height) - (5.677 * age);
            bmr = 10 * weightKg + 6.25 * heightCm - 5 * age + 5;
        }
        else {
           // bmr = 447.593 + (9.247 * weight) + (3.098 * height) - (4.330 * age);
            bmr = 10 * weightKg + 6.25 * heightCm - 5 * age - 161;
        }

        // Apply activity level multiplier to BMR
        double activityMultiplier;
        if (activityLevel.equals("Sedentary")) {
            activityMultiplier = 1.2;
        } else if (activityLevel.equals("Lightly active")) {
            activityMultiplier = 1.375;
        } else if (activityLevel.equals("Moderately active")) {
            activityMultiplier = 1.55;
        } else if (activityLevel.equals("Very active")) {
            activityMultiplier = 1.725;
        } else { // Extra active
            activityMultiplier = 1.9;
        }

        double totalCalories = bmr * activityMultiplier;
        return totalCalories;
    }
}

