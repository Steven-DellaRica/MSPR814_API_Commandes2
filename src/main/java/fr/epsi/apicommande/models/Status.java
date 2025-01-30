package fr.epsi.apicommande.models;

import fr.epsi.apicommande.services.UUIDConverter;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
public class Status {

    @Id
    @GeneratedValue
    @Convert(converter = UUIDConverter.class)
    private UUID id;

    @Column(nullable = false, unique = true)
    private String currentStatus;

    @ManyToMany(mappedBy = "statuses")
    private Set<Commande> commandes = new HashSet<>();

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getCurrentStatus() {
        return currentStatus;
    }

    public void setCurrentStatus(String currentStatus) {
        this.currentStatus = currentStatus;
    }

    public Set<Commande> getCommandes() {
        return commandes;
    }

    public void setCommandes(Set<Commande> commandes) {
        this.commandes = commandes;
    }
}
