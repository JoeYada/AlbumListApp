package com.challenge.albumlistapp.view.album_list;

import com.challenge.albumlistapp.models.Album;
import com.challenge.albumlistapp.service.remote.DataManager;
import com.challenge.albumlistapp.common.SchedulerProvider;

import java.util.List;

import javax.inject.Inject;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import io.reactivex.disposables.CompositeDisposable;

public class AlbumListViewModel extends ViewModel {

    private final DataManager dataManager;
    private CompositeDisposable compositeDisposable;
    private final SchedulerProvider schedulerProvider;

    private MutableLiveData<List<Album>> albumsList = new MutableLiveData<>();
    private MutableLiveData<Boolean> isLoading = new MutableLiveData<>();
    private MutableLiveData<Boolean> showErrorToast = new MutableLiveData<>();

    @Inject
    public AlbumListViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        this.schedulerProvider = schedulerProvider;
        compositeDisposable = new CompositeDisposable();
        this.dataManager = dataManager;
        getAlbums();
    }

    private void getAlbums() {
        setIsLoading(true);
        compositeDisposable.add(dataManager
                .getAlbums().subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .doOnSubscribe(data -> setIsLoading(true))
                .doOnSuccess(data -> setIsLoading(false))
                .doOnError(data -> {
                    setIsLoading(false);
                    showErrorToast.setValue(true);
                })
                .subscribe(this::setAlbumsList));
    }

    public void setIsLoading(boolean isLoading) {
        this.isLoading.setValue(isLoading);
    }

    public void setAlbumsList(List<Album> albums) {
        albumsList.setValue(albums);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        if (compositeDisposable != null) {
            compositeDisposable.clear();
            compositeDisposable = null;
        }
    }

    public MutableLiveData<List<Album>> getAlbumsList() {
        return albumsList;
    }

    public MutableLiveData<Boolean> getIsLoading() {
        return isLoading;
    }

    public MutableLiveData<Boolean> getShowErrorToast() {
        return showErrorToast;
    }
}
