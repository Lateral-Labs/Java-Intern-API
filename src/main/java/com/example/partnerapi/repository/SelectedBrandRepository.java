package com.example.partnerapi.repository;

import com.example.partnerapi.model.SelectedBrand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SelectedBrandRepository extends JpaRepository<SelectedBrand, Long> {
}
