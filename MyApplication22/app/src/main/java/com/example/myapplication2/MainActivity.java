package com.example.myapplication2;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize Firebase
        try {
            if (FirebaseApp.getApps(this).isEmpty()) {
                FirebaseApp.initializeApp(this);
            }
            mDatabase = FirebaseDatabase.getInstance("https://pplication2-b48dc-default-rtdb.firebaseio.com/").getReference("user_messages");
        } catch (Exception e) {
            Toast.makeText(this, "Firebase initialization failed. Check google-services.json", Toast.LENGTH_LONG).show();
        }

        EditText nameInput = findViewById(R.id.nameInput);
        Button sendButton = findViewById(R.id.sendButton);

        sendButton.setOnClickListener(v -> {
            String name = nameInput.getText().toString();

            if (name.isEmpty()) {
                return;
            }

            if (mDatabase == null) {
                Toast.makeText(this, "Database not initialized", Toast.LENGTH_SHORT).show();
                return;
            }

            // 3. Flow: Client -> API -> Server
            mDatabase.setValue(name)
                    .addOnSuccessListener(aVoid -> {
                        // 4. Response: Backend sends back success
                        Toast.makeText(this, "Stored in Backend!", Toast.LENGTH_SHORT).show();
                    })
                    .addOnFailureListener(e -> {
                        Toast.makeText(this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    });
        });
    }
}
