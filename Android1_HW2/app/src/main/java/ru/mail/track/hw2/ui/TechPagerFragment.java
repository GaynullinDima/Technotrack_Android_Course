package ru.mail.track.hw2.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.List;

import ru.mail.track.hw2.R;
import ru.mail.track.hw2.TechItem;
import ru.mail.track.hw2.TechItemPagerAdapter;

public class TechPagerFragment extends Fragment {

    private static final String TAG = "TechPagerFragment";
    private int mStartPosition;
    private ViewPager mPager;
    protected List<TechItem> mDataset;

    public static TechPagerFragment newInstance(int startPosition) {
        TechPagerFragment fragment = new TechPagerFragment();
        fragment.mStartPosition = startPosition;
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        initDataset();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tech_pager, container, false);
        mPager = (ViewPager) view.findViewById(R.id.pager);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        TechItemPagerAdapter pagerAdapter = new TechItemPagerAdapter(getActivity(), getChildFragmentManager(), mDataset);
        mPager.setAdapter(pagerAdapter);
        if (savedInstanceState == null) {
            mPager.setCurrentItem(mStartPosition);
        }
    }

    private void initDataset() {
        mDataset = getArguments().getParcelableArrayList("technologies");
    }
}
