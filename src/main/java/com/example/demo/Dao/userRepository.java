package com.example.demo.Dao;
import com.example.demo.user;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;
import java.util.List;


public interface userRepository extends CrudRepository<user,Integer> {
    List<user> findAll();
    user findByUsernameAndPassword(String username,String password);
    user findByUsername(String username);
}
