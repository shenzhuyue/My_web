package com.example.demo.Dao;
import com.example.demo.web_list;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.repository.CrudRepository;

import javax.swing.*;
import java.util.List;

public interface weblistRepository extends CrudRepository<web_list,Integer> {
    List<web_list> findAll();
    List<web_list> findAll(Sort sort);
    List<web_list> findAllByUserid(int usrid);
    List<web_list> findAll(Specification<web_list> spec, Pageable pageable);
}

