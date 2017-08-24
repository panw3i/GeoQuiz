package activitytest.example.com.geoquiz;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button mTrueButton;
    private Button mFalseButton;
    private Button mNext;
    private Button mPre;
    private TextView mQestionView;
    private int mCurrentIndex = 0;
    private Question[] mMQuestionBank;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTrueButton = (Button) findViewById(R.id.true_button);
        mFalseButton = (Button) findViewById(R.id.false_button);
        mNext = (Button) findViewById(R.id.next_button);
        mPre = (Button) findViewById(R.id.pre_button);
        mQestionView = (TextView) findViewById(R.id.question_text_view);


        mMQuestionBank = new Question[]{
                new Question(R.string.question01, true),
                new Question(R.string.question02, true),
                new Question(R.string.question03, true)
        };


        mNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateQuestion();
            }
        });

        mPre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                preQuestion();
            }
        });


        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(true);
            }
        });


        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(false);
            }
        });


    }

    private void preQuestion() {

        if (mCurrentIndex<=0){
            Toast.makeText(this, "前面没有更多题目了", Toast.LENGTH_SHORT).show();
        }else {
            mCurrentIndex = (mCurrentIndex-1)%mMQuestionBank.length;
            int questionID = mMQuestionBank[mCurrentIndex].getTextResId();
            mQestionView.setText(questionID);
        }


    }

    private void checkAnswer(boolean userPressedTrue){


        boolean answerTure = mMQuestionBank[mCurrentIndex].isAnswerTure();
        if (userPressedTrue == answerTure){
            Toast.makeText(this, "回答正确", Toast.LENGTH_SHORT).show();
        } else{
            Toast.makeText(this, "回答错误", Toast.LENGTH_SHORT).show();
        }
    }

    private void updateQuestion() {
        mCurrentIndex = (mCurrentIndex+1)%mMQuestionBank.length;
        int questionID = mMQuestionBank[mCurrentIndex].getTextResId();
        mQestionView.setText(questionID);
    }
}
