package com.stugame.main.stuenligshgame.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.stugame.main.stuenligshgame.R;

public class AnswerQuestionFragment extends Fragment {

    public AnswerQuestionFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_answer_question, container, false);
    }

    public static AnswerQuestionFragment newInstance() {
        AnswerQuestionFragment answerQuestionFragment = new AnswerQuestionFragment();
        return answerQuestionFragment;
    }
}
