package com.example.partnerapi.repository;

import com.example.partnerapi.model.Application;
import com.example.partnerapi.model.LeaseOption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LeaseOptionRepository extends JpaRepository<LeaseOption, Long> {

    Optional<LeaseOption> findLeaseOptionByMonthsAndApplication(Integer numberOfMonths, Application application);
}
