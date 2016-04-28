package com.stugame.main.stuenligshgame.view;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.stugame.main.stuenligshgame.R;
import com.stugame.main.stuenligshgame.adapter.HistoryAnswerRecyclerAdapter;

import butterknife.Bind;
import butterknife.ButterKnife;

public class AnswerQuestionFragment extends Fragment {

    HistoryAnswerRecyclerAdapter myAdapter;

    @Bind(R.id.historyAnswerRecyclerView)
    RecyclerView historyAnswerRecyclerView;
    @Bind(R.id.historyAnswerRefreshLayout)
    SwipeRefreshLayout historyAnswerRefreshLayout;

    public AnswerQuestionFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_answer_question, container, false);

        ButterKnife.bind(this, rootView);

        //使用线性布局管理器
        historyAnswerRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        //绑定监听器
        myAdapter = new HistoryAnswerRecyclerAdapter(getActivity());
        historyAnswerRecyclerView.setAdapter(myAdapter);

        //设置Item点击事件
        myAdapter.setmOnItemClickLitener(new HistoryAnswerRecyclerAdapter.OnItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(getActivity(), "Answer item " + position + " is on click", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemLongClick(View view, int position) {
                Toast.makeText(getActivity(), "Answer Item " + position + " is  on long click", Toast.LENGTH_SHORT).show();
            }
        });

        //创建下拉刷新监听器
        historyAnswerRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
//                         隐藏刷新条
                        historyAnswerRefreshLayout.setRefreshing(false);
                    }
                }, 2000);
            }
        });


        return rootView;
    }

    public static AnswerQuestionFragment newInstance() {
        AnswerQuestionFragment answerQuestionFragment = new AnswerQuestionFragment();
        return answerQuestionFragment;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
