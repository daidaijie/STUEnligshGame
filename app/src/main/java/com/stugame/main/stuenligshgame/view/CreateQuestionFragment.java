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

import butterknife.Bind;
import butterknife.ButterKnife;

public class CreateQuestionFragment extends Fragment {

    HistoryQuestionRecyclerAdapter myAdapter;

    @Bind(R.id.historyQuestionRecyclerView)
    RecyclerView historyQuestionRecyclerView;

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
}
