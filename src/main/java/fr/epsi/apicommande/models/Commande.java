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

    public Commande() {
        this.id = UUID.randomUUID().toString();
    }

    @ManyToMany
    @JoinTable(
            name = "commande_status",
            joinColumns = @JoinColumn( name = "commande_id" ),
            inverseJoinColumns = @JoinColumn(name = "status_id")
    )

    private Set<Status> statuses = new HashSet<>();

    @OneToMany(mappedBy = "commande", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Details> details = new ArrayList<>();

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
}
