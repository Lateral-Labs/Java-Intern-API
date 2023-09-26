package com.example.partnerapi.repository;

import com.example.partnerapi.model.LeaseOption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LeaseOptionRepository extends JpaRepository<LeaseOption, Long> {

}
