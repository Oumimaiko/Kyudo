package jp.kyudodatabaseuniformresourceidentifier.kyudo;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class DaysResultActivity extends FragmentActivity implements ViewPager.OnPageChangeListener{
    //private Toolbar toolbar;

    private ViewPager mPager;
    private int currentPage;

    private Button mButtonNext;
    private Button mButtonBack;

    private TabLayout mTabLayout;

    DaysPagerAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daysresult);
        //setViews();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DaysResultActivity.this,InputActivity.class);
                startActivity(intent);
            }
        });



        mTabLayout = findViewById(R.id.tabLayout);
        mTabLayout.addTab(mTabLayout.newTab().setText("画像"));
        mTabLayout.addTab(mTabLayout.newTab().setText("統計"));


        mPager = (ViewPager) findViewById(R.id.viewPager);
        mAdapter = new DaysPagerAdapter(getSupportFragmentManager());
        mPager.setAdapter(mAdapter);

        mPager.addOnPageChangeListener(this);

        //オートマチック方式: これだけで両方syncする
        mTabLayout.setupWithViewPager(mPager);


        currentPage = 0;

        /*
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
        */
    }

    public void onClickStatic(){
        currentPage++;
        mPager.setCurrentItem(currentPage);
    }

    public void onClickVisual(){
        currentPage--;
        mPager.setCurrentItem(currentPage);
    }

    @Override
    public void onPageScrolled(int i, float v, int i1) { }

    @Override
    public void onPageSelected(int position) {
        Log.d("MainActivity", "onPageSelected() position="+position);
    }

    @Override
    public void onPageScrollStateChanged(int i) { }
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



/*


    <LinearLayout
        android:id="@+id/linearLayout"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:gravity="bottom"
        android:orientation="horizontal"
        app:layout_constraintBottom_toBottomOf="@+id/viewPager">

        <Button
            android:id="@+id/back"
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            android:layout_margin="0dp"
            android:layout_weight="1"
            android:padding="0dp"
            android:text="統計" />

        <Button
            android:id="@+id/next"
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            android:layout_margin="0dp"
            android:layout_weight="1"
            android:padding="0dp"
            android:text="画像" />

    </LinearLayout>

 */
