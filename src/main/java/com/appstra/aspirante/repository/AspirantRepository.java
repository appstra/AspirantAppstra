package com.appstra.aspirante.repository;

import com.appstra.aspirante.entity.Aspirant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AspirantRepository extends JpaRepository<Aspirant,Integer> {
    Aspirant findByPersonId (Integer personId);
    List<Aspirant> findByStateIdNot(Integer stateId);
    List<Aspirant> findByStateId(Integer stateId);
}
