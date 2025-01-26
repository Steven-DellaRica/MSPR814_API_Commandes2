package fr.epsi.apicommande.services;

import fr.epsi.apicommande.models.Commande;
import fr.epsi.apicommande.repositories.CommandeRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommandeService {

    // Rajouter les gestions d'erreurs

    private final CommandeRepository commandeRepo;

    public CommandeService(CommandeRepository commandeRepo) {
        this.commandeRepo = commandeRepo;
    }

    public List<Commande> getAllCommandes() {
        return commandeRepo.findAll();
    }

    public Optional<Commande> getCommandeById(Long id) {
        return commandeRepo.findById(id);
    }

    public Commande createCommande(Commande commande) {
        return commandeRepo.save(commande);
    }

    public Commande updateCommande(Long id, Commande updatedCommande) {
        return commandeRepo.findById(id).map(existingCommande -> {
            // Modifier directement StatusService ou Controller
            // existingCommande.setStatus(updatedCommande.getStatus());
            existingCommande.setDateCreation(updatedCommande.getDateCreation());
            return commandeRepo.save(existingCommande);
        }).orElseThrow(() -> new EntityNotFoundException("Commande avec l'ID " + id + " introuvable."));
    }

    public void deleteCommande(Long id) {
        commandeRepo.deleteById(id);
    }
}
