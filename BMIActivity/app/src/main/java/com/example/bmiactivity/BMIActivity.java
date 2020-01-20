package com.example.bmiactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


public class BMIActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bmi_layout);
    }

    public void buttonClicked(View v) {
        EditText weightView = (EditText) findViewById(R.id.weightBox);
        String weight = weightView.getText().toString();

        EditText heightView = (EditText) findViewById(R.id.heightBox);
        String height = heightView.getText().toString();

        BMIModel myModel = new BMIModel(weight, height);
        double bmi = myModel.GetBMI();
        String myBMI = String.format("%.1f", bmi);

        ((TextView) findViewById(R.id.answer)).setText(myBMI);

    }
}
