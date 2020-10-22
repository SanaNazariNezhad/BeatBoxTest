package org.maktab.beatbox.controller.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import org.maktab.beatbox.R;
import org.maktab.beatbox.databinding.FragmentBeatBoxBinding;
import org.maktab.beatbox.databinding.ListItemSoundBinding;
import org.maktab.beatbox.model.Sound;
import org.maktab.beatbox.repository.BeatBoxRepository;

import java.util.List;

public class BeatBoxFragment extends Fragment {

    public static final String TAG = "BeatBoxFragment";
//    private RecyclerView mRecyclerView;
    private BeatBoxRepository mRepository;
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
        Log.d(TAG, "onCreate");

        setRetainInstance(true);

        mRepository = BeatBoxRepository.getInstance(getContext());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy");

        mRepository.releaseSoundPool();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView");

        // Inflate the layout for this fragment
        mBinding = DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_beat_box,
                container,
                false);

//        findViews(view);
        initViews();
        setupAdapter();

        return mBinding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        Log.d(TAG, "onDestroyView");
    }

    private void initViews() {
        mBinding.recyclerViewBeatBox
                .setLayoutManager(new GridLayoutManager(getContext(), 3));
    }

    private void setupAdapter() {
        List<Sound> sounds = mRepository.getSounds();
        SoundAdapter adapter = new SoundAdapter(sounds);
        mBinding.recyclerViewBeatBox.setAdapter(adapter);
    }

    public class SoundHolder extends RecyclerView.ViewHolder {

        private ListItemSoundBinding mListItemSoundBinding;

        public SoundHolder(ListItemSoundBinding listItemSoundBinding) {
            super(listItemSoundBinding.getRoot());

            mListItemSoundBinding = listItemSoundBinding;
            mListItemSoundBinding.setSoundHolder(this);
        }

        public void play(Sound sound) {
            mRepository.play(sound);
        }

        public void bindSound(Sound sound) {
            mListItemSoundBinding.setSound(sound);
        }
    }

    private class SoundAdapter extends RecyclerView.Adapter<SoundHolder> {

        private List<Sound> mSounds;

        public List<Sound> getSounds() {
            return mSounds;
        }

        public void setSounds(List<Sound> sounds) {
            mSounds = sounds;
        }

        public SoundAdapter(List<Sound> sounds) {
            mSounds = sounds;
        }

        @NonNull
        @Override
        public SoundHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            ListItemSoundBinding binding = DataBindingUtil.inflate(
                    LayoutInflater.from(getContext()),
                    R.layout.list_item_sound,
                    parent,
                    false);

            return new SoundHolder(binding);
        }

        @Override
        public void onBindViewHolder(@NonNull SoundHolder holder, int position) {
            Sound sound = mSounds.get(position);
            holder.bindSound(sound);
        }

        @Override
        public int getItemCount() {
            return mSounds.size();
        }
    }
}