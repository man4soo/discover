package com.sungkyu.discover.di.modules;

import androidx.lifecycle.ViewModel;

import com.sungkyu.discover.viewModels.DiscoverViewModel;

import dagger.Module;


@Module
public abstract class ViewModelModule {
    abstract ViewModel discoverViewModel(DiscoverViewModel discoverViewModel);
}
