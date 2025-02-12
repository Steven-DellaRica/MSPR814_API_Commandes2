package fr.epsi.apicommande.models;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
public class Status {

    @Id
    @Column(length = 36, unique = true, nullable = false)
    private String id;

    @Column(nullable = false, unique = true)
    private String currentStatus;

    @OneToMany(mappedBy = "status", cascade = CascadeType.ALL)
    private Set<Commande> commandes = new HashSet<>();

    public Status() {
        this.id = UUID.randomUUID().toString();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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
