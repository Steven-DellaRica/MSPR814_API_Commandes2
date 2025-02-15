package fr.epsi.apicommande.listeners;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQListener {



    @RabbitListener(queues = "produit_queue")
    public void receiveMessage(String message) {
        System.out.println("Message reçu dans API_Commande : " + message);

    }
}
