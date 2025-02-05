package fr.epsi.apicommande.services;

import fr.epsi.apicommande.models.Commande;
import fr.epsi.apicommande.repositories.CommandeRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommandeService {

    private final CommandeRepository commandeRepo;

    public CommandeService(CommandeRepository commandeRepo) {
        this.commandeRepo = commandeRepo;
    }

    public List<Commande> getAllCommandes() {
        return commandeRepo.findAll();
    }

    public Optional<Commande> getCommandeById(String id) {
        return commandeRepo.findById(id);
    }

    public Commande createCommande(Commande commande) {

        System.out.println("Avant save - UUID non généré : " + commande.getId());

        Commande savedCommande = commandeRepo.save(commande);

        System.out.println("Après save - UUID généré : " + savedCommande.getId());

        return savedCommande;
    }

    public Commande updateCommande(String id, Commande updatedCommande) {
        try {
            return commandeRepo.findById(id).map(existingCommande -> {
                // Modifier directement StatusService ou Controller
                // existingCommande.setStatus(updatedCommande.getStatus());
                existingCommande.setDateCreation(updatedCommande.getDateCreation());
                return commandeRepo.save(existingCommande);
            }).orElseThrow(() -> new EntityNotFoundException("Commande avec l'ID " + id + " introuvable."));
        } catch (IllegalArgumentException e) {
            System.out.println("Non C'est pas bien");
            return null;
//            return Optional.empty(); // Si l'UUID n'est pas valide, retourner une réponse vide
        }

    }

    public void deleteCommande(String id) {
        commandeRepo.deleteById(id);
    }
}
