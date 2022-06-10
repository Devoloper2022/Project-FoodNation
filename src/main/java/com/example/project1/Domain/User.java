package com.example.project1.Domain;

import com.example.project1.Domain.Dictionary.DPosition;
import com.example.project1.Domain.Dictionary.DRole;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
@Data
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String firstName;
    @Column
    private String secondName;
    @Column(nullable = false, unique = true)
    private String username;
    @Column(length = 3000)
    private String password;


    @Column(unique = true)
    private String email;
    @Column(unique = true, length = 10)
    private String phoneNumber;

    @Column(columnDefinition = "text")
    private String urlImage;

    @ManyToMany
    private Set<DRole> roles =new HashSet<>();

    @ManyToMany
    private Set<DPosition> positions = new HashSet<>();

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "localOrganizationID")
    private LocalOrganization localOrganization;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "generalOrganizationID")
    private GeneralOrganization generalOrganization;

    @OneToMany(mappedBy = "customer")
    private List<OrderDetails> orderList;

    @OneToMany(mappedBy = "customer")
    private List<Reservation> reservationList;

    @Column
    private  Boolean delete;

    @Transient
    private Collection<? extends GrantedAuthority> authorities;

    public User() {
    }

    public User(Long id, String username, String password, String email, Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.authorities = authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
