package com.example.healthtipapp;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.button.MaterialButton;
import androidx.appcompat.app.AppCompatActivity;

public class SettingsActivity extends AppCompatActivity {

    RadioGroup radioGroup;
    MaterialButton btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        MaterialToolbar toolbar = findViewById(R.id.topAppBar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(v -> finish()); // Back button

        radioGroup = findViewById(R.id.radioGroup);
        btnSave = findViewById(R.id.btnSave);

        SharedPreferences prefs = getSharedPreferences("HealthTips", MODE_PRIVATE);
        String savedGoal = prefs.getString("goal", "General");

        if (savedGoal.equals("Weight Loss")) radioGroup.check(R.id.rbWeightLoss);
        else if (savedGoal.equals("Mental Health")) radioGroup.check(R.id.rbMentalHealth);
        else if (savedGoal.equals("Fitness")) radioGroup.check(R.id.rbFitness);

        btnSave.setOnClickListener(v -> {
            int selectedId = radioGroup.getCheckedRadioButtonId();
            RadioButton selected = findViewById(selectedId);
            if (selected != null) {
                String goal = selected.getText().toString();
                prefs.edit().putString("goal", goal).apply();
                finish();
            }
        });
    }
}