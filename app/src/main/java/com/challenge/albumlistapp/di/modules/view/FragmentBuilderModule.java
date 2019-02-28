package com.challenge.albumlistapp.di.modules.view;

import com.challenge.albumlistapp.view.album_list.AlbumListFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class FragmentBuilderModule {

    @ContributesAndroidInjector
    abstract AlbumListFragment provideAlbumListFragment();
}
