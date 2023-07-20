package org.condueetpension.data.repository;

import org.condueetpension.data.models.Token;
import org.condueetpension.data.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token, String> {
    Optional<Token> findTokenByUserId(String userId);
    Optional<Token> findTokenByUserIdAndToken(String userId, String token);
}
