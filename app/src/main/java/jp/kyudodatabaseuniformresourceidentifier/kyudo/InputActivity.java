package jp.kyudodatabaseuniformresourceidentifier.kyudo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;

public class InputActivity extends AppCompatActivity{

    public final static String EXTRA_TARGET = "jp.kyudodatabaseuniformresourceidentifier.kyudo.TARGET";

    //メンバクラスの設定
    private boolean mHayaFlag = false;
    private boolean mOtoyaFlag = false;
    private Button mHaya;
    private Button mOtoya;

    private TextView mHayaDot;
    private TextView mOtoyaDot;

    private Target mTarget;

    private float mHayaPositionX;
    private float mHayaPositionY;
    private float mOtoyaPositionX;
    private float mOtoyaPositionY;

    private int mTargetId;

    private Realm mRealm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);

        mRealm = Realm.getDefaultInstance();

        Intent intent = getIntent();
        mTargetId = intent.getIntExtra(DaysResultActivityVisualPageFragment.EXTRA_TARGET, -1);

        ImageView iv = (ImageView)findViewById(R.id.mato);
        mHayaDot = (TextView) findViewById(R.id.hayaDot);
        mOtoyaDot = (TextView) findViewById(R.id.otoyaDot);

        int targetIdEX;

        /*
        if(mTargetId == 0){
            targetIdEX = mTargetId + 1000;
        } else {
            targetIdEX = mTargetId * 1000;
        }
        */

        mTarget = mRealm.where(Target.class).equalTo("id",mTargetId).findFirst();
        mRealm.close();
        if(mTarget == null){
            if(mHayaDot == null){
                mHayaPositionX = 0;
                mHayaPositionY = 0;
            } else if (mOtoyaDot == null) {
                mOtoyaPositionX = 0;
                mOtoyaPositionY = 0;
            } else {
                mHayaPositionX = mHayaDot.getLeft();
                mHayaPositionY = mHayaDot.getTop();
                mOtoyaPositionX = mOtoyaDot.getLeft();
                mOtoyaPositionY = mOtoyaDot.getTop();
            }
        } else {
            mHayaDot.setTranslationX(mHayaPositionX);
            mHayaDot.setTranslationY(mHayaPositionY);
            mOtoyaDot.setTranslationX(mOtoyaPositionX);
            mOtoyaDot.setTranslationY(mOtoyaPositionY);
        }

        // 早矢ボタンUIの実装。ボタンを押すと早矢フラグが立つ。乙矢フラグが立っているとへし折る
        mHaya = (Button) findViewById(R.id.Haya);
        mHaya.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mOtoyaFlag){
                    mOtoyaFlag =false;
                }
                if (mHayaDot.getVisibility() == View.INVISIBLE){
                    mHayaDot.setVisibility(View.VISIBLE);
                }
                mHayaFlag = true;
            }
        });

        // 乙矢ボタンUIの実装。ボタンを押すと乙矢フラグが立つ。早矢フラグが立っているとへし折る
        mOtoya = (Button) findViewById(R.id.Otoya);
        mOtoya.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mHayaFlag){
                    mHayaFlag = false;
                }
                if (mOtoyaDot.getVisibility() == View.INVISIBLE){
                    mOtoyaDot.setVisibility(View.VISIBLE);
                }
                mOtoyaFlag = true;
            }
        });

    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        // Realmの起動
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();

        // Realmからターゲットクラスを全て抜き取る
        RealmResults<Target> targetRealmResults = realm.where(Target.class).findAll();

        if (mTarget == null){
            // 新規作成の場合
            mTarget = new Target();

            int identifier;
            //二十番号対策やで
            int identifierEX;
            if (targetRealmResults.max("id") != null){
                //identifierEX = mTargetId * 1000;

                identifier = targetRealmResults.max("id").intValue() + 1; //+identifierEX;
            } else {
                identifier = 0;
            }
            mTarget.setId(identifier);
        }

        float eventPositionX = event.getX() - 100;
        float eventPositionY = event.getY() - 200;

        // 早矢フラグが立っているときの処理。早矢乙矢フラグは両立しません
        if (mHayaFlag){
            float Dotleft = mHayaDot.getLeft();
            float DotTop = mHayaDot.getTop();

            float positionX = eventPositionX - Dotleft;
            float positionY = eventPositionY - DotTop;

            mHayaDot.setTranslationX(positionX);
            mHayaDot.setTranslationY(positionY);
            mHayaFlag = false;

            mTarget.setHayaPositionX(positionX);
            mTarget.setHayaPositionY(positionY);

        }

        // 乙矢フラグが立っているときの処理
        if(mOtoyaFlag){
            float Dotleft = mOtoyaDot.getLeft();
            float DotTop = mOtoyaDot.getTop();

            float positionX = eventPositionX - Dotleft;
            float positionY = eventPositionY - DotTop;

            mOtoyaDot.setTranslationY(positionY);
            mOtoyaDot.setTranslationX(positionX);
            mOtoyaFlag = false;

            mTarget.setOtoyaPositionX(positionX);
            mTarget.setHayaPositionY(positionY);
        }

        realm.copyToRealmOrUpdate(mTarget);
        realm.commitTransaction();

        // realmの処理
        realm.close();

        Log.d("TouchEvent", "X:" + eventPositionX + ",Y:" + eventPositionY);

        return true;

    }

}
