package it.uniba.di.angekkk.countersteps;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;


public class SetGoalActivity extends AppCompatActivity {
    public static float mSeries = 0f;
    public static float mSeries1 = 0f;
    SharedPreferences sharedpreferences;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setgoal);
         sharedpreferences = getSharedPreferences("GOAL", Context.MODE_PRIVATE);

        final EditText stepGoal = findViewById(R.id.et1);
        final EditText calorieGoal = findViewById(R.id.et2);

        Button setgoal = findViewById(R.id.setgoal);
        Calendar calendar = Calendar.getInstance();
        int hour24hrs = calendar.get(Calendar.HOUR_OF_DAY);
        if(hour24hrs==0){
            sharedpreferences=null;
        }
        if(sharedpreferences!= null && !sharedpreferences.getString("goal","").isEmpty()) {
            Intent myIntent = new Intent(SetGoalActivity.this, MainActivity.class);
            myIntent.putExtra("goal",sharedpreferences.getString("goal",""));
            startActivity(myIntent);
        }
        setgoal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (stepGoal.getText().toString().length() == 0) {
                    stepGoal.setError("Set Steps Goal");
                    return;
                } else if (calorieGoal.getText().toString().length() == 0) {
                    calorieGoal.setError("Set Calorie Goal!");
                    return;
                }

                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.putString("goal", stepGoal.getText().toString());
                editor.commit();
                Intent myIntent = new Intent(SetGoalActivity.this, MainActivity.class);
                myIntent.putExtra("goal",stepGoal.getText().toString());
                startActivity(myIntent);
            }
        });


    }
}
