package com.digi.api.storage.repository;

import com.digi.api.storage.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Page<Category> findAll(Specification<Category> specification, Pageable pageable);

    @Query("SELECT c FROM Category c WHERE c.parentCategory.id = ?1")
    List<Category> findByParentId(Integer parentId);
}
