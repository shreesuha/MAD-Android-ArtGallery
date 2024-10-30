package com.example.artgallery.Activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import androidx.fragment.app.Fragment;
import com.example.artgallery.R;

import java.util.ArrayList;

public class FavoritesFragment extends Fragment {
    private GridView gridView;
    private ArrayList<ArtPiece> favoriteArtPieces;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorites, container, false);
        gridView = view.findViewById(R.id.grid_view);
        favoriteArtPieces = new ArrayList<>();

        // Here, you would ideally fetch favorite art pieces from the original list
        // For simplicity, we will assume you have a method to get favorites

        ArtAdapter adapter = new ArtAdapter(getActivity(), favoriteArtPieces);
        gridView.setAdapter(adapter);

        return view;
    }

    // Method to update the favorite art pieces
    public void updateFavorites(ArrayList<ArtPiece> allArtPieces) {
        favoriteArtPieces.clear();
        for (ArtPiece artPiece : allArtPieces) {
            if (artPiece.isFavorite()) {
                favoriteArtPieces.add(artPiece);
            }
        }
        ((ArtAdapter) gridView.getAdapter()).notifyDataSetChanged(); // Notify the adapter of the changes
    }

}