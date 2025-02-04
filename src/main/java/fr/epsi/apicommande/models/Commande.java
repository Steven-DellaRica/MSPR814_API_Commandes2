package fr.epsi.apicommande.models;

import fr.epsi.apicommande.services.UUIDConverter;
import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDate;
import java.util.*;

@Entity
@Table(name = "commandes")
public class Commande {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Convert(converter = UUIDConverter.class)
    @Column(columnDefinition = "BINARY(16)")
    private UUID id;

    @PostLoad
    public void postLoad() {
        System.out.println("UUID chargé : " + id.toString());
    }

    @Column(name = "date_creation", nullable = false)
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
