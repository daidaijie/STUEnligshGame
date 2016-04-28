package com.stugame.main.stuenligshgame.adapter;

import android.app.Activity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.stugame.main.stuenligshgame.R;

/**
 * Created by daidaijie on 2016/4/26.
 */
public class HistoryAnswerRecyclerAdapter extends RecyclerView.Adapter<HistoryAnswerRecyclerAdapter.MyViewHolder> {

    Activity activity;

    public HistoryAnswerRecyclerAdapter(Activity activity) {
        this.activity = activity;
    }

    //设置Item点击回调接口
    public interface OnItemClickLitener {
        void onItemClick(View view, int position);

        void onItemLongClick(View view, int position);
    }

    private OnItemClickLitener mOnItemClickLitener;

    public void setmOnItemClickLitener(OnItemClickLitener mOnItemClickLitener) {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        //创建自己的ViewHolder
        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(activity).inflate(
                R.layout.history_answer_item, parent, false
        ));
        return holder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        holder.historyAnswerInfoTextView.setText("Answer item : " + position);

        if (mOnItemClickLitener != null) {
            //设置点击事件
            holder.historyAnswerCardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int pos = holder.getLayoutPosition();
                    mOnItemClickLitener.onItemClick(holder.itemView, position);
                }
            });
            holder.historyAnswerCardView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    int pos = holder.getLayoutPosition();
                    mOnItemClickLitener.onItemLongClick(holder.itemView, position);
                    return false;
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return 30;
    }

    //ViewHolder用于保存每一个子Item的数据
    class MyViewHolder extends RecyclerView.ViewHolder {

        CardView historyAnswerCardView;
        TextView historyAnswerInfoTextView;

        public MyViewHolder(View view) {
            super(view);
            historyAnswerCardView = (CardView) view.findViewById(R.id.historyAnswerCardView);
            historyAnswerInfoTextView = (TextView) view.findViewById(R.id.historyAnswerInfoTextView);
        }
    }
}
