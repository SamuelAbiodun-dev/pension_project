package org.condueetpension.data.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@Entity
@Builder
public class PenOpUser extends User{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String penOpUserId;
    private String organisationId;
}
