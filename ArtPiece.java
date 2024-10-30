package com.example.artgallery.Activity;

public class ArtPiece {
    private String title;
    private String artist;
    private String description;
    private int imageResourceId;
    private boolean isFavorite;
    private int id;

    public ArtPiece(String title, String artist, String description, int imageResourceId) {
        this.title = title;
        this.artist = artist;
        this.description = description;
        this.imageResourceId = imageResourceId;
        this.isFavorite = false;
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public String getArtist() {
        return artist;
    }

    public String getDescription() {
        return description;
    }

    public int getImageResourceId() {
        return imageResourceId;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }

    public int getId() {
        return id;
    }

}