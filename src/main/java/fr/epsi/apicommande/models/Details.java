package fr.epsi.apicommande.models;

import fr.epsi.apicommande.services.UUIDConverter;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
public class Details {

    @Id
    @GeneratedValue
    @Convert(converter = UUIDConverter.class)
    @Column(columnDefinition = "BINARY(16)")
    private UUID id;

    @Column(nullable = false)
    private int quantity;

    @ManyToOne
    @JoinColumn(name = "commande_id", nullable = false)
    private Commande commande;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
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
