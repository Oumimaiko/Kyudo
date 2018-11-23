package jp.kyudodatabaseuniformresourceidentifier.kyudo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class DaysAdapter extends BaseAdapter {
    private LayoutInflater mLayoutInflater;
    private List<Days> mDaysList;

    public DaysAdapter(Context context){
        mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void setDateList(List<Days> daysList){
        mDaysList = daysList;
    }

    @Override
    public int getCount() {
        return mDaysList.size();
    }

    @Override
    public Object getItem(int position) {
        return mDaysList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return mDaysList.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = mLayoutInflater.inflate(android.R.layout.simple_list_item_2, null);
        }

        TextView textView1 = (TextView) convertView.findViewById(android.R.id.text1);
        TextView textView2 = (TextView) convertView.findViewById(android.R.id.text2);

        // 後でDateクラスから情報を取得するように変更する
        textView1.setText(mDaysList.get(position).toString());

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.JAPANESE);
        Date date = mDaysList.get(position).getDate();
        textView2.setText(simpleDateFormat.format(date));


        return convertView;

    }
}
