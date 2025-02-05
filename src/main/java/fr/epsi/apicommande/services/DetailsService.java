package fr.epsi.apicommande.services;

import fr.epsi.apicommande.models.Details;
import fr.epsi.apicommande.repositories.CommandeRepository;
import fr.epsi.apicommande.repositories.DetailsRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DetailsService {

    private final DetailsRepository detailsRepo;

    public DetailsService(DetailsRepository detailsRepo, CommandeRepository commandeRepo) {
        this.detailsRepo = detailsRepo;
    }

    public List<Details> getAllDetails() {
        return detailsRepo.findAll();
    }

    public Details getDetailsById(String id) {
        return detailsRepo.findById(id).orElseThrow(() -> new EntityNotFoundException("Details introuvable avec ID : " + id));
    }

    public Details createDetails(Details details) {
        return detailsRepo.save(details);
    }

    public Details updateDetails(String id, Details updatedDetails) {
        return detailsRepo.findById(id).map(details -> {
            details.setQuantity(updatedDetails.getQuantity());
            details.setCommande(updatedDetails.getCommande());
            return detailsRepo.save(details);
        }).orElseThrow(() -> new EntityNotFoundException("Details introuvable avec ID : " + id));
    }

    public void deleteDetails(String id) {
        detailsRepo.deleteById(id);
    }
}
