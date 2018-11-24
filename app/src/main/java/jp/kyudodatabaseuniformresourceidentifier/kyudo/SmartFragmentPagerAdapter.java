package jp.kyudodatabaseuniformresourceidentifier.kyudo;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.SparseArray;
import android.view.ViewGroup;

public abstract class SmartFragmentPagerAdapter extends FragmentPagerAdapter {
    // 各ページのFragmentのキャッシュ
    private final SparseArray<Fragment> cachedFragments = new SparseArray<Fragment>();

    public SmartFragmentPagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Fragment fragment = (Fragment) super.instantiateItem(container, position);
        cachedFragments.put(position, fragment);
        return fragment;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        cachedFragments.remove(position);
        super.destroyItem(container, position, object);
    }

    public Fragment getCachedFragmentAt(int position) {
        return cachedFragments.get(position);
    }
}