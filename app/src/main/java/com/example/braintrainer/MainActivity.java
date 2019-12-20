package com.example.braintrainer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    Button startButton;
    ArrayList<Integer> answers = new ArrayList<Integer>();
    int locationOfCorrectAnswer;
    int score = 0;
    int numberOfQuestions = 0;
    TextView resultTextView;
    TextView pointsTextView;
    TextView sumTextView;
    TextView timerTextView;
    Button button0, button1, button2, button3;
    Button playAgainButton;

    public void start(View view) {
        startButton.setVisibility(View.INVISIBLE);
        showItemsOnStart();
    }

    public void generateQuestion() {
        Random rand = new Random();
        int a = rand.nextInt(21);
        int b = rand.nextInt(21);

        sumTextView.setText(Integer.toString(a) + " + " + Integer.toString(b));

        locationOfCorrectAnswer = rand.nextInt(4);
        int incorrectAnswer;
        answers.clear();

        for (int i = 0; i < 4; i++) {
            if (i == locationOfCorrectAnswer) {
                answers.add(a + b);
            } else {
                incorrectAnswer = rand.nextInt(41);
                while (incorrectAnswer == a + b) {
                    incorrectAnswer = rand.nextInt(41);
                }
                answers.add(incorrectAnswer);
            }
        }
        button0.setText(Integer.toString(answers.get(0)));
        button1.setText(Integer.toString(answers.get(1)));
        button2.setText(Integer.toString(answers.get(2)));
        button3.setText(Integer.toString(answers.get(3)));
    }

    public void chooseAnswer(View view) {
        if (view.getTag().toString().equals(Integer.toString(locationOfCorrectAnswer))) {
            Log.i("correct", "correct");
            score++;
            resultTextView.setText("Correct!");
        } else {
            resultTextView.setText("Wrong!");
        }
//        Log.i("TAG:", view.getTag().toString());
        numberOfQuestions++;
        pointsTextView.setText(Integer.toString(score) + "/" + Integer.toString(numberOfQuestions));

        generateQuestion();

    }

    public void playAgain(View view) {
        score = 0;
        numberOfQuestions = 0;

        timerTextView.setText("30s");
        pointsTextView.setText("0/0");
        resultTextView.setText("");
        playAgainButton.setVisibility(View.INVISIBLE);

        generateQuestion();
        new CountDownTimer(31000, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                timerTextView.setText(String.valueOf(millisUntilFinished / 1000) + "s");
            }

            @Override
            public void onFinish() {
                timerTextView.setText("0s");
                resultTextView.setText("Your score: " + Integer.toString(score) + "/" + Integer.toString(numberOfQuestions));
                playAgainButton.setVisibility(View.VISIBLE);
            }
        }.start();
    }

    public void hideItemsBeforeStart() {
        timerTextView.setVisibility(View.INVISIBLE);
        resultTextView.setVisibility(View.INVISIBLE);
        sumTextView.setVisibility(View.INVISIBLE);
        pointsTextView.setVisibility(View.INVISIBLE);
        button0.setVisibility(View.INVISIBLE);
        button1.setVisibility(View.INVISIBLE);
        button2.setVisibility(View.INVISIBLE);
        button3.setVisibility(View.INVISIBLE);
    }

    public void showItemsOnStart() {
        timerTextView.setVisibility(View.VISIBLE);
        resultTextView.setVisibility(View.VISIBLE);
        sumTextView.setVisibility(View.VISIBLE);
        pointsTextView.setVisibility(View.VISIBLE);
        button0.setVisibility(View.VISIBLE);
        button1.setVisibility(View.VISIBLE);
        button2.setVisibility(View.VISIBLE);
        button3.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startButton = (Button)findViewById(R.id.startButton);
        sumTextView = (TextView)findViewById(R.id.sumTextView);
        pointsTextView = (TextView)findViewById(R.id.pointsTextView);
        resultTextView = (TextView) findViewById(R.id.resultTextView);
        timerTextView  = (TextView) findViewById(R.id.timerTextView);
        button0 = (Button)findViewById(R.id.answer0);
        button1 = (Button)findViewById(R.id.answer1);
        button2 = (Button)findViewById(R.id.answer2);
        button3 = (Button)findViewById(R.id.answer3);
        playAgainButton = (Button) findViewById(R.id.playAgainButton);

        hideItemsBeforeStart();
        playAgain(playAgainButton);
    }
}
