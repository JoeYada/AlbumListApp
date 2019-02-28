package com.challenge.albumlistapp.di.modules;

import com.challenge.albumlistapp.service.AlbumsService;
import com.challenge.albumlistapp.service.remote.DataManager;
import com.challenge.albumlistapp.service.remote.DataManagerImpl;
import com.challenge.albumlistapp.utils.Constants;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module(includes = ViewModelModule.class)
public class ApplicationModule {

    @Singleton
    @Provides
    Retrofit provideRetrofit() {
        return new Retrofit.Builder().baseUrl(Constants.BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Singleton
    @Provides
    AlbumsService provideRetrofitService(Retrofit retrofit) {
        return retrofit.create(AlbumsService.class);
    }

    @Singleton
    @Provides
    DataManager provideDataManager(AlbumsService service) {
        return new DataManagerImpl(service);
    }

}
