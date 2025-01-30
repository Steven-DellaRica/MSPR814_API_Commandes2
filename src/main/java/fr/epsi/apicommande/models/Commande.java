package fr.epsi.apicommande.models;

import fr.epsi.apicommande.services.UUIDConverter;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.*;

@Entity
@Table(name = "commandes")
public class Commande {

    @Id
    @GeneratedValue
    @Convert(converter = UUIDConverter.class)
    private UUID id;

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
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public LocalDate getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(LocalDate dateCreation) {
        this.dateCreation = dateCreation;
    }
}
