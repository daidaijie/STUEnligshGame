package com.stugame.main.stuenligshgame.view;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.stugame.main.stuenligshgame.R;
import com.stugame.main.stuenligshgame.adapter.MainViewPaperAdapter;
import com.stugame.main.stuenligshgame.component.floatingActionButton.FloatingActionButton;
import com.stugame.main.stuenligshgame.component.floatingActionButton.FloatingActionsMenu;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private final String TAG = "Debug_" + this.getClass().getName();

    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Bind(R.id.drawerLayout)
    DrawerLayout drawerLayout;

    @Bind(R.id.nav_view)
    NavigationView navigationView;

    @Bind(R.id.containerViewPager)
    ViewPager containerViewPager;

    @Bind(R.id.tabLayout)
    TabLayout tabLayout;

    @Bind(R.id.createQuizAction)
    FloatingActionButton createQuizAction;

    @Bind(R.id.createSurveyAction)
    FloatingActionButton createSurveyAction;

    @Bind(R.id.createQuestionActions)
    FloatingActionsMenu createQuestionActions;

    @Bind(R.id.answerQuestionAction)
    FloatingActionButton answerQuestionAction;

    @Bind(R.id.createAnswerActions)
    FloatingActionsMenu createAnswerActions;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setToolbar();

        setTabAndPager();

    }

    private void setToolbar() {
//      设置toolbar
        toolbar.setTitle(R.string.app_name);

//      让toolbar替代actionBar
        setSupportActionBar(toolbar);

//      添加toolbar drawer的开关
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);

//      给drawerLayout添加监听器为toggle
        drawerLayout.addDrawerListener(toggle);

//      toggle同步状态
        toggle.syncState();

//      设置点击navigationView菜单事件
        if (navigationView != null) {
            navigationView.setNavigationItemSelectedListener(this);
        }
    }

    private void setTabAndPager() {
//        为containerViewPager设置ViewPaperAdapter
        containerViewPager.setAdapter(new MainViewPaperAdapter(getSupportFragmentManager()));

//      设置tabLayout关联containerViewPager
        tabLayout.setupWithViewPager(containerViewPager);

//      修改两个Tab的文字
        tabLayout.getTabAt(0).setText("Create Question");
        tabLayout.getTabAt(1).setText("Answer Question");

        containerViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                createAnswerActions.collapse();
                createQuestionActions.collapse();
            }

            @Override
            public void onPageSelected(int position) {
                Log.d(TAG, "onPageSelected: " + position);
                if (position == 0) {
                    createAnswerActions.collapse();
                    createAnswerActions.setVisibility(View.GONE);
                    createQuestionActions.setVisibility(View.VISIBLE);
                } else if (position == 1) {
                    createQuestionActions.collapse();
                    createQuestionActions.setVisibility(View.GONE);
                    createAnswerActions.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public void onBackPressed() {
        //重写back键方法
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }
        //点击后关闭drawerLayout
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @OnClick({R.id.createQuizAction, R.id.createSurveyAction})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.createQuizAction:
                Toast.makeText(this, "create Quiz", Toast.LENGTH_SHORT).show();
                break;
            case R.id.createSurveyAction:
                Toast.makeText(this, "create Survey", Toast.LENGTH_SHORT).show();
                break;
        }

        createQuestionActions.collapse();
    }

    @OnClick(R.id.answerQuestionAction)
    public void onClick() {
        createAnswerActions.collapse();
    }
}
