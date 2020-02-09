package com.sungkyu.discover.di;


import android.app.Application;

import com.sungkyu.discover.App;
import com.sungkyu.discover.di.modules.ActivityModule;
import com.sungkyu.discover.di.modules.DBModule;
import com.sungkyu.discover.di.modules.RepositoyrModule;
import com.sungkyu.discover.di.modules.ViewModelModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component(modules = {
        AndroidSupportInjectionModule.class,
        RepositoyrModule.class,
        ActivityModule.class,
        ViewModelModule.class,
        DBModule.class})
public interface AppComponent {

     @Component.Builder
     interface Builder {
          @BindsInstance
          Builder application(Application application);

          AppComponent build();
     }

     void inject(App app);
}

