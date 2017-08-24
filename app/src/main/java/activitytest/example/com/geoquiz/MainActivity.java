package activitytest.example.com.geoquiz;

import android.content.Intent;
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
    private Button mCheatButton;
    private TextView mQestionView;

    private int mCurrentIndex = 0;
    private Question[] mMQuestionBank;
    private static final String KEY_VALUE = "index";



    // 重写父类的 onSaveInstanceState 方法用来保存当前的数据序列
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(KEY_VALUE,mCurrentIndex);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 在旋转屏幕后保持界面不变
        if (savedInstanceState != null){
            mCurrentIndex = savedInstanceState.getInt(KEY_VALUE);
        }


        mTrueButton = (Button) findViewById(R.id.true_button);
        mFalseButton = (Button) findViewById(R.id.false_button);
        mCheatButton = (Button) findViewById(R.id.cheat_button);

        mNext = (Button) findViewById(R.id.next_button);
        mPre = (Button) findViewById(R.id.pre_button);
        mQestionView = (TextView) findViewById(R.id.question_text_view);

        // 数据来源
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

        mCheatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(MainActivity.this,CheatActivity.class);
//                startActivity(intent);
                Question question = mMQuestionBank[mCurrentIndex];
                Boolean result = question.isAnswerTure();
                Intent intent = CheatActivity.newIntent(MainActivity.this, result);
//                startActivity(intent);
                startActivityForResult(intent,1);
            }
        });
        
        

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        
        if (resultCode != RESULT_OK){
            return;
        }
        
        if (requestCode == 1){
            if (data == null){
                return;
            }

            boolean show = data.getBooleanExtra("show", false);
            Toast.makeText(this, "您已经查看过结果了!", Toast.LENGTH_SHORT).show();
        }
        
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
