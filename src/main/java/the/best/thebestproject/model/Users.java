package the.best.thebestproject.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Set;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "users")
public class Users implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

    @Column(nullable = false, length = 63)
    String fullName;

    @Column(unique = true)
    String email;

    @Column(nullable = false)
    String password;

    @OneToMany(mappedBy = "users")
    List<Address> address;

    @ManyToOne
    @JoinColumn(name = "default_address_id")
    private Address defaultAddress;

    @OneToOne(mappedBy = "users")
    Cart cart;

    @OneToMany(mappedBy = "users")
    Set<Orders> orders;

    @Column(name = "is_admin")
    @Builder.Default
    Boolean admin = false;

    @Column(name = "is_enabled")
    @Builder.Default
    Boolean enabled = true;

    private String resetToken;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getUsername() {
        return "";
    }
}

