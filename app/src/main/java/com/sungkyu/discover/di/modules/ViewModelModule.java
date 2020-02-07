package com.sungkyu.discover.di.modules;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.sungkyu.discover.viewModels.DiscoverViewModel;

import dagger.Binds;
import dagger.Module;


@Module
public abstract class ViewModelModule {
    abstract ViewModel discoverViewModel(DiscoverViewModel discoverViewModel);
}
