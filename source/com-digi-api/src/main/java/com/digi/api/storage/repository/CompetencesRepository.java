package com.digi.api.storage.repository;

import com.digi.api.storage.model.Competences;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CompetencesRepository extends JpaRepository<com.digi.api.storage.model.Competences, Long>, JpaSpecificationExecutor<Competences> {
}
