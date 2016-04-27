package com.stugame.main.stuenligshgame.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.stugame.main.stuenligshgame.R;
import com.stugame.main.stuenligshgame.adapter.HistoryQuestionRecyclerAdapter;
import com.stugame.main.stuenligshgame.component.floatingActionButton.FloatingActionButton;
import com.stugame.main.stuenligshgame.component.floatingActionButton.FloatingActionsMenu;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CreateQuestionFragment extends Fragment {

    HistoryQuestionRecyclerAdapter myAdapter;

    @Bind(R.id.historyQuestionRecyclerView)
    RecyclerView historyQuestionRecyclerView;
    @Bind(R.id.createQuizAction)
    FloatingActionButton createQuizAction;
    @Bind(R.id.createSurveyAction)
    FloatingActionButton createSurveyAction;
    @Bind(R.id.multiple_actions)
    FloatingActionsMenu multipleActions;

    public CreateQuestionFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_create_question, container, false);

        ButterKnife.bind(this, rootView);

        //使用线性布局管理器
        historyQuestionRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        //绑定监听器
        myAdapter = new HistoryQuestionRecyclerAdapter(getActivity());
        historyQuestionRecyclerView.setAdapter(myAdapter);

        //设置Item点击事件
        myAdapter.setmOnItemClickLitener(new HistoryQuestionRecyclerAdapter.OnItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(getActivity(), "Item " + position + " is on click", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemLongClick(View view, int position) {
                Toast.makeText(getActivity(), "Item " + position + " is  on long click", Toast.LENGTH_SHORT).show();
            }
        });

        return rootView;
    }

    public static CreateQuestionFragment newInstance() {
        CreateQuestionFragment createQuestionFragment = new CreateQuestionFragment();
        return createQuestionFragment;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.createQuizAction, R.id.createSurveyAction})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.createQuizAction:
                Toast.makeText(getActivity(), "create Quiz", Toast.LENGTH_SHORT).show();
                break;
            case R.id.createSurveyAction:
                Toast.makeText(getActivity(), "create Survey", Toast.LENGTH_SHORT).show();
                break;
        }

        multipleActions.collapse();
    }
}
