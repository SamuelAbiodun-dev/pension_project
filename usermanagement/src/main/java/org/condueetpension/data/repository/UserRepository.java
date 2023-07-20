package org.condueetpension.data.repository;
import org.condueetpension.data.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
@Repository
public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findByEmail(String email);
    Optional<User> findByToken(String token);
    Optional<User> findByUserId(String id);
    void deleteUserByEmail(String email);
    void deleteAll();
}
