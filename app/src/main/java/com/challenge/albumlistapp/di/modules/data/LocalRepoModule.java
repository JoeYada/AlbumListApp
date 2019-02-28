package com.challenge.albumlistapp.di.modules.data;

import android.app.Application;

import com.challenge.albumlistapp.service.local.AlbumDataBase;
import com.challenge.albumlistapp.service.local.AlbumsDao;
import com.challenge.albumlistapp.utils.Constants;

import javax.inject.Singleton;

import androidx.room.Room;
import dagger.Module;
import dagger.Provides;

@Module
public class LocalRepoModule {

    @Singleton
    @Provides
    AlbumDataBase provideDatabase(Application context) {
        return Room.databaseBuilder(context.getApplicationContext(),
                AlbumDataBase.class, Constants.ALBUM_DB_NAME).build();
    }

    @Singleton
    @Provides
    AlbumsDao provideAlbumsDao(AlbumDataBase dataBase) {
        return dataBase.albumsDao();
    }

}
