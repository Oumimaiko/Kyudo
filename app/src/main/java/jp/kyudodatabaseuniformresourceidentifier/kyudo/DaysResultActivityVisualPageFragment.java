package jp.kyudodatabaseuniformresourceidentifier.kyudo;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;
import io.realm.Sort;

public class DaysResultActivityVisualPageFragment extends Fragment {

    public final static String EXTRA_TARGET = "jp.kyudodatabaseuniformresourceidentifier.kyudo.TARGET";

    //Realmのメンバクラス化
    private Realm mRealm;
    private RealmChangeListener mRealmChangeListener = new RealmChangeListener() {
        @Override
        public void onChange(Object o) {
            reloadListView();
        }
    };

    //GridViewのメンバクラス化
    private GridView mGridView;
    private ImageAdapter mImageAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View layout = inflater.inflate(R.layout.fragment_visualresult, container, false);

        // Realmの設定
        mRealm = Realm.getDefaultInstance();
        mRealm.addChangeListener(mRealmChangeListener);

        // GridViewの設定
        mImageAdapter = new ImageAdapter(getContext());
        mGridView = (GridView) layout.findViewById(R.id.gridView);

        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                Target target = (Target) parent.getAdapter().getItem(position);

                Intent intent = new Intent(getContext(), InputActivity.class);
                intent.putExtra(EXTRA_TARGET, target.getId());

                startActivity(intent);
            }
        });

        mGridView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long l) {

                final Target target = (Target) parent.getAdapter().getItem(position);

                Log.d("Loglog", parent.getAdapter().getItem(position).toString());

                // ダイアログを表示する
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

                builder.setTitle("削除");
                builder.setMessage("本当に削除しますか");
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        RealmResults<Target> results = mRealm.where(Target.class).equalTo("id", target.getId()).findAll();

                        mRealm.beginTransaction();
                        results.deleteAllFromRealm();
                        mRealm.commitTransaction();

                        reloadListView();
                    }
                });
                builder.setNegativeButton("CANCEL", null);

                AlertDialog dialog = builder.create();
                dialog.show();

                return true;
            }
        });

        reloadListView();
        return layout;
    }

    public void reloadListView(){
        // Realmデータベースから、「全てのデータを取得して新しい日時順に並べた結果」を取得
        RealmResults<Target> targetRealmResults = mRealm.where(Target.class).findAll().sort("id", Sort.DESCENDING);
        // 上記の結果を、TaskList としてセットする
        mImageAdapter.setTargetList(mRealm.copyFromRealm(targetRealmResults));
        // TaskのListView用のアダプタに渡す
        mGridView.setAdapter(mImageAdapter);
        // 表示を更新するために、アダプターにデータが変更されたことを知らせる
        mImageAdapter.notifyDataSetChanged();
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        mRealm.close();
    }
}