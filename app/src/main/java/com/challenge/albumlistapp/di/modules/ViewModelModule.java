package com.challenge.albumlistapp.di.modules;

import com.challenge.albumlistapp.view.AlbumListViewModel;
import com.challenge.albumlistapp.di.ViewModelKey;
import com.challenge.albumlistapp.utils.ViewModelFactory;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(AlbumListViewModel.class)
    abstract ViewModel bindListViewModel(AlbumListViewModel listViewModel);

    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(ViewModelFactory factory);
}
