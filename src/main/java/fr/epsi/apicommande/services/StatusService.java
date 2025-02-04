package fr.epsi.apicommande.services;


import fr.epsi.apicommande.models.Status;
import fr.epsi.apicommande.repositories.StatusRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class StatusService {
    private final StatusRepository statusRepo;

    public StatusService(StatusRepository statusRepo) { this.statusRepo = statusRepo; }

    public List<Status> getAllStatuses() { return statusRepo.findAll(); }

    public Status getStatusById(UUID id) {
        return statusRepo.findById(id).orElseThrow(() -> new EntityNotFoundException("Status introuvable avec ID : " + id));
    }

    public Status createStatus(Status status) { return statusRepo.save(status); }

    public Status updateStatus(UUID id, Status updatedStatus) {
        return statusRepo.findById(id).map(status -> {
            status.setCurrentStatus(updatedStatus.getCurrentStatus());
            return statusRepo.save(status);
        }).orElseThrow(() -> new EntityNotFoundException("Status introuvable avec ID : " + id));
    }
    public void deleteStatus(UUID id) { statusRepo.deleteById(id); }
}
