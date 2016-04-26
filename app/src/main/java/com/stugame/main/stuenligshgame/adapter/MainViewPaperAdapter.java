package com.stugame.main.stuenligshgame.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.stugame.main.stuenligshgame.view.AnswerQuestionFragment;
import com.stugame.main.stuenligshgame.view.CreateQuestionFragment;

/**
 * Created by daidaijie on 2016/4/26.
 */
public class MainViewPaperAdapter extends FragmentPagerAdapter {

    public MainViewPaperAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return CreateQuestionFragment.newInstance();
        } else if (position == 1) {
            return AnswerQuestionFragment.newInstance();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 2;
    }
}
