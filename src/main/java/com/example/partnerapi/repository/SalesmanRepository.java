package com.example.partnerapi.repository;

import com.example.partnerapi.model.Salesman;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalesmanRepository extends JpaRepository<Salesman,Long> {

}
