package fr.epsi.apicommande.models;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
public class Details {

    @Id
    @Column(length = 36, unique = true, nullable = false)
    private String id;

    @Column(nullable = false)
    private int quantity;

    public Details() {
        this.id = UUID.randomUUID().toString();
    }

    @ManyToOne
    @JoinColumn(name = "commande_id", nullable = false)
    private Commande commande;

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
}
