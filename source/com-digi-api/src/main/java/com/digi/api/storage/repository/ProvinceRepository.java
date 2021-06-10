package com.digi.api.storage.repository;

import com.digi.api.storage.model.Province;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProvinceRepository extends JpaRepository<Province, Long> {
    Page<Province> findAll(Specification<Province> specification, Pageable pageable);
}
