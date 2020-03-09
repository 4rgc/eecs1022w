package com.andriibohdan.kryptonote;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onEncryptClick(View v) {
        String key = ((EditText) findViewById(R.id.key_tbox)).getText().toString();
        Cipher c = new Cipher(key);

        EditText data = ((EditText) findViewById(R.id.data_tbox));
        String note = data.getText().toString();
        String cipherText = c.encrypt(note);
        data.setText(cipherText);
    }

    public void onDecryptClick(View v) {
        String key = ((EditText) findViewById(R.id.key_tbox)).getText().toString();
        Cipher c = new Cipher(key);

        EditText data = ((EditText) findViewById(R.id.data_tbox));
        String note = data.getText().toString();
        String cipherText = c.decrypt(note);
        data.setText(cipherText);
    }

    public void onSave(View v) {
        try {
            String name = ((EditText) findViewById(R.id.filename_tbox)).getText().toString();
            File dir = this.getFilesDir();
            File file = new File(dir, name);
            FileWriter fw = new FileWriter(file);
            fw.write(((EditText) findViewById(R.id.data_tbox)).getText().toString());
            fw.close();
        } catch (IOException e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG);
        }
    }

    public void onLoad(View v) {
        try {
            String name = ((EditText) findViewById(R.id.filename_tbox)).getText().toString();
            File dir = this.getFilesDir();
            File file = new File(dir, name);
            String show = "";
            FileReader fr = new FileReader(file);
            for(int c = fr.read(); c != -1; c = fr.read()) {
                show += (char) c;
            }
            fr.close();
            ((EditText) findViewById(R.id.data_tbox)).setText(show);
        } catch (IOException e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG);
        }
    }
}
