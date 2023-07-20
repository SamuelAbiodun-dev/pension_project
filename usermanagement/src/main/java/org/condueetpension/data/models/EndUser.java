package org.condueetpension.data.models;

import jakarta.persistence.*;
import lombok.*;
import org.condueetpension.userMannagementUtility.constants.UserStatus;

@Entity
@AllArgsConstructor
@RequiredArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class EndUser extends User  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String endUserId;
    private String address;
    private String bvn;
    @OneToOne
    private Account account;
    private String image;
    private String role;
    private String pfa;
    @Enumerated(EnumType.STRING)
    private UserStatus userStatus;


}
