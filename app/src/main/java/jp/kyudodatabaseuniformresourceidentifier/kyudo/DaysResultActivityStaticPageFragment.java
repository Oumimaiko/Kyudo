package jp.kyudodatabaseuniformresourceidentifier.kyudo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class DaysResultActivityStaticPageFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View layout = inflater.inflate(R.layout.fragment_days_result_static,container,false);

        //TextView lastName = (TextView) layout.findViewById(R.id.lastname);
        //lastName.setText(mLast);

        //TextView firstName = (TextView)layout.findViewById(R.id.firstname);
        //firstName.setText(mFirst);

        return layout;
    }
}