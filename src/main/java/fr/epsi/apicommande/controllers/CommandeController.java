package fr.epsi.apicommande.controllers;

import fr.epsi.apicommande.models.Commande;
import fr.epsi.apicommande.services.CommandeService;

import jakarta.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/apicommande/commandes")
public class CommandeController {

    @Autowired
    private final CommandeService commandeService;

    public CommandeController(CommandeService commandeService) {
        this.commandeService = commandeService;
    }

    @GetMapping
    public List<Commande> getAllCommandes() {
        return commandeService.getAllCommandes();
    }


    @GetMapping("/{id}")
    public ResponseEntity<Commande> getCommandeById(@PathVariable String id) {
        Optional<Commande> commande = commandeService.getCommandeById(id);
        return commande.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Commande createCommande(@RequestBody Commande commande) {
        return commandeService.createCommande(commande);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Commande> updateCommande(@PathVariable String id, @RequestBody Commande updatedCommande) {
        return commandeService.updateCommande(id, updatedCommande).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCommande(@PathVariable String id) {
        commandeService.deleteCommande(id);
        return ResponseEntity.noContent().build();
    }
}
