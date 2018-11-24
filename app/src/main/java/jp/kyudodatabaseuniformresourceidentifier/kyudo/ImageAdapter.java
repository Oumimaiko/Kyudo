package jp.kyudodatabaseuniformresourceidentifier.kyudo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import io.realm.Realm;
import io.realm.annotations.PrimaryKey;

public class ImageAdapter extends BaseAdapter {
    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private List<Target> mTargetList;

    private Realm mRealm = Realm.getDefaultInstance();

    //コンストラクター コンテクストを作ってレイアウトインフレーターに渡す
    public ImageAdapter(Context c){
        mContext = c;
        mLayoutInflater = LayoutInflater.from(c);
    }

    //フラグメントからターゲット.classのリストを受け取る
    public void setTargetList(List<Target> targetList){
        mTargetList = targetList;
    }

    //レイアウトUI保持用のクラス
    /*
    private static class ViewHolder{
        public ImageView imageView;
        public TextView HayaText;
        public TextView OtoyaText;
    }
    */

    public int getCount(){
        return mTargetList.size();
    }

    public Object getItem(int position){
        return mTargetList.get(position);
    }

    public long getItemId(int position){
        return mTargetList.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){

        //ViewHolder viewHolder;
        if (convertView == null){
            convertView = mLayoutInflater.inflate(R.layout.grid_item_target,null);
        }
        TextView hayaText = (TextView) convertView.findViewById(R.id.hayaDot);
        TextView otoyaText = (TextView) convertView.findViewById(R.id.otoyaDot);

        if (hayaText != null){
            hayaText.setTranslationX(mTargetList.get(position).getHayaPositionX());
            hayaText.setTranslationY(mTargetList.get(position).getHayaPositionY());
        }

        if (otoyaText != null) {
            otoyaText.setTranslationX(mTargetList.get(position).getOtoyaPositionX());
            otoyaText.setTranslationY(mTargetList.get(position).getOtoyaPositionY());
        }

        return convertView;
    }

}


/*
        //ViewHolder viewHolder;
        if (convertView == null){
            convertView = mLayoutInflater.inflate(R.layout.grid_item_target,null);
            viewHolder = new ViewHolder();
            viewHolder.HayaText = (TextView)convertView.findViewById(R.id.hayaDot);
            viewHolder.OtoyaText = (TextView)convertView.findViewById(R.id.otoyaDot);

            viewHolder.HayaText.setTranslationX(mTargetList.get(position).getHayaPositionX());
            viewHolder.HayaText.setTranslationY(mTargetList.get(position).getHayaPositionY());

            viewHolder.OtoyaText.setTranslationX(mTargetList.get(position).getOtoyaPositionX());
            viewHolder.OtoyaText.setTranslationY(mTargetList.get(position).getOtoyaPositionY());
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder)convertView.getTag();
        }
 */
