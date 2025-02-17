package fr.epsi.apicommande.services;

import fr.epsi.apicommande.models.Status;
import fr.epsi.apicommande.repositories.StatusRepository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class StatusInitializer implements CommandLineRunner {
    private final StatusRepository statusRepository;

    public StatusInitializer(StatusRepository statusRepository) {
        this.statusRepository = statusRepository;
    }

    @Override
    public void run(String... args) {

        statusRepository.deleteAll();

        // Créer et enregistrer les statuts
        List<Status> statuses = Arrays.asList(
                new Status("En attente"),
                new Status("En cours"),
                new Status("Terminé")
        );

        statusRepository.saveAll(statuses);
        System.out.println("Statuts initialisés !");
    }
}
