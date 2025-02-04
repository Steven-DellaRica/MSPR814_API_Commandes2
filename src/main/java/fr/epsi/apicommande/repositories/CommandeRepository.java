package fr.epsi.apicommande.repositories;

import fr.epsi.apicommande.models.Commande;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CommandeRepository extends JpaRepository<Commande, UUID> {
    @Query("SELECT c FROM Commande c WHERE c.id = :id")
    Optional<Commande> findByIdAsBinary(@Param("id") byte[] id);
}