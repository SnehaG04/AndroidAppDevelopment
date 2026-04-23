package com.example.filehandling;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    Button b1, b2;
    TextView tv;
    EditText ed1;
    String data;
    private final String file = "mydata.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b1 = findViewById(R.id.button);
        b2 = findViewById(R.id.button2);
        ed1 = findViewById(R.id.editText);
        tv = findViewById(R.id.textview2);

        b1.setOnClickListener(v -> {
            data = ed1.getText().toString();
            try (FileOutputStream fOut = openFileOutput(file, MODE_APPEND)) {
                fOut.write(data.getBytes());
                Toast.makeText(getBaseContext(), "file saved", Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                Log.e(TAG, "Error saving file", e);
            }
        });

        b2.setOnClickListener(v -> {
            try (FileInputStream fin = openFileInput(file)) {
                int c;
                StringBuilder temp = new StringBuilder();
                while ((c = fin.read()) != -1) {
                    temp.append((char) c);
                }
                tv.setText(temp.toString());
                Toast.makeText(getBaseContext(), "file read", Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                Log.e(TAG, "Error reading file", e);
            }
        });
    }
}
