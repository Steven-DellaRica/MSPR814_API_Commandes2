package fr.epsi.apicommande.controllers;

import fr.epsi.apicommande.models.Status;
import fr.epsi.apicommande.services.StatusService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/apicommande/status")
public class StatusController {
    private final StatusService statusService;

    public StatusController(StatusService statusService) {
        this.statusService = statusService;
    }

    @GetMapping
    public ResponseEntity<List<Status>> getAllStatuses() {
        return ResponseEntity.ok(statusService.getAllStatuses());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Status> getStatusById(@PathVariable String id) {
        return ResponseEntity.ok(statusService.getStatusById(id));
    }

    @PostMapping
    public ResponseEntity<Status> createStatus(@RequestBody Status status) {
        return ResponseEntity.ok(statusService.createStatus(status));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Status> updateStatus(@PathVariable String id, @RequestBody Status updatedStatus) {
        return ResponseEntity.ok(statusService.updateStatus(id, updatedStatus));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStatus(@PathVariable String id) {
        statusService.deleteStatus(id);
        return ResponseEntity.noContent().build();
    }
}
