package fr.epsi.apicommande.services;

import fr.epsi.apicommande.models.Commande;
import fr.epsi.apicommande.repositories.CommandeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommandeService {

    @Autowired
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
        return commandeRepo.save(commande);
    }

    public Optional<Commande> updateCommande(String id, Commande updatedCommande) {
        return commandeRepo.findById(id).map(existingCommande -> {
            existingCommande.setDateCreation(updatedCommande.getDateCreation());
            existingCommande.setStatus(updatedCommande.getStatus());
            return commandeRepo.save(existingCommande);
        });
    }

    public void deleteCommande(String id) {
        commandeRepo.deleteById(id);
    }
}
