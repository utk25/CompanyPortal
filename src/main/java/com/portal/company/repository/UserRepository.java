package com.portal.company.repository;

import com.portal.company.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, String> {

    @Query("select distinct user from User user " +
            "where user.username = :username " +
            "AND user.password = :password")
    User findUserByUsername(@Param("username") String username, @Param("password") String password);

    @Query("select distinct user from User user " +
            "where user.username = :username")
    User findUserByUsername(@Param("username") String username);
}