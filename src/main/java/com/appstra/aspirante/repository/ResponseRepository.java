package com.appstra.aspirante.repository;

import com.appstra.aspirante.entity.Response;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ResponseRepository extends JpaRepository<Response,Integer> {
    List<Response> findByAskAskId(Integer askId);
}
