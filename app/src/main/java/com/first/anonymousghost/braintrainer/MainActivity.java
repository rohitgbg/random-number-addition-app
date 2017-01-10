package com.first.anonymousghost.braintrainer;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button button;

    Button button2;
    Button button3;
    Button button4;
    Button button5;

    TextView sumText;
    ArrayList<Integer> answers = new ArrayList<Integer>();
    int locationOfCorrectAns;

    TextView resultText;

    int score =0;

    int numOfQuestions = 0;

    TextView pointsText;
    TextView timerText;
    Button playAgainButton;

    RelativeLayout gameRelativeLayout;

    public void playAgain(View view)
    {
        score = 0;
        numOfQuestions = 0;

        timerText.setText("30s");
        pointsText.setText("0/0");
        resultText.setText("");

        playAgainButton.setVisibility(View.INVISIBLE);
        generateQuestions();


        button2.setEnabled(true);
        button3.setEnabled(true);
        button4.setEnabled(true);
        button5.setEnabled(true);


        new CountDownTimer(30100,1000)
        {


            @Override
            public void onTick(long millisUntilFinished) {


                timerText.setText(String.valueOf(millisUntilFinished/1000) +"s");

            }

            @Override
            public void onFinish() {

                playAgainButton.setVisibility(View.VISIBLE);

                timerText.setText("0s");
                resultText.setText("Your score: " + Integer.toString(score) + "/" + Integer.toString(numOfQuestions) );

                button2.setEnabled(false);
                button3.setEnabled(false);
                button4.setEnabled(false);
                button5.setEnabled(false);


            }
        }.start();




    }


    public void generateQuestions()
    {


        Random rand = new Random();

        int a = rand.nextInt(21);
        int b = rand.nextInt(21);



        sumText.setText(Integer.toString(a) + " + " + Integer.toString(b));

        locationOfCorrectAns = rand.nextInt(4);

        answers.clear();

        int incorrectAns;

        for(int i=0; i<4; i++)
        {
            if(i == locationOfCorrectAns)
            {
                answers.add(a+b);
            }
            else
            {
                incorrectAns = rand.nextInt(42);

                while(incorrectAns == a+b)
                {
                    incorrectAns = rand.nextInt(42);
                }

                answers.add(incorrectAns);


            }


        }


        button2.setText(Integer.toString(answers.get(0)));
        button3.setText(Integer.toString(answers.get(1)));
        button4.setText(Integer.toString(answers.get(2)));
        button5.setText(Integer.toString(answers.get(3)));


    }



    public void choseAns(View view)
    {


       if(view.getTag().toString().equals(Integer.toString(locationOfCorrectAns)))
       {
           //Log.v("Correct", (String) view.getTag() );


           score++;


           resultText.setText("Correct!");


       }
        else
       {
           resultText.setText("Wrong!");

       }


        numOfQuestions++;
        pointsText.setText(Integer.toString(score) + "/" + Integer.toString(numOfQuestions));
        generateQuestions();






    }


    public void startGame(View view)
    {

        button.setVisibility(View.INVISIBLE);

        gameRelativeLayout.setVisibility(RelativeLayout.VISIBLE);

        playAgain(findViewById(R.id.playAgain));


    }





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button) findViewById(R.id.startButton);
        sumText = (TextView)findViewById(R.id.sumText);

        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        button4 = (Button) findViewById(R.id.button4);
        button5 = (Button) findViewById(R.id.button5);

        resultText = (TextView)findViewById(R.id.resultText);
        pointsText = (TextView)findViewById(R.id.pointsText);
        timerText = (TextView) findViewById(R.id.timerText) ;

        playAgainButton = (Button)findViewById(R.id.playAgain);

        gameRelativeLayout =(RelativeLayout)findViewById(R.id.gameRelativeLayout);















    }
}
