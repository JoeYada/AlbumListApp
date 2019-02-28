package com.challenge.albumlistapp.service.local;

import com.challenge.albumlistapp.models.Album;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import io.reactivex.Single;

@Dao
public interface AlbumsDao {

    @Query("SELECT * FROM Album")
    Single<List<Album>> getAlbums();

    /**
     * Retrieve a album by id.
     *
     * @param id the Album id.
     * @return the album with id
     */
    @Query("SELECT * FROM Album WHERE id = :id")
    Single<Album> getAlbumById(int id);

    /**
     * Insert Album in the database. If the Album already exists, ignore the action.
     *
     * @param album to be inserted.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Album album);
}
