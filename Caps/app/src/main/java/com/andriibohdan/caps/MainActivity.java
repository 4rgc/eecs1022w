//Student name: Andrii Bohdan

//This lab was done individually

package com.andriibohdan.caps;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private Game model;
    private String curAnswer;
    private String curQuestion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initialize the model and enter the first question
        model = new Game();
        nextQuestion();
    }

    private void nextQuestion() {
        //Get the instances of on-screen elements
        EditText answerTb = findViewById(R.id.answerTb);
        TextView qNumberLb = findViewById(R.id.qNumberLb);
        TextView scoreLb = findViewById(R.id.scoreLb);
        TextView questionLb = findViewById(R.id.questionLb);

        //Reset answer box
        answerTb.setText(R.string.empty_string);

        //Get a new question-answer pair and update the screen
        String[] res = model.qa().split("\n");
        curQuestion = res[0];
        questionLb.setText(curQuestion);
        curAnswer = res[1];

        //Update the score and question number
        String scoreOut = String.format(getString(R.string.scoreLb_text), model.score);
        String qNumberOut = String.format(getString(R.string.qNumberLb_text), model.getQNumber());
        qNumberLb.setText(qNumberOut);
        scoreLb.setText(scoreOut);
    }

    public void onDoneBtnClick(View v) {
        //Get the instances of on-screen elements
        TextView logLb = findViewById(R.id.logLb);
        EditText answerTb = findViewById(R.id.answerTb);

        //Retrieve the user-entered answer
        String userAnswer = answerTb.getText().toString();

        //Check the answer
        if(userAnswer.toLowerCase().equals(curAnswer.toLowerCase()))
            model.score++;

        //Form the new log message
        String log = String.format(getString(R.string.log_text),
                model.getQNumber(), curQuestion, answerTb.getText(), curAnswer)
                + logLb.getText();

        //Update the log element
        logLb.setText(log);

        //If there already were 10 questions
        if(model.getQNumber() == 10) {
            //Clear and disable the answer textbox
            answerTb.setText(R.string.empty_string);
            answerTb.setEnabled(false);

            //Disable the button
            findViewById(R.id.doneBtn).setEnabled(false);

            //Update the score
            String scoreOut = String.format(getString(R.string.scoreLb_text), model.score);
            ((TextView)findViewById(R.id.scoreLb)).setText(scoreOut);

            //Show the game over text
            ((TextView) findViewById(R.id.qNumberLb)).setText(R.string.qNumberLb_gameoverText);
        }
        else
            //Proceed to the next question
            nextQuestion();
    }
}
