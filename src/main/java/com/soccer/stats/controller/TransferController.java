package com.soccer.stats.controller;

import com.soccer.stats.model.Transfer;
import com.soccer.stats.service.TransferService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/transfers")
@Tag(name = "Teams", description = "Team management API")
public class TransferController {

    @Autowired
    TransferService transferService;

    @Operation(summary = "Get all teams")
    @GetMapping
    public List<Transfer> getAllTransfers() {
        return transferService.getAllTransfers();
    }

    @Operation(summary = "Get team by ID")
    @GetMapping("/{id}")
    public Optional<Transfer> getTransfer(@PathVariable String id) {
        return transferService.getTransferById(id);
    }

    @Operation(summary = "Add a new team")
    @PostMapping
    public Transfer addTransfer(@RequestBody Transfer team) {
        return transferService.addTransfer(team);
    }

    @Operation(summary = "Update a team")
    @PutMapping("/{id}")
    public Transfer updateTransfer(@PathVariable String id, @RequestBody Transfer team) {
        return transferService.updateTransfer(id, team);
    }

    @Operation(summary = "Delete a team")
    @DeleteMapping("/{id}")
    public void deleteTransfer(@PathVariable String id) {
        transferService.deleteTransfer(id);
    }
}
