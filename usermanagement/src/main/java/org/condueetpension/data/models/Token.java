package org.condueetpension.data.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
public class Token {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tokenId;
    private final LocalDateTime creationTime = LocalDateTime.now();
    private final LocalDateTime expiryTime = creationTime.plusMinutes(30L);
    private String token;
    private String userId;

    public Token () {
    }

    public Token (User user, String token) {
        this.userId = user.getUserId();
        this.token = token;

    }

}
