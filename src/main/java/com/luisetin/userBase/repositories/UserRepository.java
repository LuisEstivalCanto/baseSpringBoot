package com.luisetin.userBase.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.luisetin.userBase.models.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUsername(String username);


}
