package com.example.bmicalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {
    TextView resultTextView;
    EditText ageTextView;
    EditText weightTextView;
    EditText heightTextView;
    RadioButton maleButton;
    RadioButton femaleButton;
    Button calculateButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();
        setupCalculateButtonClickListener();
    }

    private void setupCalculateButtonClickListener() {
        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateBmi();
                double bmiVal = calculateBmi();
                int age = Integer.parseInt(ageTextView.getText().toString());
                if (age >= 18) {
                    displayResult(bmiVal);
                } else {
                    displayGuidance(bmiVal);
                }
            }
        });
    }

    private void displayGuidance(double bmi) {
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        String bmiTextResult = decimalFormat.format(bmi);
        String fullStringResult = "";
        if (maleButton.isChecked()) {
            fullStringResult = bmiTextResult + " - as your age is under 18, consult doctor for healthy range for boys";
        } else if (femaleButton.isChecked()) {
            fullStringResult = bmiTextResult + " - as your age is under 18, consult doctor for healthy range for girls";
        } else {
            displayResult(bmi);
        }
        resultTextView.setText(fullStringResult);
    }

    private double calculateBmi() {
        double height = Double.parseDouble(heightTextView.getText().toString()) / 100;
        double weight = Double.parseDouble(weightTextView.getText().toString());
        double bmi = weight / (height * height);
        Log.d("calculated bmi", String.valueOf(bmi));
        return bmi;
    }

    private void findViews() {
        resultTextView = findViewById(R.id.text_view_result);
        ageTextView = findViewById(R.id.edit_text_age);
        weightTextView = findViewById(R.id.edit_text_weight);
        heightTextView = findViewById(R.id.edit_text_height);
        maleButton = findViewById(R.id.radio_button_male);
        femaleButton = findViewById(R.id.radio_button_female);
        calculateButton = findViewById(R.id.button_calculate);
    }

    private void displayResult(double bmi) {
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        String bmiTextResult = decimalFormat.format(bmi);
        String fullStringResult = "";
        if (bmi < 18.5) {
            fullStringResult = bmiTextResult + " - You are underweight";
        } else if (bmi > 25) {
            fullStringResult = bmiTextResult + " - You are overweight";
        } else {
            fullStringResult = bmiTextResult + " - You are healthy";
        }
        resultTextView.setText(fullStringResult);
    }
}