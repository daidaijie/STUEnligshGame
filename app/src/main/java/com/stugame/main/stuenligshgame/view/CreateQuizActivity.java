package com.stugame.main.stuenligshgame.view;

import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;

import com.stugame.main.stuenligshgame.R;
import com.stugame.main.stuenligshgame.adapter.CreateQuizViewPaperAdapter;
import com.stugame.main.stuenligshgame.component.floatingActionButton.FloatingActionButton;
import com.stugame.main.stuenligshgame.component.floatingActionButton.FloatingActionsMenu;
import com.stugame.main.stuenligshgame.view.createQuestion.CreateRadioFragment;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CreateQuizActivity extends AppCompatActivity {

    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Bind(R.id.createQuizViewPager)
    ViewPager createQuizViewPager;

    @Bind(R.id.createQuizTabLayout)
    TabLayout createQuizTabLayout;

    @Bind(R.id.addQuizAction)
    FloatingActionButton addQuizAction;

    @Bind(R.id.deleteQuizAction)
    FloatingActionButton deleteQuizAction;

    @Bind(R.id.motifyQuizActionsMenu)
    FloatingActionsMenu motifyQuizActionsMenu;

    CreateQuizViewPaperAdapter createQuizViewPaperAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        }

        setContentView(R.layout.activity_create_quiz);
        ButterKnife.bind(this);

        setToolBar();

        setViewPager();

    }

    private void setViewPager() {
        createQuizViewPaperAdapter = new CreateQuizViewPaperAdapter(getSupportFragmentManager());
        createQuizViewPager.setAdapter(createQuizViewPaperAdapter);
        createQuizTabLayout.setupWithViewPager(createQuizViewPager);
        createQuizTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);

        createQuizTabLayout.getTabAt(0).setText("Question:" + 0);
    }

    private void setToolBar() {
        toolbar.setTitle("Create Quiz");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @OnClick({R.id.addQuizAction, R.id.deleteQuizAction})
    public void onClick(View view) {
        //获取当前位置,不然删除之后不会自动去到当前位置
        int currentItem = createQuizViewPager.getCurrentItem();
        switch (view.getId()) {
            case R.id.addQuizAction:
                //在当前项之后加入
                createQuizViewPaperAdapter.addFragmentItem(CreateRadioFragment.newInstance(), currentItem + 1);
                createQuizViewPager.setCurrentItem(currentItem + 1);
                break;
            case R.id.deleteQuizAction:
                //只能删除剩最后一个
                if (createQuizViewPaperAdapter.getCount() > 1) {
                    createQuizViewPaperAdapter.deleteFragmentItem(currentItem);
                    createQuizViewPager.setCurrentItem(currentItem - 1);
                }
                break;
        }
        //重新设置标题,不知道从当前开始为什么会有bug
        for (int i = 0; i < createQuizViewPaperAdapter.getCount(); i++) {
            createQuizTabLayout.getTabAt(i).setText("Question:" + i);
        }
    }
}
