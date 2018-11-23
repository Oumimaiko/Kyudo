package jp.kyudodatabaseuniformresourceidentifier.kyudo;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class DaysResultActivity extends FragmentActivity {
    //private Toolbar toolbar;

    private ViewPager mPager;
    private int currentPage;

    private Button mButtonNext;
    private Button mButtonBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daysresult);
        //setViews();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        
        mPager = (ViewPager) findViewById(R.id.viewPager);
        DaysPagerAdapter adapter = new DaysPagerAdapter(getSupportFragmentManager());

        mPager.setAdapter(adapter);
        currentPage = 0;

        mButtonBack = findViewById(R.id.next);
        mButtonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickStatic();
            }
        });

        mButtonNext = findViewById(R.id.back);
        mButtonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickVisual();
            }
        });
    }

    public void onClickStatic(){
        currentPage++;
        mPager.setCurrentItem(currentPage);
    }

    public void onClickVisual(){
        currentPage--;
        mPager.setCurrentItem(currentPage);
    }

    /*
    protected void setViews() {
        FragmentManager manager = getSupportFragmentManager();
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewPager);
        VisualResultPagerAdapter adapter = new VisualResultPagerAdapter(manager);
        viewPager.setAdapter(adapter);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager);
    }
    */





}
