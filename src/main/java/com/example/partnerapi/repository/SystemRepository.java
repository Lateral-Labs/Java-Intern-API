package com.example.partnerapi.repository;

import com.example.partnerapi.model.System;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SystemRepository extends JpaRepository<System, Long> {

}
