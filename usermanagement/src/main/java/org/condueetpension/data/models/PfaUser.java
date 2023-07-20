package org.condueetpension.data.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@Builder
public class PfaUser extends User{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String pfaId;
    private String organisationId;
}
