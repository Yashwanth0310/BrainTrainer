package com.example.braintrainer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    Button goButton;
    ArrayList<Integer> answers = new ArrayList<Integer>();
    int locationOfCorrectAnswer;
    TextView resultTextView;
    int score = 0;
    int noOfQuestions = 0;
    TextView scoreTextView;
    TextView sumTextView;
    Button button1;
    Button button2;
    Button button3;
    Button button4;
    Button playAgainButton;
    TextView timerTextView;
    ConstraintLayout gameLayout;
    public void playAgain(View view){
        score = 0;
        noOfQuestions = 0;
        timerTextView.setText("30s");
        scoreTextView.setText(Integer.toString(score) +" / "+ Integer.toString(noOfQuestions));
        newQuestion();
        playAgainButton.setVisibility(View.INVISIBLE);
        resultTextView.setText("");
        new CountDownTimer(50100, 1000){

            @Override
            public void onTick(long millisUntilFinished) {
                timerTextView.setText(String.valueOf(millisUntilFinished/1000) + "s");
            }

            @Override
            public void onFinish() {
                resultTextView.setText("DONE !");
                playAgainButton.setVisibility(View.VISIBLE);
            }
        }.start();
    }
    public void chooseAnswer(View view){
        if(Integer.toString(locationOfCorrectAnswer).equals(view.getTag().toString())){
            resultTextView.setText("Correct :)");
            score++;
        } else {
            resultTextView.setText("Wrong :(");
        }
        noOfQuestions++;
        scoreTextView.setText(Integer.toString(score) +" / "+ Integer.toString(noOfQuestions));
        newQuestion();
    }
    public void start(View view){
        goButton.setVisibility(View.INVISIBLE);
        playAgain(findViewById(R.id.timerTextView));
        gameLayout.setVisibility(View.VISIBLE);
    }
    public void newQuestion(){
        Random random = new Random();
        int a = random.nextInt(21);
        int b = random.nextInt(21);
        sumTextView.setText(Integer.toString(a) + " + " + Integer.toString(b));
        locationOfCorrectAnswer = random.nextInt(4);
        answers.clear();
        for (int i=0 ; i<4 ; i++){
            if(i==locationOfCorrectAnswer){
                answers.add(a+b);
            }else{
                int wrongAnswer = random.nextInt(41);
                while (wrongAnswer == a+b)
                {
                    wrongAnswer = random.nextInt(41);
                }
                answers.add(wrongAnswer);
            }
        }
        button1.setText(Integer.toString(answers.get(0)));
        button2.setText(Integer.toString(answers.get(1)));
        button3.setText(Integer.toString(answers.get(2)));
        button4.setText(Integer.toString(answers.get(3)));
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sumTextView = findViewById(R.id.sumTextView);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);
        playAgainButton = findViewById(R.id.playAgainButton);
        resultTextView = findViewById(R.id.resultTextView);
        scoreTextView = findViewById(R.id.scoreTextView);
        timerTextView = findViewById(R.id.timerTextView);
        goButton = findViewById(R.id.goButton);
        gameLayout = findViewById(R.id.gameLayout);
        goButton.setVisibility(View.VISIBLE);
        gameLayout.setVisibility(View.INVISIBLE);
    }
}