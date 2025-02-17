package fr.epsi.apicommande.models;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.*;

@Entity
@Table(name = "commandes")
public class Commande {

    @Id
    @Column(length = 36, unique = true, nullable = false)
    private String id;

    @Column(name = "date_creation", nullable = false)
    private LocalDate dateCreation;

    @Column(name = "username", nullable = false)
    private String username;

    @ManyToOne
    @JoinColumn(name = "status_id")
    private Status status;

    @OneToMany(mappedBy = "commande", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Details> details = new ArrayList<>();

    public Commande() {
        this.id = UUID.randomUUID().toString();
        this.dateCreation = LocalDate.now();
    }

    // Getters et Setters
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public LocalDate getDateCreation() {
        return dateCreation;
    }
    public void setDateCreation(LocalDate dateCreation) {
        this.dateCreation = dateCreation;
    }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public Status getStatus() {
        return status;
    }
    public void setStatus(Status status) {
        this.status = status;
    }

    public List<Details> getDetails() {
        return details;
    }
    public void setDetails(List<Details> details) {
        this.details = details;
    }
}
