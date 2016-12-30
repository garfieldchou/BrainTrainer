package com.garfield_chou.braintrainer;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import static java.util.Arrays.asList;

public class MainActivity extends AppCompatActivity {

    TextView timerTextView, scoreTextView, questionTextView, resultTextView;
    GridLayout guessLayout;
    Button playAgain, button, button1, button2, button3;
    int trial, scored, correctIdx;
    Random rand = new Random();

    public void nextQuestion () {

        int num1 = 0;
        int num2 = 0;
        int maxOfOperand = 0;
        int minIndex = 0;
        ArrayList<Integer> choices = new ArrayList<Integer>(asList(0, 0, 0, 0));

        num1 = rand.nextInt(30) + 1;
        num2 = rand.nextInt(30) + 1;
        if (num1 > num2) {
            maxOfOperand = num1;
        } else {
            maxOfOperand = num2;
        }
        correctIdx = rand.nextInt(4);
        Log.i("correctIdx", Integer.toString(correctIdx));
        choices.set(correctIdx, num1 + num2 );
        Log.i("value of correctIdx", Integer.toString(choices.get(correctIdx)));

        for (int i = 0; i < choices.size(); i++) {
            if (0 == choices.get(i)) {
                choices.set(i, rand.nextInt(60-maxOfOperand) + maxOfOperand +1 );
                Log.i("choices set", Integer.toString(choices.get(i)));
            }
        }
        button.setText(Integer.toString(choices.get(0)));
        button1.setText(Integer.toString(choices.get(1)));
        button2.setText(Integer.toString(choices.get(2)));
        button3.setText(Integer.toString(choices.get(3)));
        questionTextView.setText( Integer.toString(num1) + "+" + Integer.toString(num2));

    }

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

        if ( correctIdx == Integer.parseInt(buttonTag)) {
            scored++;
            resultTextView.setText("Correct!");
        } else {
            resultTextView.setText("Wrong!");
        }
        resultTextView.setVisibility(View.VISIBLE);
        scoreTextView.setText(Integer.toString(scored) + "/" + Integer.toString(trial));

        nextQuestion();

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
        button = (Button) findViewById(R.id.button);
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        trial = 0;
        scored = 0;
        nextQuestion();
    }
}
