
package com.TradeSpot.repositories;


import com.TradeSpot.entities.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepo extends JpaRepository<User,Long> {

    @Query(" SELECT u from User u where u.firstName= :name ")
    List<User> getByName(@Param("name") String name);

}
