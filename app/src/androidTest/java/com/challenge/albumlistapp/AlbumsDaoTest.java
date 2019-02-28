package com.challenge.albumlistapp;

import com.challenge.albumlistapp.models.Album;
import com.challenge.albumlistapp.service.local.AlbumDataBase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Arrays;
import java.util.List;

import androidx.room.Room;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;
import io.reactivex.subscribers.TestSubscriber;

@RunWith(AndroidJUnit4.class)
public class AlbumsDaoTest {

    private AlbumDataBase albumDataBase;

    private TestSubscriber<Album> albumTestSubscriber;
    private TestSubscriber<List<Album>> albumListTestSubscriber;

    private static final List<Album> ALBUM_LIST = Arrays.asList(
            new Album(1, 2, "Album One"),
            new Album(2, 3, "Album Two"),
            new Album(4, 5, "Album Three")
    );

    @Before
    public void initDb() {
        albumDataBase =  Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getInstrumentation().getContext(),
                AlbumDataBase.class).build();

        albumTestSubscriber = new TestSubscriber<>();
        albumListTestSubscriber = new TestSubscriber<>();
    }

    @After
    public void close() {
        albumDataBase.close();
    }

    @Test
    public void insertAlbumAndGetById() {
        albumDataBase.albumsDao().insert(ALBUM_LIST.get(0));

        albumDataBase.albumsDao().getAlbumById(ALBUM_LIST.get(0).getId()).toFlowable().subscribe(albumTestSubscriber);

        albumTestSubscriber.assertValue(ALBUM_LIST.get(0));
    }

    @Test
    public void insertQuakesAndGet() {
        // insert quakes
        albumDataBase.albumsDao().insert(ALBUM_LIST.get(0));
        albumDataBase.albumsDao().insert(ALBUM_LIST.get(1));
        albumDataBase.albumsDao().insert(ALBUM_LIST.get(2));

        // getting quakes from the database
        albumDataBase.albumsDao()
                .getAlbums().toFlowable().subscribe(albumListTestSubscriber);

        // the loaded data contains the expected values
        albumListTestSubscriber.assertValue(ALBUM_LIST);
    }
}
