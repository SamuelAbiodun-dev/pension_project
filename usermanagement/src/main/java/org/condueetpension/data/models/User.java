package org.condueetpension.data.models;

import jakarta.persistence.*;
import lombok.*;
import org.condueetpension.userMannagementUtility.constants.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Data
@Builder
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String userId;
    private String firstName;
    private String middleName;
    private String lastName;
    @Column(nullable = false, unique = true)
    private String email;
    private String password;
    @Column(nullable = false, unique = true)
    private String phoneNumber;
    private String userName;
//    @Enumerated(EnumType.STRING)
//    private Role role;
    private String rsaPin;
    private String token;
    @Column(columnDefinition = "Timestamp")
    private LocalDateTime tokenCreationDateTime;
    private Role role;
    private boolean isEnabled = false;
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> authorities = new HashSet<>();
//        for (String role : rolesType) {
        authorities.add(new SimpleGrantedAuthority("ROLE_"+getRole()));
//        }
        return authorities;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }

}
