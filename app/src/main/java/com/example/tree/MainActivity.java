package com.example.tree;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;


public class MainActivity extends AppCompatActivity {

    EditText nameEditText;
    EditText scoreEditText;
    TextView displayTextView;
    MyDatabaseHelper databaseHelper;
    DateFormat dateFormat = new SimpleDateFormat("d");
    DateFormat monthFormat = new SimpleDateFormat("MM");

    //Goals
    private int stepsGoal = 10000;
    private int cupsOfWaterGoal = 8;
    private int caloriesConsumedGoal = 2000;
    private int numberOfMealsGoal = 3;
    private int hoursOfSleepGoal = 8;
    private int hoursOfExerciseGoal = 1;

    //User's input variables from questions activity
    static final String STEPS_WALKED = "stepsWalked";
    static final String CUPS_WATER = "cupsWater";
    static final String CALORIES_CONSUMED = "caloriesConsumed";
    static final String MEALS_NUMBER = "mealsNumber";
    static final String HOURS_SLEEP = "hoursSleep";
    static final String HOURS_EXERCISE = "hoursExercise";

    //Current variables
    private int todayScore;
    private int todayStage;
    private int todayDayOfYear;

    //Database variables
    private int startDate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseHelper = new MyDatabaseHelper(this, null, null, 1);


        //finds start date
        if(databaseHelper.hasData()){
            startDate = databaseHelper.findDateWithID(1);
        }

        //Sets home screen button to Sunday, Monday, Tuesday, etc.
        DateFormat dayFormat = new SimpleDateFormat("EEEE");
        String day = dayFormat.format(Calendar.getInstance().getTime());
        Button startDay = (Button) findViewById(R.id.buttonDayOfWeek);
        startDay.setText(day);



        //gets current day out of 365 (todayDayOfYear)
        String currentMonthString = monthFormat.format(Calendar.getInstance().getTime());
        int currentMonthInt = Integer.valueOf(currentMonthString);
        String currentDayOfMonthString = dateFormat.format(Calendar.getInstance().getTime());
        int currentDayOfMonthInt = Integer.valueOf(currentDayOfMonthString);
        todayDayOfYear = outOf365(currentDayOfMonthInt,currentMonthInt);

        int scoreSum = 0;
        if(databaseHelper.hasData()) {
            for (int i = todayDayOfYear; i >= startDate; i--) {
                scoreSum += databaseHelper.findScoreWithDate(i);
            }
        }
        todayStage = calculateStage(scoreSum);


        //grows tree upon opening
        ImageView treeImage = (ImageView) findViewById(R.id.imageViewTree);
        treeImage.setBackgroundResource(todayStage);


    }


    public void goToQuestions(View v){
        //sends user to questions activity***
        Button buttonSubmit = (Button) findViewById(R.id.buttonSubmit);

        Intent intent = new Intent(this, QuestionsActivity.class);
        startActivity(intent);



        //sets button to either "Grow Tree' or "Plant Tree"
//        if(startDate==todayDayOfYear){
//            buttonSubmit.setText("Plant Tree");
//        }
//        else{
//            buttonSubmit.setText("Grow Tree");
//        }
    }


    public void submit(View v) {
        // sends you back to Main Activity
        // gets score name and value from edittext and uses it to create a new Scores object
        // it adds this element to the database and then reprints the database to show the change

//        EditText steps = (EditText) findViewById(R.id.editTextSteps);
//        EditText water = (EditText) findViewById(R.id.editTextCups);
//        EditText meals = (EditText) findViewById(R.id.editTextMeals);
//        EditText calories = (EditText) findViewById(R.id.editTextCalories);
//        EditText sleep = (EditText) findViewById(R.id.editTextSleep);
//        EditText exercise = (EditText) findViewById(R.id.editTextExercise);
//
//
//        int inputSteps = Integer.parseInt(steps.getText().toString());
//        int inputCupsOfWater = Integer.parseInt(water.getText().toString());
//        int inputMealsEaten = Integer.parseInt(meals.getText().toString());
//        int inputCaloriesConsumed = Integer.parseInt(calories.getText().toString());
//        int inputHoursOfSleep = Integer.parseInt(sleep.getText().toString());
//        int inputHoursOfExercise = Integer.parseInt(exercise.getText().toString());

        int inputSteps = 8000;
        int inputCupsOfWater = 3;
        int inputMealsEaten = 2;
        int inputCaloriesConsumed = 1500;
        int inputHoursOfSleep = 4;
        int inputHoursOfExercise = 0;


        if(inputSteps>=stepsGoal&&inputSteps<stepsGoal*1.5){
            todayScore += 5;
        }
        if(inputSteps>stepsGoal*1.5){
            todayScore += 10;
        }
        if(inputCupsOfWater>=cupsOfWaterGoal&&inputCupsOfWater<cupsOfWaterGoal*1.5){
            todayScore += 5;
        }
        if(inputCupsOfWater>cupsOfWaterGoal*1.5){
            todayScore += 10;
        }
        if(inputMealsEaten>=numberOfMealsGoal&&inputMealsEaten<numberOfMealsGoal*1.5){
            todayScore += 5;
        }
        if(inputMealsEaten>numberOfMealsGoal*1.5){
            todayScore += 10;
        }
        if(inputCaloriesConsumed>=caloriesConsumedGoal&&inputSteps<caloriesConsumedGoal*1.5){
            todayScore += 5;
        }
        if(inputCaloriesConsumed>caloriesConsumedGoal*1.5){
            todayScore += 10;
        }
        if(inputHoursOfSleep>=hoursOfSleepGoal&&inputHoursOfSleep<hoursOfSleepGoal*1.5){
            todayScore += 5;
        }
        if(inputHoursOfSleep>hoursOfSleepGoal*1.5){
            todayScore += 10;
        }
        if(inputHoursOfExercise>=hoursOfExerciseGoal&&inputHoursOfExercise<hoursOfExerciseGoal*1.5){
            todayScore += 5;
        }
        if(inputHoursOfExercise>hoursOfExerciseGoal*1.5){
            todayScore += 10;
        }

        Scores score = new Scores(todayScore, todayDayOfYear);

        databaseHelper.addScore(score);

        //** disables user from going back to questions intent
    }



