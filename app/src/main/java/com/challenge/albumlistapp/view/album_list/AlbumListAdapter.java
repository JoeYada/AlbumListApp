package com.challenge.albumlistapp.view.album_list;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.challenge.albumlistapp.R;
import com.challenge.albumlistapp.models.Album;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AlbumListAdapter extends RecyclerView.Adapter<AlbumListViewHolder> {

    List<Album> albumList = new ArrayList<>();

    @NonNull
    @Override
    public AlbumListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View itemView = layoutInflater.inflate(R.layout.album_list_item, viewGroup, false);
        return new AlbumListViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull AlbumListViewHolder albumListViewHolder, int position) {
        Album album = albumList.get(position);
        albumListViewHolder.bind(album);
    }

    @Override
    public int getItemCount() {
        return albumList != null ? albumList.size() : 0;
    }

    public void setAlbumList(List<Album> newItems) {
        if (albumList.size() > 0) {
            albumList.clear();
        }
        albumList.addAll(newItems);
        notifyDataSetChanged();
    }
}
