package com.example.artgallery.Activity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.ImageView;
import com.example.artgallery.R;
import java.util.ArrayList;

public class CartAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<CartItem> cartItems;
    private CartFragment cartFragment;

    public CartAdapter(Context context, ArrayList<CartItem> cartItems) {
        this.context = context;
        this.cartItems = cartItems;
        this.cartFragment = cartFragment;
    }

    @Override
    public int getCount() {
        return cartItems.size();
    }

    @Override
    public Object getItem(int position) {
        return cartItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_cart, parent, false);
        }

        CartItem cartItem = cartItems.get(position);
        TextView titleTextView = convertView.findViewById(R.id.cart_item_title);
        TextView quantityTextView = convertView.findViewById(R.id.cart_item_quantity);
        ImageView imageView = convertView.findViewById(R.id.cart_item_image);
        ImageButton increaseButton = convertView.findViewById(R.id.increase_quantity);
        ImageButton decreaseButton = convertView.findViewById(R.id.decrease_quantity);

        titleTextView.setText(cartItem.getArtPiece().getTitle());
        quantityTextView.setText("Quantity: " + cartItem.getQuantity());
        imageView.setImageResource(cartItem.getArtPiece().getImageResourceId());

        increaseButton.setOnClickListener(v -> {
            cartItem.setQuantity(cartItem.getQuantity() + 1);
            notifyDataSetChanged();
            cartFragment.saveCartData(); // Call saveCartData() directly on the fragment
        });

        decreaseButton.setOnClickListener(v -> {
            if (cartItem.getQuantity() > 1) {
                cartItem.setQuantity(cartItem.getQuantity() - 1);
                notifyDataSetChanged();
                cartFragment.saveCartData(); // Call saveCartData() directly on the fragment
            } else {
                // Optionally, remove the item from the cart if quantity is 1
                cartItems.remove(position);
                notifyDataSetChanged();
                cartFragment.saveCartData(); // Call saveCartData() directly on the fragment
            }
        });

        return convertView;
    }
}