package org.maktab.beatbox.viewmodel;

import android.content.Context;

import org.maktab.beatbox.model.Sound;
import org.maktab.beatbox.repository.BeatBoxRepository;

import java.util.List;

public class BeatBoxViewModel {

    private BeatBoxRepository mRepository;

    public BeatBoxViewModel(Context context) {
        mRepository = BeatBoxRepository.getInstance(context);
    }

    public List<Sound> getSounds() {
        return mRepository.getSounds();
    }

    public void releaseSoundPool() {
        mRepository.releaseSoundPool();
    }
}
