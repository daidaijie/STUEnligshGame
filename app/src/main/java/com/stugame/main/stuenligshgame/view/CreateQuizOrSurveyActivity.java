package com.stugame.main.stuenligshgame.view;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.stugame.main.stuenligshgame.R;

/**
 * Created by xfzhang on 2016/4/27.
 */
public class CreateQuizOrSurveyActivity extends Activity
{
    //create a quiz按钮
    private Button createQuiz;
    //create a survey按钮
    private  Button createSurvey;
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_layout);
        createQuiz = (Button) findViewById(R.id.create_quiz);
        //点击createQuiz按钮跳转到创建quiz界面
        createQuiz.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {


            }
        });

        createSurvey = (Button) findViewById(R.id.create_survey);
        //点击createSurvey按钮跳转到创建survey界面
        createSurvey.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

            }
        });
    }
}
