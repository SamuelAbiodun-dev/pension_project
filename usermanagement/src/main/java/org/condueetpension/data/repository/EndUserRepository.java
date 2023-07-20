package org.condueetpension.data.repository;

import org.condueetpension.data.models.EndUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EndUserRepository extends JpaRepository<EndUser, String> {
}
