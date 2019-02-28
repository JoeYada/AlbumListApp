package com.challenge.albumlistapp.di.modules;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;

import com.challenge.albumlistapp.di.modules.data.LocalRepoModule;
import com.challenge.albumlistapp.di.modules.data.NetworkModule;
import com.challenge.albumlistapp.di.modules.viewmodel.ViewModelModule;
import com.challenge.albumlistapp.service.AlbumsService;
import com.challenge.albumlistapp.service.local.AlbumsDao;
import com.challenge.albumlistapp.service.remote.DataManager;
import com.challenge.albumlistapp.service.remote.DataManagerImpl;
import com.challenge.albumlistapp.common.BaseSchedulerProvider;
import com.challenge.albumlistapp.utils.ConnectivityUtils.DefaultOnlineChecker;
import com.challenge.albumlistapp.utils.ConnectivityUtils.OnlineChecker;
import com.challenge.albumlistapp.common.SchedulerProvider;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module(includes = {ViewModelModule.class, NetworkModule.class, LocalRepoModule.class})
public class ApplicationModule {

    @Singleton
    @Provides
    ConnectivityManager provideConnectivityManager(Application context) {
        return (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
    }

    @Singleton
    @Provides
    OnlineChecker onlineChecker(ConnectivityManager cm) {
        return new DefaultOnlineChecker(cm);
    }


    @Singleton
    @Provides
    BaseSchedulerProvider provideSchedulerProvider() {
        return new SchedulerProvider();
    }

    @Singleton
    @Provides
    DataManager provideDataManager(AlbumsService service, OnlineChecker onlineChecker, AlbumsDao albumsDao, SchedulerProvider schedulerProvider) {
        return new DataManagerImpl(service, albumsDao, onlineChecker, schedulerProvider);
    }

}
