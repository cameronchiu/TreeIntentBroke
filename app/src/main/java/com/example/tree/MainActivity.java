package com.example.tree;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
    private int stepsGoal;
    private int cupsOfWaterGoal;
    private int caloriesConsumedGoal;
    private int numberOfMealsGoal;
    private int hoursOfSleepGoal;
    private int hoursOfExerciseGoal;

    //Current variables
    private int todayScore;
    private int todayStage;
    private int todayDayOfYear;

    //New
    private int score; //(score at a given date)


    //Database variables
    private int lastDayStage;
    private int startDate;
    private int lastDayScore;

    private int[] stages = {R.drawable.stage1,R.drawable.stage2,R.drawable.stage3,R.drawable.stage4,R.drawable.stage5,R.drawable.stage6,
            R.drawable.stage7,R.drawable.stage8,R.drawable.stage9,R.drawable.stage10,R.drawable.stage11,R.drawable.stage13,R.drawable.stage14};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseHelper = new MyDatabaseHelper(this, null, null, 1);



        //finds start date
        if(databaseHelper.hasData()){
            startDate = databaseHelper.findDateWithScore(0);
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
        lastDayScore = databaseHelper.findScoreWithDate(todayDayOfYear-1);

        //updates tree image
        if(lastDayScore >= 45){
            //grow two stages

        }
        if(lastDayScore >=30&&lastDayScore<45){
            //grow one stage
        }
        else{
            //doesn't grow any stages
        }







    }


    public void goToQuestions(View v){
        //sends user to questions activity***

        //sets button to either "Grow Tree' or "Plant Tree"
        Button submit = (Button) findViewById(R.id.buttonSubmit);
        if(startDate==todayDayOfYear){
            submit.setText("Plant Tree");
        }
        else{
            submit.setText("Grow Tree");
        }

    }





    public void submit(View v) {
        // gets score name and value from edittext and uses it to create a new Scores object
        // it adds this element to the database and then reprints the database to show the change

        EditText steps = (EditText) findViewById(R.id.editTextSteps);
        EditText water = (EditText) findViewById(R.id.editTextCups);
        EditText meals = (EditText) findViewById(R.id.editTextMeals);
        EditText calories = (EditText) findViewById(R.id.editTextCalories);
        EditText sleep = (EditText) findViewById(R.id.editTextSleep);
        EditText exercise = (EditText) findViewById(R.id.editTextExercise);


        int inputSteps = Integer.parseInt(steps.getText().toString());;
        int inputCupsOfWater = Integer.parseInt(water.getText().toString());;
        int inputMealsEaten = Integer.parseInt(meals.getText().toString());;
        int inputCaloriesConsumed = Integer.parseInt(calories.getText().toString());;
        int inputHoursOfSleep = Integer.parseInt(sleep.getText().toString());;
        int inputHoursOfExercise = Integer.parseInt(exercise.getText().toString());;


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
}
