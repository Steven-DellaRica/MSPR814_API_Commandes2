package fr.epsi.apicommande.repositories;

import fr.epsi.apicommande.models.Commande;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CommandeRepository extends JpaRepository<Commande, UUID> {
}