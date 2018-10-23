package jp.kyudodatabaseuniformresourceidentifier.kyudo;

import android.os.Bundle;
import android.support.annotation.ColorRes;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

public class VisualResult extends Fragment {
    private final static String BACKGROUND_COLOR = "background_color";

    public static VisualResult newInstance(@ColorRes int IdRes){
        VisualResult visual = new VisualResult();
        Bundle b = new Bundle();
        b.putInt(BACKGROUND_COLOR, IdRes);
        visual.setArguments(b);
        return visual;
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.content_daysresult,null);
        LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.fragment_visualResult);
        linearLayout.setBackgroundResource(getArguments().getInt(BACKGROUND_COLOR));

        return view;

    }

}
