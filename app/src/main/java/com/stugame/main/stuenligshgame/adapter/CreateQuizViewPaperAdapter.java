package com.stugame.main.stuenligshgame.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;

import com.stugame.main.stuenligshgame.view.AnswerQuestionFragment;
import com.stugame.main.stuenligshgame.view.CreateQuestionFragment;
import com.stugame.main.stuenligshgame.view.createQuestion.CreateRadioFragment;

import java.util.ArrayList;

/**
 * Created by daidaijie on 2016/4/26.
 * FragmentStatePagerAdapter不缓存Fragment，用于实现动态加载
 */
public class CreateQuizViewPaperAdapter extends FragmentStatePagerAdapter {

    //用来保存Fragment
    private ArrayList<Fragment> fragmentArrayList;

    public CreateQuizViewPaperAdapter(FragmentManager fm) {
        super(fm);
        fragmentArrayList = new ArrayList<>();
        fragmentArrayList.add(CreateRadioFragment.newInstance());
    }

    public void addFragmentItem(Fragment fragment){
        fragmentArrayList.add(fragment);
        this.notifyDataSetChanged();
    }

    public void addFragmentItem(Fragment fragment, int position) {
        fragmentArrayList.add(position,fragment);
        this.notifyDataSetChanged();
    }

    public void deleteFragmentItem(int position) {
        fragmentArrayList.remove(position);
        this.notifyDataSetChanged();
    }


    @Override
    public Fragment getItem(int position) {
        return fragmentArrayList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentArrayList.size();
    }

    /**
     * Override the adapter method getItemPosition (shown below). When we call mAdapter.notifyDataSetChanged();
     *
     * @param object
     * @return
     */
    @Override
    public int getItemPosition(Object object) {
        return PagerAdapter.POSITION_NONE;
    }
}
