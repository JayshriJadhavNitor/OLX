
package com.TradeSpot.entities;

import jakarta.persistence.*;
import com.TradeSpot.entities.*;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


@Entity
@Table(name = "Users")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User extends BaseEntity implements UserDetails {


    private String firstName;
    private String lastName;
    @Column(unique = true)
    private String email;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String address;

    @Enumerated(EnumType.STRING)
    private Roles role;

    @OneToMany(mappedBy = "buyer",cascade = CascadeType.ALL)
    List<BroughtItems> broughtItemsList=new ArrayList<>();

    @OneToMany(mappedBy = "seller", cascade = CascadeType.ALL)
    List<SellItems> sellItemsList=new ArrayList<>();



//	Implementation for UserDetails

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // TODO Auto-generated method stub
        return List.of(new SimpleGrantedAuthority(role.name()));
    }



    @Override
    public String getUsername() {
        // TODO Auto-generated method stub
        return email;
    }



    @Override
    public boolean isAccountNonExpired() {
        // TODO Auto-generated method stub
        return true;
    }



    @Override
    public boolean isAccountNonLocked() {
        // TODO Auto-generated method stub
        return true;
    }



    @Override
    public boolean isCredentialsNonExpired() {
        // TODO Auto-generated method stub
        return true;
    }



    @Override
    public boolean isEnabled() {
        // TODO Auto-generated method stub
        return true;
    }
}
