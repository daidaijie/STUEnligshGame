package com.stugame.main.stuenligshgame.view.createQuestion;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.stugame.main.stuenligshgame.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class CreateRadioFragment extends Fragment {


    @Bind(R.id.showQuestionTextView)
    TextView showQuestionTextView;
    @Bind(R.id.editQuestionButton)
    Button editQuestionButton;
    @Bind(R.id.editChoicesRecyclerView)
    RecyclerView editChoicesRecyclerView;

    public CreateRadioFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_create_radio, container, false);

        ButterKnife.bind(this, rootView);
        return rootView;
    }

    public static CreateRadioFragment newInstance() {
        CreateRadioFragment createRadioFragment = new CreateRadioFragment();
        return createRadioFragment;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.showQuestionTextView, R.id.editQuestionButton})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.showQuestionTextView:
                break;
            case R.id.editQuestionButton:
                break;
        }
    }
}
