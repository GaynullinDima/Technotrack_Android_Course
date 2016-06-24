package ru.mail.track.hw2.ui;

import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import ru.mail.track.hw2.R;
import ru.mail.track.hw2.TechItem;
import ru.mail.track.hw2.TechItemAdapter;

public class TechRecycleViewFragment extends Fragment {

    private static final String TAG = "TechRecycleViewFragment";

    protected RecyclerView mRecyclerView;
    protected TechItemAdapter mAdapter;
    protected RecyclerView.LayoutManager mLayoutManager;
    protected List<TechItem> mDataset;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        initDataset();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_tech_list, container, false);
        rootView.setTag(TAG);

        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.my_recycler_view);

        mLayoutManager = new LinearLayoutManager(getActivity());

        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new TechItemAdapter(getActivity(), mDataset);

        mAdapter.SetOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(View v, int position) {
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                TechPagerFragment techPagerFragment = TechPagerFragment.newInstance(position);
                techPagerFragment.setArguments(getArguments());
                ft.replace(R.id.fragment_container, techPagerFragment, techPagerFragment.getTag());
                ft.addToBackStack(null);
                ft.commit();
            }
        });

        mRecyclerView.setAdapter(mAdapter);

        return rootView;
    }

    private void initDataset() {
        mDataset = getArguments().getParcelableArrayList("technologies");
    }
}