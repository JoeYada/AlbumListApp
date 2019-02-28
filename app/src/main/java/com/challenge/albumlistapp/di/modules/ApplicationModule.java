package com.challenge.albumlistapp.di.modules;

import com.challenge.albumlistapp.di.modules.data.LocalRepoModule;
import com.challenge.albumlistapp.di.modules.data.NetworkModule;
import com.challenge.albumlistapp.di.modules.viewmodel.ViewModelModule;
import com.challenge.albumlistapp.service.AlbumsService;
import com.challenge.albumlistapp.service.remote.DataManager;
import com.challenge.albumlistapp.service.remote.DataManagerImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module(includes = {ViewModelModule.class, NetworkModule.class, LocalRepoModule.class})
public class ApplicationModule {

    @Singleton
    @Provides
    DataManager provideDataManager(AlbumsService service) {
        return new DataManagerImpl(service);
    }

}
