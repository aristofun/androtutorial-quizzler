package com.butlitsky.quizzler;

/**
 * (c) MB since 21.01.18.
 */

public class Question {
    private int mQuestionId;
    private boolean mIsitTrue;

    public Question(int questionId, boolean isAnswerTrue) {
        mQuestionId = questionId;
        mIsitTrue = isAnswerTrue;
    }

    public void setQuestionId(int questionId) {
        mQuestionId = questionId;
    }

    public int getQuestionId() {
        return mQuestionId;
    }

    public boolean isIsitTrue() {
        return mIsitTrue;
    }

    public void setIsitTrue(boolean isitTrue) {
        mIsitTrue = isitTrue;
    }
}
