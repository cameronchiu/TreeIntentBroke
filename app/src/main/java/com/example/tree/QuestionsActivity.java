package com.example.tree;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class QuestionsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);



        EditText editTextSteps = (EditText) findViewById(R.id.editTextSteps);
        EditText editTextCups = (EditText) findViewById(R.id.editTextCups);
        EditText editTextMeals = (EditText) findViewById(R.id.editTextMeals);
        EditText editTextCalories = (EditText) findViewById(R.id.editTextCalories);
        EditText editTextSleep = (EditText) findViewById(R.id.editTextSleep);
        EditText editTextExercise = (EditText) findViewById(R.id.editTextExercise);

        //Extracting the text from those edit text fields
        String editTextStepsStr = editTextSteps.getText().toString();
        String editTextCupsStr = editTextCups.getText().toString();
        String editTextMealsStr = editTextMeals.getText().toString();
        String editTextCaloriesStr = editTextCalories.getText().toString();
        String editTextSleepStr = editTextSleep.getText().toString();
        String editTextExerciseStr = editTextExercise.getText().toString();

        // Creating the intent object
//        Intent intent = new Intent(this, QuestionsActivity.class);

//        // putting data from edit text fields into intent to send to other activity
//        //  are constants in MainActivity class
//        intent.putExtra(MainActivity.STEPS_WALKED, editTextStepsStr);
//        intent.putExtra(MainActivity.CUPS_WATER, editTextCupsStr);
//        intent.putExtra(MainActivity.CALORIES_CONSUMED, editTextMealsStr);
//        intent.putExtra(MainActivity.MEALS_NUMBER, editTextCaloriesStr);
//        intent.putExtra(MainActivity.HOURS_SLEEP, editTextSleepStr);
//        intent.putExtra(MainActivity.HOURS_EXERCISE, editTextExerciseStr);
//
//
//        // loads the next activity (MainActivity)
//        startActivity(intent);

    }

}
