package com.example.artgallery.Activity;

public class CartItem {
    private ArtPiece artPiece;
    private int quantity;

    public CartItem(ArtPiece artPiece, int quantity) {
        this.artPiece = artPiece;
        this.quantity = quantity;
    }

    public ArtPiece getArtPiece() {
        return artPiece;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}