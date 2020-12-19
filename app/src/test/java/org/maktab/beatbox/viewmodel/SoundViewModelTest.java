package org.maktab.beatbox.viewmodel;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.maktab.beatbox.model.Sound;
import org.maktab.beatbox.repository.BeatBoxRepository;
import org.mockito.Mockito;
import org.mockito.hamcrest.MockitoHamcrest;

import static org.junit.Assert.*;

public class SoundViewModelTest {

    private SoundViewModel mSubject;
    private BeatBoxRepository mRepository;
    private Sound mSound;

    @Before
    public void setUp() throws Exception {
        mRepository = Mockito.mock(BeatBoxRepository.class);
        mSubject = new SoundViewModel(mRepository);

        mSound = new Sound("test_dir/testFile.tst");
        mSubject.setSound(mSound);
    }

    @Test
    public void exposesSoundNameAsTitle() {
        String title = mSubject.getTitle();
        String name = mSound.getName();

//        Assert.assertEquals(title, name);
        MatcherAssert.assertThat(title, Matchers.equalTo(name));
    }

    @Test
    public void callsBeatBoxPlayOnButtonClicked() {
        mSubject.onPlayButtonClicked();
        Mockito.verify(mRepository).play(mSound);
    }
}