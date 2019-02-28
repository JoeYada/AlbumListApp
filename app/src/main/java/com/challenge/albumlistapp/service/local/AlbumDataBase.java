package com.challenge.albumlistapp.service.local;

import com.challenge.albumlistapp.models.Album;
import com.challenge.albumlistapp.utils.Constants;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Album.class}, version = Constants.DATABASE_VERSION, exportSchema = false)
public abstract class AlbumDataBase extends RoomDatabase {
    public abstract AlbumsDao albumsDao();
}
