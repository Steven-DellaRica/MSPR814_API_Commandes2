package fr.epsi.apicommande.services;

import fr.epsi.apicommande.models.Details;
import fr.epsi.apicommande.repositories.DetailsRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DetailsService {
    private final DetailsRepository detailsRepository;

    public DetailsService(DetailsRepository detailsRepository) {
        this.detailsRepository = detailsRepository;
    }

    public List<Details> getAllDetails() {
        return detailsRepository.findAll();
    }

    public Optional<Details> getDetailsById(String id) {
        return detailsRepository.findById(id);
    }

    public Details createDetails(Details details) {
        return detailsRepository.save(details);
    }

    public Details updateDetails(String id, Details updatedDetails) {
        return detailsRepository.findById(id).map(details -> {
            details.setQuantity(updatedDetails.getQuantity());
            details.setProduitId(updatedDetails.getProduitId());
            return detailsRepository.save(details);
        }).orElse(null);
    }

    public void deleteDetails(String id) {
        detailsRepository.deleteById(id);
    }
}
