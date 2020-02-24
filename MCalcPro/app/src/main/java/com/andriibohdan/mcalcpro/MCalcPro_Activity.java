package com.andriibohdan.mcalcpro;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

import ca.roumani.i2c.MPro;

public class MCalcPro_Activity extends AppCompatActivity implements TextToSpeech.OnInitListener, SensorEventListener {

    private TextToSpeech tts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mcalcpro_layout);

        SensorManager sm = (SensorManager) getSystemService(SENSOR_SERVICE);
        sm.registerListener(this, sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), sm.SENSOR_DELAY_NORMAL);

        tts = new TextToSpeech(this,this);

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

        String out = "Monthly payment: $" + mp.computePayment("%,.2f") +
                "\n\nBy making this payment monthly for " + mp.getAmortization() +
                " years, the mortgage will be paid in full. But if you terminate the mortgage " +
                "on its nth anniversary, the balance still owing depends on n as shown below: ";
        tts.speak(out, TextToSpeech.QUEUE_FLUSH, null);
        out += "\n\n\tn\t\t\t\t\t\t\t\tBalance\n\n";

        int amortizationNum = Integer.parseInt(mp.getAmortization());
        for(int i = 1; i <= 4; i++) {
            out += "\t" + i + "\t" + mp.outstandingAfter(i,"%,16.0f") + "\n";
        }
        for(int i = 5; i <= amortizationNum; i+=5) {
            out += "\t" + i + "\t" + mp.outstandingAfter(i,"%,16.0f") + "\n";
        }
        if(amortizationNum % 5 != 0) {
            out += "\t" + amortizationNum + "\t" +
                    mp.outstandingAfter(amortizationNum,"%,16.0f") + "\n";
        }
        TextView tv = findViewById(R.id.output);
        tv.setText(out);

    }

    @Override
    public void onInit(int status) {
        this.tts.setLanguage(Locale.CANADA);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        double ax = event.values[0];
        double ay = event.values[1];
        double az = event.values[2];
        double a = Math.sqrt(ax*ax + ay*ay + az*az);
        if(a > 20) {
            ((EditText) findViewById(R.id.pBox)).setText("");
            ((EditText) findViewById(R.id.aBox)).setText("");
            ((EditText) findViewById(R.id.iBox)).setText("");
            ((TextView) findViewById(R.id.output)).setText("");
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) { }
}
