package com.example.artgallery.Activity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.artgallery.Activity.ArtPiece;
import com.example.artgallery.R;
import java.util.ArrayList;

public class ArtAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<ArtPiece> artPieces;
    private ArtGalleryFragment artGalleryFragment; // Reference to the fragment

    public ArtAdapter(Context context, ArrayList<ArtPiece> artPieces) {
        this.context = context;
        this.artPieces = artPieces;
        this.artGalleryFragment = artGalleryFragment;
    }

    @Override
    public int getCount() {
        return artPieces.size();
    }

    @Override
    public Object getItem(int position) {
        return artPieces.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_art, parent, false);
        }

        ArtPiece artPiece = artPieces.get(position);
        ImageView imageView = convertView.findViewById(R.id.art_image);
        TextView titleTextView = convertView.findViewById(R.id.art_title);
        TextView artistTextView = convertView.findViewById(R.id.art_artist);
        ImageView favoriteIcon = convertView.findViewById(R.id.favorite_icon);
        ImageView addToCartIcon = convertView.findViewById(R.id.add_to_cart_icon);

        imageView.setImageResource(artPiece.getImageResourceId());
        titleTextView.setText(artPiece.getTitle());
        artistTextView.setText(artPiece.getArtist());


        favoriteIcon.setImageResource(artPiece.isFavorite() ? R.drawable.baseline_favorite_24 : R.drawable.baseline_favorite_border_24);
        favoriteIcon.setOnClickListener(v -> {
            artPiece.setFavorite(!artPiece.isFavorite()); // Toggle favorite status
            notifyDataSetChanged(); // Refresh the adapter
        });

        addToCartIcon.setOnClickListener(v -> {
            CartItem cartItem = new CartItem(artPiece, 1); // Default quantity is 1
            artGalleryFragment.addToCart(cartItem); // Call method in fragment to add to cart
            Toast.makeText(context, artPiece.getTitle() + " added to cart", Toast.LENGTH_SHORT).show();
        });

        return convertView;
    }
}