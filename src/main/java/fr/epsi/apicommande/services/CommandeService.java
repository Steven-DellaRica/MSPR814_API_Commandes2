package fr.epsi.apicommande.services;

import fr.epsi.apicommande.models.Commande;
import fr.epsi.apicommande.repositories.CommandeRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

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

    public Optional<Commande> getCommandeById(UUID id) {
        return commandeRepo.findById(id);
    }

    public Commande createCommande(Commande commande) {
        UUID uuid = UUID.randomUUID();
        byte[] bytes = new UUIDConverter().convertToDatabaseColumn(uuid);
        UUID reconvertedUUID = new UUIDConverter().convertToEntityAttribute(bytes);

        System.out.println("Original UUID: " + uuid);
        System.out.println("Converted to bytes: " + Arrays.toString(bytes));
        System.out.println("Reconverted UUID: " + reconvertedUUID);

        return commandeRepo.save(commande);
    }

    public Commande updateCommande(UUID id, Commande updatedCommande) {
        return commandeRepo.findById(id).map(existingCommande -> {
            // Modifier directement StatusService ou Controller
            // existingCommande.setStatus(updatedCommande.getStatus());
            existingCommande.setDateCreation(updatedCommande.getDateCreation());
            return commandeRepo.save(existingCommande);
        }).orElseThrow(() -> new EntityNotFoundException("Commande avec l'ID " + id + " introuvable."));
    }

    public void deleteCommande(UUID id) {
        commandeRepo.deleteById(id);
    }
}
