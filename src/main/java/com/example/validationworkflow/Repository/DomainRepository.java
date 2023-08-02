package com.example.validationworkflow.Repository;

import com.example.validationworkflow.entities.Domain;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DomainRepository extends JpaRepository<Domain,Long> {

    List<Domain> findAll();






}