//    public void removeButtonClicked(View v){
//        // Requires the user to enter the name and score of an entry to delete
//        // If the same name/score combo are in table more than once all entries are
//        // deleted with that combination.
//
//        String nameToRemove = nameEditText.getText().toString();
//        int scoreToRemove = -1;
//
//        // Verify the name field isn't empty
//        if(nameToRemove.length() != 0) {
//
//            try {
//                // Try to convert score field to an int
//                scoreToRemove = Integer.parseInt(scoreEditText.getText().toString());
//
//                // If there is a name, and an int, then call removeScore method
//                databaseHelper.removeScore(nameToRemove, scoreToRemove);
//                // clear the edittext fields
//                nameEditText.setText("");
//                scoreEditText.setText("");
//
//            } catch (Exception e) {
//                // the value in the score edittext wasn't an int
//                Toast.makeText(this, "Invalid score", Toast.LENGTH_SHORT).show();
//            }
//        }
//
//        else {
//            Toast.makeText(this, "Enter a name", Toast.LENGTH_SHORT).show();
//        }
//
//
//        printDB();
//    }

//    public void printDB() {
//        // calls the method that creates a string of all the database elements
//        // sets this string to the text in Textview component
//
//        String dbString = databaseHelper.databasetoString();
//        displayTextView.setText(dbString);
//    }

    public int outOf365 (int dayOutOf31, int month){

        int dayOutOf365 = 0;

        if(month==1){
            dayOutOf31 = dayOutOf365;
        }
        if(month==2){
            dayOutOf31 = 31+dayOutOf365;
        }
        if(month==3){
            dayOutOf31 = 60+dayOutOf365;
        }
        if(month==4){
            dayOutOf31 = 90+dayOutOf365;
        }
        if(month==5){
            dayOutOf31 = 121+dayOutOf365;
        }
        if(month==6){
            dayOutOf31 = 151+dayOutOf365;
        }
        if(month==7){
            dayOutOf31 = 182+dayOutOf365;
        }
        if(month==8){
            dayOutOf31 = 212+dayOutOf365;
        }
        if(month==9){
            dayOutOf31 = 243+dayOutOf365;
        }
        if(month==10){
            dayOutOf31 = 273+dayOutOf365;
        }
        if(month==11){
            dayOutOf31 = 304+dayOutOf365;
        }
        if(month==12){
            dayOutOf31 = 334+dayOutOf365;
        }

        return dayOutOf365;
    }

    public int calculateStage(int scoreSum) {
        if (scoreSum > 780) {
            return R.drawable.stage14;

        }
        if (scoreSum > 720 && scoreSum <= 780) {
            return R.drawable.stage13;

        }
        if (scoreSum > 660 && scoreSum <= 720) {
            return R.drawable.stage12;

        }
        if (scoreSum > 600 && scoreSum <= 660) {
            return R.drawable.stage11;

        }
        if (scoreSum > 540 && scoreSum <= 600) {
            return R.drawable.stage10;

        }
        if (scoreSum > 480 && scoreSum <= 540) {
            return R.drawable.stage9;

        }
        if (scoreSum > 420 && scoreSum <= 480) {
            return R.drawable.stage8;

        }
        if (scoreSum > 360 && scoreSum <= 420) {
            return R.drawable.stage7;

        }
        if (scoreSum > 300 && scoreSum <= 360) {
            return R.drawable.stage6;

        }
        if (scoreSum > 240 && scoreSum <= 300) {
            return R.drawable.stage5;

        }
        if (scoreSum > 180 && scoreSum <= 240) {
            return R.drawable.stage4;

        }
        if (scoreSum > 120 && scoreSum <= 180) {
            return R.drawable.stage3;

        }
        if (scoreSum > 60 && scoreSum <= 120) {
            return R.drawable.stage2;

        }

        return R.drawable.stage1;

    }
}