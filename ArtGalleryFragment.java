package com.example.artgallery.Activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import androidx.fragment.app.Fragment;
import com.example.artgallery.R;
import java.util.ArrayList;

public class ArtGalleryFragment extends Fragment {
    private GridView gridView;
    private ArrayList<ArtPiece> artPieces;
    private CartFragment cartFragment;
    private ArrayList<CartItem> cartItems;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_art_gallery, container, false);
        gridView = view.findViewById(R.id.grid_view);
        artPieces = new ArrayList<>();
        cartItems = new ArrayList<>();
        cartFragment = new CartFragment();

        // Add art pieces to the list (you can replace these with actual images and data)
        artPieces.add(new ArtPiece("Starry Night", "Vincent van Gogh", "A famous painting by Vincent van Gogh.", R.drawable.art4));
        artPieces.add(new ArtPiece("Mona Lisa", "Leonardo da Vinci", "A portrait painting by Leonardo da Vinci.", R.drawable.art11));
        // Add more art pieces...

        ArtAdapter adapter = new ArtAdapter(getActivity(), artPieces);
        gridView.setAdapter(adapter);

        return view;
    }

    public void addToCart(CartItem cartItem) {
        // Check if the item already exists in the cart
        boolean itemExists = false;
        for (CartItem item : cartItems) {
            if (item.getArtPiece().getId() == cartItem.getArtPiece().getId()) {
                item.setQuantity(item.getQuantity() + 1); // Increase quantity if already in cart
                itemExists = true;
                break;
            }
        }

        // If item doesn't exist, add it to the cart
        if (!itemExists) {
            cartItems.add(cartItem);
        }

    }
}