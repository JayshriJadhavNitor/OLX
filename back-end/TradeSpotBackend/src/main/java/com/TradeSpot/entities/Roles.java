
package com.TradeSpot.entities;


import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;

public enum Roles {

    USER, ADMIN;

    public List<SimpleGrantedAuthority> getAuthorities(){
        List<SimpleGrantedAuthority> lst=new ArrayList<>();
        lst.add(new SimpleGrantedAuthority("ROLE_"+this.name()));
        return lst;
    }
}
