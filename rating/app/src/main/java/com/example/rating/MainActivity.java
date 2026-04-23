package com.example.rating;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

    RatingBar ratingBar;
    TextView tvRating;
    ProgressBar progressBar;
    Button btnIncrease, btnSubmit;
    RelativeLayout rootLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Toolbar setup
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        rootLayout = findViewById(R.id.rootLayout);
        ratingBar = findViewById(R.id.ratingBar);
        tvRating = findViewById(R.id.tvRating);
        progressBar = findViewById(R.id.progressBar);
        btnIncrease = findViewById(R.id.btnIncrease);
        btnSubmit = findViewById(R.id.btnSubmit);

        // RatingBar listener
        ratingBar.setOnRatingBarChangeListener((ratingBar, rating, fromUser) ->
                tvRating.setText("Rating: " + (int) rating));

        // Increase progress button
        btnIncrease.setOnClickListener(v -> {
            int progress = progressBar.getProgress();
            if (progress < progressBar.getMax()) {
                progress += 10;
                if (progress > progressBar.getMax()) progress = progressBar.getMax();
                progressBar.setProgress(progress);
            }
        });

        // Submit button
        btnSubmit.setOnClickListener(v -> {
            int rating = (int) ratingBar.getRating();
            int progress = progressBar.getProgress();
            Toast.makeText(this, "Rating: " + rating + ", Progress: " + progress,
                    Toast.LENGTH_SHORT).show();

            ratingBar.setRating(0);
            progressBar.setProgress(0);
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    // Menu actions with background color change
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_reset) {
            ratingBar.setRating(0);
            progressBar.setProgress(0);
            rootLayout.setBackgroundColor(Color.WHITE); // reset background to white
            Toast.makeText(this, "Reset complete", Toast.LENGTH_SHORT).show();
            return true;

        } else if (id == R.id.action_about) {
            rootLayout.setBackgroundColor(Color.LTGRAY); // light gray background
            new AlertDialog.Builder(this)
                    .setTitle("About")
                    .setMessage("Rating App\nVersion 1.0\nCreated with Android Studio Java")
                    .setPositiveButton("OK", null)
                    .show();
            return true;

        } else if (id == R.id.action_exit) {
            rootLayout.setBackgroundColor(Color.RED); // red background before exit
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}