package org.condueetpension.data.repository;

import org.condueetpension.data.models.PfcUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PfcRepository extends JpaRepository<PfcUser, String> {
    Optional<PfcUser> findByUserId(String pfcUserId);
}
