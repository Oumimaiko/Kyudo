package jp.kyudodatabaseuniformresourceidentifier.kyudo;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.SparseArray;

import java.util.ArrayList;
import java.util.Map;

public class DaysPagerAdapter extends SmartFragmentPagerAdapter {
    public DaysPagerAdapter(FragmentManager fm){
        super(fm);
    }

    private final static int NUM_PAGE = 2;

    @Override
    public Fragment getItem(int position){
        Fragment frag = null;
        switch (position) {
            case 0:
                frag = new DaysResultActivityStaticPageFragment();
                //frag.setArguments(bundle);
                break;
            case 1:
                frag = new DaysResultActivityVisualPageFragment();
                //frag.setArguments(bundle);
                break;
        }
        return frag;
    }

    @Override
    public int getCount(){
        return NUM_PAGE;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (position == 0){
            return "画像";
        } else {
            return "統計";
        }
    }

}
