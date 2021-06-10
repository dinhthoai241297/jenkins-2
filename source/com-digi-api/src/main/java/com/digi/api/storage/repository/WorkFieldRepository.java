package com.digi.api.storage.repository;

import com.digi.api.storage.model.WorkField;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface WorkFieldRepository extends JpaRepository<WorkField, Long>, JpaSpecificationExecutor<WorkField> {

}
