package com.challenge.albumlistapp.utils;

import com.challenge.albumlistapp.models.Album;

import java.util.Collections;
import java.util.List;

public final class SortUtils {

    public static List<Album> sortByTitle(List<Album> albumList) {
        Collections.sort(albumList, (album, album2) -> album.getTitle().compareTo(album2.getTitle()));

        return albumList;
    }
}
