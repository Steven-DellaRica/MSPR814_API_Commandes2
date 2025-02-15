package fr.epsi.apicommande.services;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQListener {
    @RabbitListener(queues = "commandeQueue")
    public void recevoirMessageCommande(String message) {
        System.out.println("Message reçu dans API_Commande : " + message);
    }
}
