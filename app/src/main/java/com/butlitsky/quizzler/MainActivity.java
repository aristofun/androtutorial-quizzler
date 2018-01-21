package com.butlitsky.quizzler;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
    private static final String TAG = "QuizzlerApp";

    Button mTrueButton, mFalseButton;
    TextView mQtextView, mScore;
    ProgressBar mProgressBar;
    int mIndex;
    int mUserScore = 0;

    private Question[] mQuestionBank = new Question[]{
            new Question(R.string.question_1, true),
            new Question(R.string.question_2, true),
            new Question(R.string.question_3, true),
            new Question(R.string.question_4, true),
            new Question(R.string.question_5, true),
            new Question(R.string.question_6, false),
            new Question(R.string.question_7, true),
            new Question(R.string.question_8, false),
            new Question(R.string.question_9, true),
            new Question(R.string.question_10, true),
            new Question(R.string.question_11, false),
            new Question(R.string.question_12, false),
            new Question(R.string.question_13, true)
    };

    public final int PB_INCREMENT = (int) Math.ceil(100. / mQuestionBank.length);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.butlitsky.quizzler.R.layout.activity_main);

        mTrueButton = (Button) findViewById(com.butlitsky.quizzler.R.id.true_button);
        mFalseButton = (Button) findViewById(com.butlitsky.quizzler.R.id.false_button);
        mScore = (TextView) findViewById(com.butlitsky.quizzler.R.id.score);
        mProgressBar = (ProgressBar) findViewById(com.butlitsky.quizzler.R.id.progress_bar);
        mQtextView = (TextView) findViewById(com.butlitsky.quizzler.R.id.question_text_view);

        mQtextView.setText(mQuestionBank[mIndex].getQuestionId());

        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(true);
                nextQuestion();
            }
        });

        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(false);
                nextQuestion();
            }
        });
    }

    private void nextQuestion() {
        mIndex = (mIndex + 1) % mQuestionBank.length;

        if (mIndex == 0) {
            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setTitle("Финиш! 🏁");
            alert.setCancelable(false);
            alert.setMessage("У тебя " + mUserScore + " oчкофф 🏆");
            alert.setPositiveButton("Close app", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    finish();
                }
            });
            alert.show();
        }

        mQtextView.setText(mQuestionBank[mIndex].getQuestionId());
        mProgressBar.incrementProgressBy(PB_INCREMENT);
        mScore.setText("" + mUserScore + "/" + mQuestionBank.length);
    }

    private void checkAnswer(boolean answer) {
        if (answer == mQuestionBank[mIndex].isIsitTrue()) {
            Toast.makeText(getApplicationContext(), R.string.correct_toast, Toast.LENGTH_SHORT).show();
            mUserScore++;
        } else {
            Toast.makeText(getApplicationContext(), R.string.incorrect_toast, Toast.LENGTH_SHORT).show();
        }
    }
}
