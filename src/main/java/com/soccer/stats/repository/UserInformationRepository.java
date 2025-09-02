package com.soccer.stats.repository;

import com.soccer.stats.model.security.UserInformation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserInformationRepository extends JpaRepository<UserInformation, String> {

    Optional<UserInformation> findByUsername(String username);

}
