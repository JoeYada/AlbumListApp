package com.challenge.albumlistapp.service;

import com.challenge.albumlistapp.models.Album;
import com.challenge.albumlistapp.utils.Constants;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;

public interface AlbumsService {

    @GET(Constants.RELATIVE_URL)
    Single<List<Album>> getAlbums();
}
