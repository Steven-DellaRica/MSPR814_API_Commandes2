package fr.epsi.apicommande.controllers;

import fr.epsi.apicommande.models.Details;
import fr.epsi.apicommande.services.DetailsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/apicommande/details")
public class DetailsController {
    private final DetailsService detailsService;

    public DetailsController(DetailsService detailsService) {
        this.detailsService = detailsService;
    }

    @GetMapping
    public ResponseEntity<List<Details>> getAllDetails() {
        return ResponseEntity.ok(detailsService.getAllDetails());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Details> getDetailsById(@PathVariable String id) {
        return ResponseEntity.ok(detailsService.getDetailsById(id));
    }

    @PostMapping
    public ResponseEntity<Details> createDetails(@RequestBody Details details) {
        return ResponseEntity.ok(detailsService.createDetails(details));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Details> updateDetails(@PathVariable String id, @RequestBody Details updatedDetails) {
        return ResponseEntity.ok(detailsService.updateDetails(id, updatedDetails));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDetails(@PathVariable String id) {
        detailsService.deleteDetails(id);
        return ResponseEntity.noContent().build();
    }
}
