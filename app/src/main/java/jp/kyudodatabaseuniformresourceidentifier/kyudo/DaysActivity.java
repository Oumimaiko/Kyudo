package jp.kyudodatabaseuniformresourceidentifier.kyudo;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;
import io.realm.Sort;

public class DaysActivity extends AppCompatActivity {

    private Realm mRealm;
    private RealmChangeListener mRealmChangeListener = new RealmChangeListener() {
        @Override
        public void onChange(Object o) {
            reloadListView();
        }
    };

    private ListView mListView;
    private DaysAdapter mDaysAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_days);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        mRealm = Realm.getDefaultInstance();
        mRealm.addChangeListener(mRealmChangeListener);

        mDaysAdapter = new DaysAdapter(DaysActivity.this);
        mListView = (ListView) findViewById(R.id.daysview);


        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //入力・編集画面
            }
        });

        mListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                //タスクの削除
                return true;
            }
        });

        reloadListView();

    }

    private void reloadListView() {
        RealmResults<Days> datesRealmResults = mRealm.where(Days.class).findAll().sort("date", Sort.DESCENDING);

        mDaysAdapter.setDateList(mRealm.copyFromRealm(datesRealmResults));
        mListView.setAdapter(mDaysAdapter);
        mDaysAdapter.notifyDataSetChanged();

    }
}
