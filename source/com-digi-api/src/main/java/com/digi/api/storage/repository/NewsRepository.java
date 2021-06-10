package com.digi.api.storage.repository;

import com.digi.api.storage.model.News;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NewsRepository extends JpaRepository<News, Long> {
    Page<News> findAll(Specification<News> specification, Pageable pageable);
}
