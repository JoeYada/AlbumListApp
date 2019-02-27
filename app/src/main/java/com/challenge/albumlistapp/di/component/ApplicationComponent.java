package com.challenge.albumlistapp.di.component;

import android.app.Application;

import com.challenge.albumlistapp.base.BaseApplication;
import com.challenge.albumlistapp.di.modules.ActivityBuilderModule;
import com.challenge.albumlistapp.di.modules.ApplicationModule;
import com.challenge.albumlistapp.di.modules.ContextModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component(modules = {ContextModule.class, ApplicationModule.class, AndroidSupportInjectionModule.class, ActivityBuilderModule.class})
public interface ApplicationComponent extends AndroidInjector<DaggerApplication> {

    void inject(BaseApplication application);

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);
        ApplicationComponent build();
    }
}
