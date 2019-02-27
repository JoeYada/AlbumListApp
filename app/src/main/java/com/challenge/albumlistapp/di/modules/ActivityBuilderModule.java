package com.challenge.albumlistapp.di.modules;

import com.challenge.albumlistapp.view.AlbumListActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuilderModule {
    @ContributesAndroidInjector(modules = {FragmentBuilderModule.class})
    abstract AlbumListActivity bindAlbumListActivity();
}
