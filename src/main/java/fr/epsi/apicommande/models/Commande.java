package fr.epsi.apicommande.models;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "commandes")
public class Commande {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date_creation")
    private LocalDate dateCreation;

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
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(LocalDate dateCreation) {
        this.dateCreation = dateCreation;
    }
}
