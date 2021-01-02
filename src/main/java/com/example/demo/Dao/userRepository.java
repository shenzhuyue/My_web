package com.example.demo.Dao;
import com.example.demo.user;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface userRepository extends CrudRepository<user,Integer> {
    List<user> findAll();
}
