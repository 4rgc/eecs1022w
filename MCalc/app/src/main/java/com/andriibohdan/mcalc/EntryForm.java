package com.andriibohdan.mcalc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class EntryForm extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mortgage_layout);
    }

    public void onMonthlyButtonClick(View v) {
        MortgageModel model = new MortgageModel(
            ((EditText)findViewById(R.id.principleView)).getText().toString(),
            ((EditText)findViewById(R.id.amortizationView)).getText().toString(),
            ((EditText)findViewById(R.id.interestView)).getText().toString()
        );
        model.computePayment();
        ((TextView) findViewById(R.id.monthlyPaymentLabel))
                .setText(
                        model.getMonthlyPaymentString()
                );
    }
}
