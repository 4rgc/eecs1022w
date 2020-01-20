package com.example.bmiactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


public class BMIActivity extends AppCompatActivity
{
    private static final String DISPLAYED_BMI = "DisplayedBMI";
    private double currentBmi;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bmi_layout);

        String myBMI = String.format("%.1f", currentBmi);

        ((TextView) findViewById(R.id.answer)).setText(myBMI);
    }

    @Override
    protected void onSaveInstanceState(final Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putDouble(DISPLAYED_BMI, currentBmi);
    }

    @Override
    protected void onRestoreInstanceState(final Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        currentBmi = savedInstanceState.getDouble(DISPLAYED_BMI);
    }

    public void buttonClicked(View v) {
        EditText weightView = (EditText) findViewById(R.id.weightBox);
        String weight = weightView.getText().toString();

        EditText heightView = (EditText) findViewById(R.id.heightBox);
        String height = heightView.getText().toString();

        BMIModel myModel = new BMIModel(weight, height);
        currentBmi = myModel.GetBMI();
        String myBMI = String.format("%.1f", currentBmi);

        ((TextView) findViewById(R.id.answer)).setText(myBMI);

    }
}
