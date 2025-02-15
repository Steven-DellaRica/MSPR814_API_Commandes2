package fr.epsi.apicommande.controllers;

import fr.epsi.apicommande.models.Details;
import fr.epsi.apicommande.services.DetailsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/apicommande/details")
public class DetailsController {
    private final DetailsService detailsService;

    public DetailsController(DetailsService detailsService) {
        this.detailsService = detailsService;
    }

    @GetMapping
    public List<Details> getAllDetails() {
        return detailsService.getAllDetails();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Details> getDetailsById(@PathVariable String id) {
        Optional<Details> details = detailsService.getDetailsById(id);
        return details.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Details createDetails(@RequestBody Details details) {
        return detailsService.createDetails(details);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Details> updateDetails(@PathVariable String id, @RequestBody Details updatedDetails) {
        Details details = detailsService.updateDetails(id, updatedDetails);
        return details != null ? ResponseEntity.ok(details) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDetails(@PathVariable String id) {
        detailsService.deleteDetails(id);
        return ResponseEntity.noContent().build();
    }
}

