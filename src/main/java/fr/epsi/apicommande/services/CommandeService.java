package fr.epsi.apicommande.services;

import fr.epsi.apicommande.models.Commande;
import fr.epsi.apicommande.repositories.CommandeRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.nio.ByteBuffer;
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

    public Optional<Commande> getCommandeById(String id) {
        try {
            UUID uuid = UUID.fromString(id);
            // Convertir l'UUID en BINARY(16) (byte[])
            byte[] binaryUuid = convertUUIDToBytes(uuid);
            // Chercher la commande en base de données en utilisant le UUID converti
            return commandeRepo.findByIdAsBinary(binaryUuid);
        } catch (IllegalArgumentException e) {
            return Optional.empty(); // Si l'UUID n'est pas valide, retourner une réponse vide
        }

//        return commandeRepo.findById(id);
    }

    public Commande createCommande(Commande commande) {

        System.out.println("Avant save - UUID non généré : " + commande.getId());

        Commande savedCommande = commandeRepo.save(commande);

        System.out.println("Après save - UUID généré : " + savedCommande.getId());

        return savedCommande;
    }

    public Commande updateCommande(UUID id, Commande updatedCommande) {
        try {
            // Convertir l'UUID en BINARY(16) (byte[])
            byte[] binaryUuid = convertUUIDToBytes(id);
            // Chercher la commande en base de données en utilisant le UUID converti
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

    public void deleteCommande(UUID id) {
//        byte[] binaryUuid = convertUUIDToBytes(id);
        commandeRepo.deleteById(id);
    }

    private byte[] convertUUIDToBytes(UUID uuid) {
        ByteBuffer buffer = ByteBuffer.wrap(new byte[16]);
        buffer.putLong(uuid.getMostSignificantBits());
        buffer.putLong(uuid.getLeastSignificantBits());
        return buffer.array();
    }
}
