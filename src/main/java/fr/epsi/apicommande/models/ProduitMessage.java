package fr.epsi.apicommande.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ProduitMessage {
    @JsonProperty
    private String Id;

    @JsonProperty
    private double Price;

    @JsonProperty
    private int Quantity;

    public String getId() {
        return Id;
    }

    public double getPrice() {
        return Price;
    }

    public int getQuantity() {
        return Quantity;
    }
}
