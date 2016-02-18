package com.yart.user.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.yart.user.bean.User;

@Repository
public interface UserRepository extends CrudRepository<User, String>{

    public User getUserByEmail(String email);
    
    @Query("SELECT CASE WHEN COUNT(u) > 0 THEN 'true' ELSE 'false' END FROM User u WHERE u.email = ?1")
    public boolean existsByEmail(String email);
}
