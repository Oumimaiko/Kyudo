package jp.kyudodatabaseuniformresourceidentifier.kyudo;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class VisualResultPagerAdapter extends FragmentPagerAdapter{
    public VisualResultPagerAdapter(FragmentManager fm){
        super(fm);
    }

    @Override
    public Fragment getItem(int position){
        switch (position){
            case 0:
                return VisualResult.newInstance(android.R.color.holo_blue_bright);
            case 1:
                return VisualResult.newInstance(android.R.color.holo_green_light);
            case 2:
                return VisualResult.newInstance(android.R.color.holo_red_dark);
        }
        return null;
    }

    @Override
    public int getCount(){
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position){
        return "ページ" + (position + 1);
    }
}
