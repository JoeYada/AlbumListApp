package com.challenge.albumlistapp.view.album_list;


import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.challenge.albumlistapp.R;
import com.challenge.albumlistapp.base.BaseFragment;
import com.challenge.albumlistapp.utils.ViewModelFactory;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;


public class AlbumListFragment extends BaseFragment {

    @BindView(R.id.album_list_recycler_view)
    RecyclerView albumListRecyclerView;
    @BindView(R.id.loading_progress_bar)
    ProgressBar progressBar;
    @BindView(R.id.error_message_textview)
    TextView errorMessageTextView;

    @Inject
    ViewModelFactory viewModelFactory;

    private AlbumListViewModel viewModel;

    @Override
    protected int layoutRes() {
        return R.layout.fragment_album_list;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(AlbumListViewModel.class);

    }
}
