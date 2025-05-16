package com.example.healthtipapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.button.MaterialButton;
import java.util.Random;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    TextView tvTip;
    MaterialButton btnSettings, btnRefresh;
    String goal; // Moved to global scope

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MaterialToolbar toolbar = findViewById(R.id.topAppBar);
        setSupportActionBar(toolbar);

        tvTip = findViewById(R.id.tvTip);
        btnSettings = findViewById(R.id.btnSettings);
        btnRefresh = findViewById(R.id.btnRefresh);

        // ✅ Get goal from SharedPreferences here so it's available for refresh too
        SharedPreferences prefs = getSharedPreferences("HealthTips", MODE_PRIVATE);
        goal = prefs.getString("goal", "General");

        // Show initial tip
        showTip(goal);

        // Refresh button click
        btnRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvTip.setText(getRandomTip(goal));
            }
        });

        // Settings button click
        btnSettings.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
            startActivity(intent);
        });

    }
    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences prefs = getSharedPreferences("HealthTips", MODE_PRIVATE);
        goal = prefs.getString("goal", "General");
        showTip(goal);
    }

    private String getRandomTip(String goal) {
        String[] tips;

        switch (goal) {
            case "Weight Loss":
                tips = new String[]{
                        "Drink water before meals.",
                        "Avoid sugary drinks.",
                        "Eat more protein.",
                        "Get enough sleep."
                };
                break;
            case "Mental Health":
                tips = new String[]{
                        "Practice mindfulness daily.",
                        "Talk to someone you trust.",
                        "Take breaks and rest.",
                        "Limit screen time."
                };
                break;
            case "Fitness":
                tips = new String[]{
                        "Do bodyweight workouts.",
                        "Try yoga for flexibility.",
                        "Go for a jog or brisk walk.",
                        "Stay consistent — results take time."
                };
                break;
            default:
                tips = new String[]{
                        "Take regular walks.",
                        "Stretch in the morning.",
                        "Drink enough water.",
                        "Get 8 hours of sleep."
                };
                break;
        }

        int index = new Random().nextInt(tips.length);
        return tips[index];
    }

    private void showTip(String goal) {
        tvTip.setText(getRandomTip(goal));
    }
}
