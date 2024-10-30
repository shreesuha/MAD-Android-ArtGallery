package com.example.artgallery.Activity;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import com.example.artgallery.R;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class CartFragment extends Fragment {
    private ListView listView;
    private ArrayList<CartItem> cartItems;
    Button checkoutButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cart, container, false);
        listView = view.findViewById(R.id.list_view);
        checkoutButton = view.findViewById(R.id.checkout_button);
        cartItems = new ArrayList<>();

        // Load cart items (can be passed from the activity or fetched from a database)
        CartAdapter adapter = new CartAdapter(getActivity(), cartItems);
        listView.setAdapter(adapter);

        Button checkoutButton = view.findViewById(R.id.checkout_button);
        checkoutButton.setOnClickListener(v -> {
            if (cartItems.isEmpty()) {
                Toast.makeText(getActivity(), "Your cart is empty!", Toast.LENGTH_SHORT).show();
            } else {
                // Proceed to checkout
                Toast.makeText(getActivity(), "Proceeding to checkout...", Toast.LENGTH_SHORT).show();
                // Optionally, clear the cart after checkout
                cartItems.clear();
                adapter.notifyDataSetChanged(); // Notify adapter of changes
            }
        });

        return view;
    }

    CartAdapter adapter = new CartAdapter(getActivity(), cartItems);

    // Method to add item to cart
    public void addToCart(CartItem cartItem) {
        cartItems.add(cartItem);
        ((CartAdapter) listView.getAdapter()).notifyDataSetChanged();
    }

    public void setCartItems(ArrayList<CartItem> cartItems) {
        this.cartItems = cartItems;
        // Update the adapter or list view here if necessary
        CartAdapter adapter = new CartAdapter(getActivity(), cartItems);
        listView.setAdapter(adapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        loadCartData(); // Load cart data when the fragment is resumed
    }

    public void saveCartData() {
        // Example: Save cart data to SharedPreferences
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("CartData", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        // Convert cartItems to JSON (you can use Gson for this)
        Gson gson = new Gson();
        String json = gson.toJson(cartItems);
        editor.putString("cart_items", json);
        editor.apply(); // Save changes
    }

    private void loadCartData() {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("CartPrefs", Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("cartItems", null);
        Type type = new TypeToken<ArrayList<CartItem>>() {}.getType();

        cartItems = gson.fromJson(json, type);
        if (cartItems == null) {
            cartItems = new ArrayList<>(); // Initialize empty if no data
        }

        // Notify the adapter of the new data
        CartAdapter adapter = new CartAdapter(getActivity(), cartItems);
        listView.setAdapter(adapter);
    }
}