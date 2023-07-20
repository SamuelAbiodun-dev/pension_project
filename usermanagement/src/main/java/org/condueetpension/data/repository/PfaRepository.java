package org.condueetpension.data.repository;

import org.condueetpension.data.models.PfaUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PfaRepository extends JpaRepository<PfaUser, String> {
    Optional<PfaUser> findByUserId(String id);
}
