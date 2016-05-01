package com.stugame.main.stuenligshgame.view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
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
import android.view.ViewAnimationUtils;
import android.view.animation.AccelerateDecelerateInterpolator;
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

    //Debug用的TAG
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

    @Bind(R.id.mainAppBarLayout)
    AppBarLayout mainAppBarLayout;


    MainViewPaperAdapter mainViewPaperAdapter;

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
        mainViewPaperAdapter = new MainViewPaperAdapter(getSupportFragmentManager());
        containerViewPager.setAdapter(mainViewPaperAdapter);

//      设置tabLayout关联containerViewPager
        tabLayout.setupWithViewPager(containerViewPager);

//      修改两个Tab的文字
        tabLayout.getTabAt(0).setText("Create Question");
        tabLayout.getTabAt(1).setText("Answer Question");


//      当ViewPager页面改变的时候切换的时候改变状态
        containerViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                //当滑动页面的时候收起FAB菜单
                createAnswerActions.collapse();
                createQuestionActions.collapse();
            }

            @Override
            public void onPageSelected(int position) {
                Log.d(TAG, "onPageSelected: " + position);
                //当切换页面的时候将该页对应的FAB菜单显示出来
                //并将其Tab和ToolBar的颜色进行修改
                if (position == 0) {
                    //切换FAB按钮
                    createAnswerActions.collapse();
                    createAnswerActions.setVisibility(View.GONE);
                    createQuestionActions.setVisibility(View.VISIBLE);

                    //加特技,设置分别为T
                    Animator animator = null;
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//                        圆形展开动画
                        animator = ViewAnimationUtils.createCircularReveal(tabLayout,
                                tabLayout.getWidth() / 4,
                                0,
                                0,
                                tabLayout.getWidth());
                        animator.setInterpolator(new AccelerateDecelerateInterpolator());
                        animator.setDuration(500);
                        animator.start();
                    }
                    Animator animator2 = null;
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        animator2 = ViewAnimationUtils.createCircularReveal(toolbar,
                                toolbar.getWidth() / 4,
                                toolbar.getHeight(),
                                0,
                                toolbar.getWidth());
                        //设置插值器，加速度
                        animator2.setInterpolator(new AccelerateDecelerateInterpolator());
                        animator2.setDuration(500);
                        animator2.start();
                        animator2.addListener(new AnimatorListenerAdapter() {
                            @Override
                            public void onAnimationEnd(Animator animation) {
                                //完成后修改底色
                                mainAppBarLayout.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                                super.onAnimationEnd(animation);
                            }
                        });
                        getWindow().setStatusBarColor(getResources().getColor(R.color.colorPrimaryDark));
                    }
                    tabLayout.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                    toolbar.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                    drawerLayout.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                    navigationView.getHeaderView(0).setBackgroundColor(getResources().getColor(R.color.colorPrimary));


                } else if (position == 1) {
                    //切换FAB按钮
                    createQuestionActions.collapse();
                    createQuestionActions.setVisibility(View.GONE);
                    createAnswerActions.setVisibility(View.VISIBLE);

                    //加特技
                    Animator animator = null;
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        animator = ViewAnimationUtils.createCircularReveal(tabLayout,
                                tabLayout.getWidth() * 3 / 4,
                                0,
                                0,
                                tabLayout.getWidth());
                        animator.setInterpolator(new AccelerateDecelerateInterpolator());
                        animator.setDuration(500);
                        animator.start();
                    }
                    Animator animator2 = null;
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        animator2 = ViewAnimationUtils.createCircularReveal(toolbar,
                                toolbar.getWidth() * 3 / 4,
                                toolbar.getHeight(),
                                0,
                                toolbar.getWidth());
                        animator2.setInterpolator(new AccelerateDecelerateInterpolator());
                        animator2.setDuration(500);
                        animator2.start();
                        animator2.addListener(new AnimatorListenerAdapter() {
                            @Override
                            public void onAnimationEnd(Animator animation) {
                                mainAppBarLayout.setBackgroundColor(getResources().getColor(R.color.colorPrimary2));
                                super.onAnimationEnd(animation);
                            }
                        });
                        getWindow().setStatusBarColor(getResources().getColor(R.color.colorPrimaryDark2));
                    }
                    toolbar.setBackgroundColor(getResources().getColor(R.color.colorPrimary2));
                    tabLayout.setBackgroundColor(getResources().getColor(R.color.colorPrimary2));
                    navigationView.getHeaderView(0).setBackgroundColor(getResources().getColor(R.color.colorPrimary2));

                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public void onBackPressed() {
        //重写back键方法,如果drawerLayout打开则将其关闭,否则进行默认的操作
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
        //这里之后会改回切换界面
        switch (view.getId()) {
            case R.id.createQuizAction:
//                Toast.makeText(this, "create Quiz", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, CreateQuizActivity.class);

                startActivity(intent);

                //假如API大于anderoid5.0，就进行转场动画，否则普通转场
                /*if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(
                            this,
                            createQuestionActions,
                            "createQuizContentTransition"
                    ).toBundle());
                } else {
                    startActivity(intent);
                }*/
                break;
            case R.id.createSurveyAction:
                Toast.makeText(this, "create Survey", Toast.LENGTH_SHORT).show();
                break;
        }
        //点击完之后收起FAB菜单
        createQuestionActions.collapse();
    }

    @OnClick(R.id.answerQuestionAction)
    public void onClick() {
        //这里之后会改回切换界面
        Toast.makeText(this, "answer question", Toast.LENGTH_SHORT).show();

        //点击完之后收起FAB菜单
        createAnswerActions.collapse();
    }
}
