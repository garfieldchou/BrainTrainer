package com.garfield_chou.braintrainer;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView timerTextView, scoreTextView, questionTextView, resultTextView;
    GridLayout guessLayout;
    Button playAgain;
    int trial, scored;

    public void goClick (View view) {

        Button goButton = (Button) findViewById(R.id.goButton);
        goButton.setVisibility(View.INVISIBLE);

        timerTextView.setVisibility(View.VISIBLE);
        scoreTextView.setVisibility(View.VISIBLE);
        questionTextView.setVisibility(View.VISIBLE);
        guessLayout.setVisibility(View.VISIBLE);

        scoreTextView.setText("0/0");

        new CountDownTimer(30000 + 50, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timerTextView.setText( Integer.toString((int) millisUntilFinished / 1000) + "s");
            }

            @Override
            public void onFinish() {
                timerTextView.setText( "0s");
                playAgain.setVisibility(View.VISIBLE);
                resultTextView.setText("Your score: " + Integer.toString(scored) + "/" +  Integer.toString(trial));
            }
        }.start();

    }

    public void ansClick (View view) {

        String buttonTag = view.getTag().toString();
        Log.i("button", buttonTag + " was clicked!");
        trial++;

        scoreTextView.setText(Integer.toString(scored) + "/" + Integer.toString(trial));

        /*resultTextView.setText("Correct!");
        resultTextView.setVisibility(View.VISIBLE);*/

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timerTextView = (TextView) findViewById(R.id.timerTextView);
        scoreTextView = (TextView) findViewById(R.id.scoreTextView);
        questionTextView = (TextView) findViewById(R.id.questionTextView);
        guessLayout = (GridLayout) findViewById(R.id.guessLayout);
        resultTextView = (TextView) findViewById(R.id.resultTextView);
        playAgain = (Button) findViewById(R.id.playAgain);
        trial = 0;
        scored = 0;
    }
}
