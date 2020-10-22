package org.maktab.beatbox.view.fragment;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.maktab.beatbox.R;
import org.maktab.beatbox.adapter.SoundAdapter;
import org.maktab.beatbox.databinding.FragmentBeatBoxBinding;
import org.maktab.beatbox.model.Sound;
import org.maktab.beatbox.viewmodel.BeatBoxViewModel;

import java.util.ArrayList;
import java.util.List;

public class BeatBoxFragment extends Fragment {

    public static final String TAG = "BeatBoxFragment";

    private BeatBoxViewModel mBeatBoxViewModel;
    private FragmentBeatBoxBinding mBinding;

    public BeatBoxFragment() {
        // Required empty public constructor
    }

    public static BeatBoxFragment newInstance() {
        BeatBoxFragment fragment = new BeatBoxFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);

        mBeatBoxViewModel = new BeatBoxViewModel(getContext());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        mBeatBoxViewModel.releaseSoundPool();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_beat_box,
                container,
                false);

        initViews();
        setupAdapter();

        return mBinding.getRoot();
    }

    private void initViews() {
        mBinding.recyclerViewBeatBox
                .setLayoutManager(new GridLayoutManager(getContext(), 3));
    }

    private void setupAdapter() {
        List<Sound> sounds = mBeatBoxViewModel.getSounds();
        SoundAdapter adapter = new SoundAdapter(getContext(), sounds);
        mBinding.recyclerViewBeatBox.setAdapter(adapter);
    }
}