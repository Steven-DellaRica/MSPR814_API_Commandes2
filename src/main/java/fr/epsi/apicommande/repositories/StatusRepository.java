package fr.epsi.apicommande.repositories;

import fr.epsi.apicommande.models.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface StatusRepository extends JpaRepository<Status, String> {
}

