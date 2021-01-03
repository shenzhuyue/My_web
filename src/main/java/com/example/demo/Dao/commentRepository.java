package com.example.demo.Dao;
import com.example.demo.comment;
import org.springframework.data.repository.CrudRepository;
import java.util.ArrayList;
import java.util.List;
public interface commentRepository extends CrudRepository<comment,Integer> {
    List<comment> findAll();
    List<comment> findAllByWebid(int webid);
}
