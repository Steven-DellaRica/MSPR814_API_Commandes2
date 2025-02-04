package fr.epsi.apicommande.controllers;

import fr.epsi.apicommande.models.Commande;
import fr.epsi.apicommande.repositories.CommandeRepository;
import fr.epsi.apicommande.services.CommandeService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/apicommande/commandes")
public class CommandeController {

    private final CommandeService commandeService;
    private final CommandeRepository commandeRepository;

    public CommandeController(CommandeService commandeService, CommandeRepository commandeRepository) {
        this.commandeService = commandeService;
        this.commandeRepository = commandeRepository;
    }

    @GetMapping
    public List<Commande> getAllCommandes() {
        return commandeService.getAllCommandes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Commande> getCommandeById(@PathVariable String id) {
        try {
//            UUID uuid = UUID.fromString(id);
            System.out.println(id);
            return commandeService.getCommandeById(id)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }

    }

    @PostMapping
    public Commande createCommande(@RequestBody Commande commande) {
        return commandeService.createCommande(commande);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Commande> updateCommande(@PathVariable UUID id, @RequestBody Commande updatedCommande) {
        try {
            Commande commande = commandeService.updateCommande(id, updatedCommande);
            return ResponseEntity.ok(commande);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCommande(@PathVariable UUID id) {
        commandeService.deleteCommande(id);
        return ResponseEntity.noContent().build();
    }
}
