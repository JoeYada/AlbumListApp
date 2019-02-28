package com.challenge.albumlistapp.view.album_list;


import android.view.View;
import android.widget.TextView;

import com.challenge.albumlistapp.R;
import com.challenge.albumlistapp.models.Album;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

class AlbumListViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.user_id_text)
    TextView userIdTextView;
    @BindView(R.id.album_text)
    TextView albumTextView;

    public AlbumListViewHolder(@NonNull View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind(Album album) {
        userIdTextView.setText(String.valueOf(album.getUserId()));
        albumTextView.setText(album.getTitle());
    }
}
