package com.andriibohdan.mcalcpro;

import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import ca.roumani.i2c.MPro;

public class MCalcPro_Activity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mcalcpro_layout);

        final EditText pBox = findViewById(R.id.pBox),
                aBox = findViewById(R.id.aBox),
                iBox = findViewById(R.id.iBox);

        pBox.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus)
                    pBoxValidate();
            }
        });
        aBox.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus)
                    aBoxValidate();
            }
        });
        iBox.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus)
                    iBoxValidate();
            }
        });
    }

    private boolean pBoxValidate() {
        boolean isValid = true;
        if(((EditText) findViewById(R.id.pBox)).getText().length() != 0) {
            double value = Double.parseDouble(
                    ((EditText) findViewById(R.id.pBox)).getText().toString()
            );
            if(Double.isNaN(value)) {
                Toast.makeText(getApplicationContext(),
                        "Proposition must be a number",
                        Toast.LENGTH_SHORT).show();
                isValid = false;
            }
            else if(value < MPro.EPSILON) {
                Toast.makeText(getApplicationContext(),
                        "Proposition must more than " + MPro.EPSILON,
                        Toast.LENGTH_SHORT).show();
                isValid = false;
            }
        }
        else {
            Toast.makeText(getApplicationContext(),
                    "Proposition must have a value",
                    Toast.LENGTH_SHORT).show();
            isValid = false;
        }
        return isValid;
    }

    private boolean aBoxValidate() {
        boolean isValid = true;
        if(((EditText) findViewById(R.id.aBox)).getText().length() != 0) {
            double value = Double.parseDouble(
                    ((EditText) findViewById(R.id.aBox)).getText().toString()
            );
            if(Double.isNaN(value)) {
                Toast.makeText(getApplicationContext(),
                        "Amortization must be a number",
                        Toast.LENGTH_SHORT).show();
                isValid = false;
            }
            else if(value < MPro.AMORT_MIN) {
                Toast.makeText(getApplicationContext(),
                        "Amortization must be more than " + MPro.AMORT_MIN,
                        Toast.LENGTH_SHORT).show();
                isValid = false;
            }
            else if(value > MPro.AMORT_MAX) {
                Toast.makeText(getApplicationContext(),
                        "Amortization must be less than " + MPro.AMORT_MAX,
                        Toast.LENGTH_SHORT).show();
                isValid = false;
            }
        }
        else {
            Toast.makeText(getApplicationContext(),
                    "Amortization must have a value",
                    Toast.LENGTH_SHORT).show();
            isValid = false;
        }
        return isValid;
    }

    private boolean iBoxValidate() {
        boolean isValid = true;
        if(((EditText) findViewById(R.id.iBox)).getText().length() != 0) {
            double value = Double.parseDouble(
                    ((EditText) findViewById(R.id.iBox)).getText().toString()
            );
            if(Double.isNaN(value)) {
                Toast.makeText(getApplicationContext(),
                        "Proposition must be a number",
                        Toast.LENGTH_SHORT).show();
                isValid = false;
            }
            else if(value < MPro.EPSILON) {
                Toast.makeText(getApplicationContext(),
                        "Proposition must be more than " + MPro.EPSILON,
                        Toast.LENGTH_SHORT).show();
                isValid = false;
            }
            else if(value > MPro.INTEREST_MAX) {
                Toast.makeText(getApplicationContext(),
                        "Interest must be less than " + MPro.INTEREST_MAX,
                        Toast.LENGTH_SHORT).show();
                isValid = false;
            }
        }
        else {
            Toast.makeText(getApplicationContext(),
                    "Interest must have a value",
                    Toast.LENGTH_SHORT).show();
            isValid = false;
        }
        return isValid;
    }

    public void onAnalyzeBtnClick(View v) {
        boolean pValid = pBoxValidate(),
                aValid = aBoxValidate(),
                iValid = iBoxValidate();
        if(!pValid || !aValid || !iValid) {
            return;
        }
        String principle = ((EditText)findViewById(R.id.pBox)).getText().toString(),
                amortization = ((EditText)findViewById(R.id.aBox)).getText().toString(),
                interest = ((EditText)findViewById(R.id.iBox)).getText().toString();

        MPro mp = new MPro();
        mp.setPrinciple(principle);
        mp.setAmortization(amortization);
        mp.setInterest(interest);
    }
}
