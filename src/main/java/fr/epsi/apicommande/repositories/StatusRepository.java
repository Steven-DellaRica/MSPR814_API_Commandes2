package fr.epsi.apicommande.repositories;

import fr.epsi.apicommande.models.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StatusRepository extends JpaRepository<Status, String> {
    @Query("SELECT s FROM Status s WHERE s.currentStatus = :nom")
    Optional<Status> findByCurrentStatus(@Param("nom") String nom);
}
