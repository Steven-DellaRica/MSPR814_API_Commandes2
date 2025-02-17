package fr.epsi.apicommande.services;

import fr.epsi.apicommande.models.Commande;
import fr.epsi.apicommande.models.Status;
import fr.epsi.apicommande.repositories.CommandeRepository;
import fr.epsi.apicommande.repositories.StatusRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommandeService {

    private final CommandeRepository commandeRepo;
    private final StatusRepository statusRepo;

    public CommandeService(CommandeRepository commandeRepo, StatusRepository statusRepo) {
        this.commandeRepo = commandeRepo;
        this.statusRepo = statusRepo;
    }

    public List<Commande> getAllCommandes() {
        return commandeRepo.findAll();
    }

    public Optional<Commande> getCommandeById(String id) {
        return commandeRepo.findById(id);
    }

    public Commande createCommande(Commande commande) {
//        if (commande.getStatus() == null) {
//            Status defaultStatus = statusRepo.findByCurrentStatus("En attente")
//                    .orElseThrow(() -> new RuntimeException("Status non trouvé"));
//
//            commande.setStatus(defaultStatus);
//        }
        return commandeRepo.save(commande);
    }

    public Commande updateCommande(String id, Commande updatedCommande) {
        Optional<Commande> optionalCommande = commandeRepo.findById(id);
        if (optionalCommande.isPresent()) {
            Commande existingCommande = optionalCommande.get();
            existingCommande.setDateCreation(updatedCommande.getDateCreation());
            existingCommande.setStatus(updatedCommande.getStatus());
            existingCommande.setDetails(updatedCommande.getDetails());
            return commandeRepo.save(existingCommande);
        } else {
            throw new RuntimeException("Commande non trouvée");
        }
    }

    public void deleteCommande(String id) {
        commandeRepo.deleteById(id);
    }
}
