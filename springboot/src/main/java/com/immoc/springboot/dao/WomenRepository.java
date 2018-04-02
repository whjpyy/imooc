package com.immoc.springboot.dao;

import com.immoc.springboot.entity.Women;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WomenRepository extends JpaRepository<Women, Integer> {

    public List<Women> findByAge(int age);
}
