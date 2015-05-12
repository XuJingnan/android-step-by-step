package com.xujingnan.geoquiz;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by xujingnan on 15-5-11.
 */
public class CheatActivity extends Activity {

    public static final String EXTRA_ANSWER_IS_TRUE = "com.xujingnan.geoquiz.answer_is_true";
    public static final String EXTRA_ANSWER_SHOWN = "com.xujingnan.geoquiz.answer_shown";
    private static final String IS_CHEAT = "is_cheat";
    private static String TAG = "CheatActivity";

    private boolean mAnswerIsTrue;
    private boolean mIsCheat = false;

    private TextView mAnswerTextView;
    private Button mShowAnswer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);

        mAnswerIsTrue = getIntent().getBooleanExtra(EXTRA_ANSWER_IS_TRUE, false);
        if (savedInstanceState != null) {
            mIsCheat = savedInstanceState.getBoolean(IS_CHEAT, false);
        }

        mAnswerTextView = (TextView) findViewById(R.id.answerTextView);
        mShowAnswer = (Button) findViewById(R.id.showAnswerButton);
        mShowAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mAnswerIsTrue) {
                    mAnswerTextView.setText(R.string.true_button);
                } else {
                    mAnswerTextView.setText(R.string.false_button);
                }
                mIsCheat = true;
                setAnswerShownResult(1);
            }
        });
        if (mIsCheat) {
            setAnswerShownResult(1);
        } else {
            setAnswerShownResult(-1);
        }
    }

    private void setAnswerShownResult(int isAnswerShown) {
        Intent data = new Intent();
        data.putExtra(EXTRA_ANSWER_SHOWN, isAnswerShown);
        setResult(RESULT_OK, data);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(IS_CHEAT, mIsCheat);
    }
}
