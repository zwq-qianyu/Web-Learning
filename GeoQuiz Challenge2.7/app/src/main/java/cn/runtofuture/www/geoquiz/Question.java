package cn.runtofuture.www.geoquiz;

public class Question {
    private int mTextResid;
    private boolean mAnswerTrue;

    public int getTextResid() {
        return mTextResid;
    }

    public void setTextResid(int textResid) {
        mTextResid = textResid;
    }

    public boolean isAnswerTrue() {
        return mAnswerTrue;
    }

    public void setAnswerTrue(boolean answerTrue) {
        mAnswerTrue = answerTrue;
    }

    public Question(int textResid, boolean answerTrue){
        mTextResid = textResid;
        mAnswerTrue = answerTrue;
    }
}
