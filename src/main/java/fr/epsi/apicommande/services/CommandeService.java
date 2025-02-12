package fr.epsi.apicommande.services;

import fr.epsi.apicommande.models.Commande;
import fr.epsi.apicommande.models.Status;
import fr.epsi.apicommande.repositories.CommandeRepository;
import fr.epsi.apicommande.repositories.StatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommandeService {

    @Autowired
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
        Status defaultStatus = statusRepo.findById("afae129d-d735-4f54-814c-dba63701d503")
                .orElseThrow(() -> new RuntimeException("Status not found"));
        commande.setStatus(defaultStatus);
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
