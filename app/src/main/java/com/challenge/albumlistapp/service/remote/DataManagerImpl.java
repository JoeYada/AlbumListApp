package com.challenge.albumlistapp.service.remote;

import com.challenge.albumlistapp.models.Album;
import com.challenge.albumlistapp.service.AlbumsService;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;

public class DataManagerImpl implements DataManager {

    private final AlbumsService albumsService;

    @Inject
    public DataManagerImpl(AlbumsService albumsService) {
        this.albumsService = albumsService;
    }

    @Override
    public Single<List<Album>> getAlbums() {
        return albumsService.getAlbums();
    }
}
