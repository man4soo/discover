package com.sungkyu.discover.di.modules;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.sungkyu.discover.DataRepository;
import com.sungkyu.discover.viewModels.DiscoverViewModel;
import com.sungkyu.discover.viewModels.ViewModelFactory;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class ViewModelModule {

    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(ViewModelFactory factory);

    @Binds
    abstract ViewModel discoverViewModel(DiscoverViewModel discoverViewModel);

    //abstract DiscoverViewModel discoverViewModel(DataRepository dataRepository);
}
