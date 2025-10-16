package com.soccer.stats.repository;

import com.soccer.stats.model.Transfer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransferRepository extends JpaRepository<Transfer, String> {
    List<Transfer> findByPlayerId(String playerId);
    List<Transfer> findByFromTeamIdOrToTeamId(String fromTeamId, String toTeamId);
}
