package com.challenge.albumlistapp.service.remote;

import com.challenge.albumlistapp.models.Album;

import java.util.List;

import io.reactivex.Single;

public interface DataManager {

    Single<List<Album>> getAlbums();
}
