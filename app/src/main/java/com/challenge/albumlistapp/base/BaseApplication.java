package com.challenge.albumlistapp.base;

import android.app.Activity;
import android.app.Application;

import com.challenge.albumlistapp.di.component.ApplicationComponent;
import com.challenge.albumlistapp.di.component.DaggerApplicationComponent;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;

public class BaseApplication extends Application implements HasActivityInjector {

    @Inject
    DispatchingAndroidInjector<Activity> activityDispatchingAndroidInjector;

    @Override
    public void onCreate() {
        super.onCreate();
        ApplicationComponent component = DaggerApplicationComponent.builder()
                .application(this).build();
        component.inject(this);
    }


    @Override
    public AndroidInjector<Activity> activityInjector() {
        return activityDispatchingAndroidInjector;
    }
}
