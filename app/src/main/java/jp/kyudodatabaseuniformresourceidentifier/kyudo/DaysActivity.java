package jp.kyudodatabaseuniformresourceidentifier.kyudo;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.Date;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;
import io.realm.Sort;

public class DaysActivity extends AppCompatActivity {

    public final static String EXTRA_DAYS = "jp.kyudodatabaseuniformresourceidentifier.kyudo.DAYS";

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
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

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
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                //入力・編集画面
                Days days = (Days) parent.getAdapter().getItem(position);

                Intent intent = new Intent(DaysActivity.this, DaysResultActivity.class);
                intent.putExtra(EXTRA_DAYS, days.getId());

                startActivity(intent);

            }
        });

        mListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View v, int position, long id) {
                //タスクの削除
                final Days days = (Days) parent.getAdapter().getItem(position);

                AlertDialog.Builder builder = new AlertDialog.Builder(DaysActivity.this);

                builder.setTitle("削除");
                builder.setMessage(days.getDate() + "を削除しますか");
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        RealmResults<Days> results = mRealm.where(Days.class).equalTo("id", days.getId()).findAll();

                        mRealm.beginTransaction();
                        results.deleteAllFromRealm();
                        mRealm.commitTransaction();

                        reloadListView();
                        }
                });
                builder.setNegativeButton("CANCELL", null);

                AlertDialog dialog = builder.create();
                dialog.show();

                return true;

            }

        });

        addTaskforTest();

        reloadListView();

    }

    private void reloadListView() {
        RealmResults<Days> datesRealmResults = mRealm.where(Days.class).findAll().sort("date", Sort.DESCENDING);

        mDaysAdapter.setDateList(mRealm.copyFromRealm(datesRealmResults));
        mListView.setAdapter(mDaysAdapter);
        mDaysAdapter.notifyDataSetChanged();

    }

    @Override
    protected void onDestroy(){
        super.onDestroy();

        mRealm.close();
    }

    private void addTaskforTest() {
        Days test = new Days();
        test.setTotalArrows(10);
        test.setHitArrows(2);
        test.setDate(new Date());
        test.setId(0);
        mRealm.beginTransaction();
        mRealm.copyToRealmOrUpdate(test);
        mRealm.commitTransaction();
    }

}
