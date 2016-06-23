package ru.mail.track.hw2;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.content.Context;

import java.util.List;

import ru.mail.track.hw2.ui.TechItemFragment;

public class TechItemPagerAdapter extends FragmentPagerAdapter {
    private ImageLoader mImageLoader;
    List<TechItem> techItemList;

    public TechItemPagerAdapter(Context context, FragmentManager fm, List<TechItem> techItemList) {
        super(fm);
        this.mImageLoader = new MultipleImageLoader(context, 0);
        this.techItemList = techItemList;
    }

    @Override
    public Fragment getItem(int position) {
        return TechItemFragment.newInstance(mImageLoader, techItemList.get(position));
    }

    @Override
    public int getCount() {
        return techItemList.size();
    }
}
