package fr.epsi.apicommande.repositories;

import fr.epsi.apicommande.models.Details;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetailsRepository extends JpaRepository<Details, Long> {
}
