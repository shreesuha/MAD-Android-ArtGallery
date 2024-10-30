package com.example.artgallery.Activity;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.artgallery.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {

        DrawerLayout drawerLayout;
        ImageButton btnDrawerToggle;
        NavigationView navigationView;
        FloatingActionButton fab;
        BottomNavigationView btmNavView;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            EdgeToEdge.enable(this);
            setContentView(R.layout.activity_home);


            ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
                Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
                return insets;
            });

            // Initialize views
            ArrayList<CartItem> cartItems = new ArrayList<>();
            CartFragment cartFragment = new CartFragment();
            ArrayList<ArtPiece> allArtPieces = new ArrayList<>();
            drawerLayout = findViewById(R.id.layout);
            btnDrawerToggle = findViewById(R.id.drawer_btn);
            btmNavView = findViewById(R.id.bottomNavigationView);
            navigationView = findViewById(R.id.navigationView);
            navigationView.setNavigationItemSelectedListener(item -> {
                int itemId = item.getItemId();

                if (itemId == R.id.home) {
                    replaceFragment(new HomeFragment());
                } else if (itemId == R.id.nav_fav) {
                    FavoritesFragment favoritesFragment = new FavoritesFragment();
                    favoritesFragment.updateFavorites(allArtPieces);
                    replaceFragment(new FavoritesFragment());
                } else if (itemId == R.id.nav_cart) {
                    cartFragment.setCartItems(cartItems);
                    replaceFragment(new CartFragment());
                } else if (itemId == R.id.nav_settings) {
                    replaceFragment(new SettingsFragment());
                } else if (itemId == R.id.nav_art_gallery) {
                    replaceFragment(new ArtGalleryFragment());
                } else if (itemId == R.id.nav_logout) {
                    finish();
                }
                drawerLayout.closeDrawers();
                return true;
            });

            // Load the default fragment
            if (savedInstanceState == null) {
                replaceFragment(new HomeFragment());
            }


            fab = findViewById(R.id.fab);

            // Set up drawer toggle
            btnDrawerToggle.setOnClickListener(v -> drawerLayout.open());

            // Set up FAB
            fab.setOnClickListener(view -> showBottomDialog());
            fab.setOnClickListener(view -> showUploadDialog());

            // Set up bottom navigation
            btmNavView.setOnItemSelectedListener(item -> {
                int itemId = item.getItemId();

                if (itemId == R.id.home) {
                    replaceFragment(new HomeFragment());
                } else if (itemId == R.id.shorts) {
                    replaceFragment(new ShortsFragment());
                } else if (itemId == R.id.subscriptions) {
                    replaceFragment(new SubscriptionFragment());
                } else if (itemId == R.id.library) {
                    replaceFragment(new LibraryFragment());
                }
                return true;
            });

            // Load default fragment
            if (savedInstanceState == null) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, new HomeFragment())
                        .commit();
                navigationView.setCheckedItem(R.id.nav_home);
            }
        }

        private void replaceFragment(Fragment fragment) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, fragment);
            fragmentTransaction.commit();
        }

        private void showBottomDialog() {
            final Dialog dialog = new Dialog(this);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setContentView(R.layout.bottom_sheet_layout);

            ConstraintLayout videoLayout = dialog.findViewById(R.id.layoutUpload);
            ConstraintLayout shortsLayout = dialog.findViewById(R.id.layoutShorts);
            ConstraintLayout liveLayout = dialog.findViewById(R.id.layoutLive);
            ImageView cancelButton = dialog.findViewById(R.id.cancelButton);

            videoLayout.setOnClickListener(v -> {
                dialog.dismiss();
                Toast.makeText(HomeActivity.this, "Upload Art is clicked", Toast.LENGTH_SHORT).show();
            });

            shortsLayout.setOnClickListener(v -> {
                dialog.dismiss();
                Toast.makeText(HomeActivity.this, "Create a short is Clicked", Toast.LENGTH_SHORT).show();
            });

            liveLayout.setOnClickListener(v -> {
                dialog.dismiss();
                Toast.makeText(HomeActivity.this, "Videos is Clicked", Toast.LENGTH_SHORT).show();
            });

            cancelButton.setOnClickListener(view -> dialog.dismiss());

            dialog.show();
            dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
            dialog.getWindow().setGravity(Gravity.BOTTOM);
        }

        private void showUploadDialog() {
            final Dialog dialog = new Dialog(this);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setContentView(R.layout.bottom_sheet_layout);


            ImageView cancelButton = dialog.findViewById(R.id.cancelButton);



            cancelButton.setOnClickListener(view -> dialog.dismiss());

            dialog.show();
            dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
            dialog.getWindow().setGravity(Gravity.BOTTOM);
        }



    }