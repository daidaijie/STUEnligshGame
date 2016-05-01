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

    @Override
    public CharSequence getPageTitle(int position) {
        //返回对应的Tab标题
        return "Question:" + position;
    }

    public CreateQuizViewPaperAdapter(FragmentManager fm) {
        //初始化的时候创建fragmentArrayList，并且加入第一个fragment的实例
        super(fm);
        fragmentArrayList = new ArrayList<>();
        fragmentArrayList.add(CreateRadioFragment.newInstance());
    }

    /**
     * 在最后一个位置添加Fragment
     *
     * @param fragment 要添加的fragment
     */
    public void addFragmentItem(Fragment fragment) {
        fragmentArrayList.add(fragment);
        this.notifyDataSetChanged();
    }

    /**
     * 在指定一个位置添加Fragment
     *
     * @param fragment 要添加的fragment
     * @param position 要添加的fragment位置
     */
    public void addFragmentItem(Fragment fragment, int position) {
        fragmentArrayList.add(position, fragment);
        this.notifyDataSetChanged();
    }

    /**
     * 删除指定位置的fragment
     *
     * @param position 要删除的fragment位置
     */
    public void deleteFragmentItem(int position) {
        fragmentArrayList.remove(position);
        this.notifyDataSetChanged();
    }

    /**
     * 从fragmentArrayList中取出获取对应的fragment
     *
     * @param position fragment位置
     * @return
     */
    @Override
    public Fragment getItem(int position) {
        return fragmentArrayList.get(position);
    }

    /**
     * 获取ViewPager的数量
     * @return fragmentArrayList的size
     */
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
