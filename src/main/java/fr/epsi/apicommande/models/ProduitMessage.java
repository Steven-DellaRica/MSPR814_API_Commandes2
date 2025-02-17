package fr.epsi.apicommande.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ProduitMessage {
    @JsonProperty
    private String id;

    @JsonProperty
    private double price;

    @JsonProperty
    private int quantity;

    public String getId() {
        return id;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }
}
