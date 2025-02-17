package fr.epsi.apicommande.listeners;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.epsi.apicommande.models.Commande;
import fr.epsi.apicommande.models.Details;
import fr.epsi.apicommande.models.ProduitMessage;
import fr.epsi.apicommande.models.Status;
import fr.epsi.apicommande.repositories.StatusRepository;
import fr.epsi.apicommande.services.CommandeService;
import fr.epsi.apicommande.services.DetailsService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RabbitMQListener {

    private final CommandeService commandeService;
    private final DetailsService detailsService;
    private final StatusRepository statusRepo;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public RabbitMQListener (CommandeService commandeService, DetailsService detailsService, StatusRepository statusRepo) {
        this.commandeService = commandeService;
        this.detailsService = detailsService;
        this.statusRepo = statusRepo;
    }

    @RabbitListener(queues = "product_info_queue")
    public void receiveMessage(List<ProduitMessage> produits) {

        try {
            for (ProduitMessage produit : produits) {
                System.out.println("Produit reçu : " + produit);

                String produitId = produit.getId();
                double price = produit.getPrice();
                int quantity = produit.getQuantity();

                System.out.println("J'ai récupéré l'Id : " + produitId);
                System.out.println("J'ai récupéré prix : " + price);
                System.out.println("J'ai récupéré quantité : " + quantity);

                Status defaultStatus = statusRepo.findByCurrentStatus("En attente")
                        .orElseThrow(() -> new RuntimeException("Le status 'En attente' est introuvable"));

                Commande newCommande = new Commande();
                newCommande.setUsername("jean_doe");
                newCommande.setStatus(defaultStatus);
                Commande savedCommande = commandeService.createCommande(newCommande);

                // Créer les détails liés à cette commande
                Details details = new Details();
                details.setProduitId(produitId);
                details.setPrice(price);
                details.setQuantity(quantity);
                details.setCommande(savedCommande); // Associer la commande

                // Sauvegarder les détails
                detailsService.createDetails(details);

                System.out.println("Re Coucou Kévin, la commande a été créée avec ID: " + savedCommande.getId());
            }
        } catch (Exception e) {
            System.err.println("Erreur de conversion JSON : " + e.getMessage());
        }
    }
}
