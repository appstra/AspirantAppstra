package com.appstra.aspirante.repository;

import com.appstra.aspirante.entity.TypeTest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TypeTestRepository extends JpaRepository<TypeTest,Integer> {
    List<TypeTest> findByStatIdNot(Integer stateId);
}
