package fr.epsi.apicommande.models;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
public class Details {

    @Id
    @Column(length = 36, unique = true, nullable = false)
    private String id;

    @Column(nullable = false)
    private int quantity;

    @ElementCollection
    @CollectionTable(name = "details_produits", joinColumns = @JoinColumn(name = "details_id"))
    @Column(name = "produit_id")
    private List<String> produits = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "commande_id", unique = true)
    private Commande commande;

    public Details() {
        this.id = UUID.randomUUID().toString();
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Commande getCommande() {
        return commande;
    }
    public void setCommande(Commande commande) {
        this.commande = commande;
    }

    public List<String> getProduits() {
        return produits;
    }
    public void setProduits(List<String> produits) {
        this.produits = produits;
    }
}
