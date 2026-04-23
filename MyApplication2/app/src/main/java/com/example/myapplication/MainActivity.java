package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    private EditText taskInput;
    private Button addButton;
    private TextView taskList;
    private DatabaseReference database = FirebaseDatabase.getInstance().getReference("tasks");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        taskInput = findViewById(R.id.taskInput);
        addButton = findViewById(R.id.addButton);
        taskList = findViewById(R.id.taskList);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = taskInput.getText().toString();
                if (!title.isEmpty()) {
                    database.push().setValue(title);  // Send to backend
                    taskInput.setText("");
                }
            }
        });

        // Live updates from Firebase backend
        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                StringBuilder tasks = new StringBuilder();
                for (DataSnapshot data : snapshot.getChildren()) {
                    tasks.append(data.getValue()).append("\n");
                }
                taskList.setText(tasks.toString());
            }

            @Override
            public void onCancelled(DatabaseError error) { }
        });
    }
}