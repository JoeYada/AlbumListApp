package com.challenge.albumlistapp.service.remote;

import com.challenge.albumlistapp.models.Album;
import com.challenge.albumlistapp.service.AlbumsService;
import com.challenge.albumlistapp.service.local.AlbumsDao;
import com.challenge.albumlistapp.utils.ConnectivityUtils.OnlineChecker;
import com.challenge.albumlistapp.utils.SchedulerProvider;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Single;

public class DataManagerImpl implements DataManager {

    private final AlbumsService albumsService;
    private final AlbumsDao albumsDao;
    private final OnlineChecker onlineChecker;
    private final SchedulerProvider schedulerProvider;

    @Inject
    public DataManagerImpl(AlbumsService albumsService, AlbumsDao albumsDao, OnlineChecker onlineChecker, SchedulerProvider schedulerProvider) {
        this.albumsService = albumsService;
        this.albumsDao = albumsDao;
        this.onlineChecker = onlineChecker;
        this.schedulerProvider = schedulerProvider;
    }

    @Override
    public Single<List<Album>> getAlbums() {
        return albumsDao.getAlbums()
                .flatMap(data -> {
                    if (onlineChecker.isOnline() && data.isEmpty()) {
                        return albumsService.getAlbums()
                                .doOnSuccess(albums -> {
                                    for (Album album : albums) {
                                        saveAlbum(album);
                                    }
                                });
                    }
                    return Single.just(data);
                });
    }

    private void saveAlbum(Album album) {
        Completable.fromRunnable(() -> albumsDao.insert(album))
                .subscribeOn(schedulerProvider.io()).subscribe();
    }

}
