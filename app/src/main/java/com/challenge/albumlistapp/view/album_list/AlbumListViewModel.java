package com.challenge.albumlistapp.view.album_list;

import com.challenge.albumlistapp.models.Album;
import com.challenge.albumlistapp.service.remote.DataManager;

import java.util.List;

import javax.inject.Inject;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class AlbumListViewModel extends ViewModel {

    private final DataManager dataManager;
    private CompositeDisposable compositeDisposable;

    private MutableLiveData<List<Album>> albumsList = new MutableLiveData<>();
    private MutableLiveData<Boolean> isLoading = new MutableLiveData<>();
    private MutableLiveData<Boolean> showErrorToast = new MutableLiveData<>();

    @Inject
    public AlbumListViewModel(DataManager dataManager) {
        compositeDisposable = new CompositeDisposable();
        this.dataManager = dataManager;
        getAlbums();
    }

    private void getAlbums() {
        setIsLoading(true);
        compositeDisposable.add(dataManager
                .getAlbums().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
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
