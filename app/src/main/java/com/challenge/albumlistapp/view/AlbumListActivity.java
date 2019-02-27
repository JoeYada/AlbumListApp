package com.challenge.albumlistapp.base.view;

import android.os.Bundle;

import com.challenge.albumlistapp.R;
import com.challenge.albumlistapp.base.BaseActivity;


public class AlbumListActivity extends BaseActivity {

    @Override
    protected int layoutRes() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            getSupportFragmentManager().beginTransaction().add(R.id.container, new AlbumListFragment()).commit();
        }
    }
}
