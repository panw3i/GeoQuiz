package activitytest.example.com.geoquiz;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CheatActivity extends AppCompatActivity {

    private Button show_answer_button;
    private TextView answer_text_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_cheat);

        show_answer_button = (Button) findViewById(R.id.show_answer_button);
        answer_text_view = (TextView) findViewById(R.id.answer_text_view);

        final boolean result = getIntent().getBooleanExtra("result", false);

        show_answer_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (result){
                    answer_text_view.setText("正确");
                } else {
                    answer_text_view.setText("错误");
                }

                setResultShownResult(true);
            }
        });

    }

    private void setResultShownResult(boolean isAnswerShown) {
        Intent data = new Intent();
        data.putExtra("show",isAnswerShown);
        setResult(RESULT_OK,data);

    }

    // 创建静态方法用于封装一个Intent
    public static Intent newIntent(Context context, boolean answerIsTrue){
        Intent intent = new Intent(context,CheatActivity.class);
        intent.putExtra("result",answerIsTrue);

        return intent;
    }
}
