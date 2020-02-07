package com.sungkyu.discover.di.modules;

import com.bumptech.glide.Glide;
import com.sungkyu.discover.ui.MainActivity;

import dagger.Module;
import dagger.Provides;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityModule {

    @ContributesAndroidInjector()
    abstract MainActivity contributeMainActivity();
}
