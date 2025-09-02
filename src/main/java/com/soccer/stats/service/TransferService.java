package com.soccer.stats.service;

import com.soccer.stats.model.Transfer;
import com.soccer.stats.repository.TransferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransferService {

    @Autowired
    TransferRepository transferRepository;

    public List<Transfer> getAllTransfers() {
        return transferRepository.findAll();
    }

    public Optional<Transfer> getTransferById(String id) {
        return transferRepository.findById(id);
    }

    public Transfer addTransfer(Transfer transfer) {
        return transferRepository.save(transfer);
    }

    public Transfer updateTransfer(String id, Transfer transfer) {
        return transferRepository.findById(id)
                .map(existingTransfer -> {
                    existingTransfer.setPlayer(transfer.getPlayer());
                    existingTransfer.setFromTeam(transfer.getFromTeam());
                    existingTransfer.setToTeam(transfer.getToTeam());
                    existingTransfer.setTransferDate(transfer.getTransferDate());
                    existingTransfer.setTransferFee(transfer.getTransferFee());
                    return transferRepository.save(existingTransfer);
                })
                .orElseThrow(() -> new RuntimeException("Transfer not found with id: " + id));
    }


    public void deleteTransfer(String id) {
        transferRepository.deleteById(id);
    }
}
