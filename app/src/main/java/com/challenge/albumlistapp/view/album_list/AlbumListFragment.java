package com.challenge.albumlistapp.view.album_list;


import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.challenge.albumlistapp.R;
import com.challenge.albumlistapp.base.BaseFragment;
import com.challenge.albumlistapp.common.ViewModelFactory;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;


public class AlbumListFragment extends BaseFragment {

    @BindView(R.id.album_list_recycler_view)
    RecyclerView albumListRecyclerView;
    @BindView(R.id.loading_progress_bar)
    ProgressBar loadingProgressBar;

    @Inject
    ViewModelFactory viewModelFactory;

    private AlbumListViewModel viewModel;
    private AlbumListAdapter albumListAdapter;

    @Override
    protected int layoutRes() {
        return R.layout.fragment_album_list;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(AlbumListViewModel.class);

        initViews();
        getAlbums();
    }

    private void initViews() {
        albumListRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        albumListAdapter = new AlbumListAdapter();
        albumListRecyclerView.setAdapter(albumListAdapter);
    }

    private void getAlbums() {
        // Get album list
        viewModel.getAlbumsList().observe(this, albumList -> {
            if (albumList !=  null) {
                albumListRecyclerView.setVisibility(View.VISIBLE);
                albumListAdapter.setAlbumList(albumList);
            }
        });

        // Observe for loading state
        viewModel.getIsLoading().observe(this, isLoading -> {
            if (isLoading) {
                loadingProgressBar.setVisibility(View.VISIBLE);
                albumListRecyclerView.setVisibility(View.GONE);
            } else {
                loadingProgressBar.setVisibility(View.GONE);
            }
        });

        viewModel.getShowErrorToast().observe(this, isError -> {
            if (isError) {
                Toast.makeText(getContext(), R.string.loading_error_message, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
