package com.digi.api.storage.criteria;

import com.digi.api.constant.DigiConstant;
import com.digi.api.storage.model.WorkField;
import lombok.Data;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

@Data
public class WorkFieldCriteria {
    private Integer id;
    private String title;
    private Integer status;

    public Specification<WorkField> getSpecification() {
        return new Specification<WorkField>() {
            private static final long serialVersionUID = 1L;
            @Override
            public Predicate toPredicate(Root<WorkField> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<>();
                if(getId() != null){
                    predicates.add(cb.equal(root.get("id"), getId()));
                }
                if(!StringUtils.isEmpty(getTitle())){
                    predicates.add(cb.like(cb.lower(root.get("title")), "%"+getTitle().toLowerCase()+"%"));
                }
                if(getStatus() != null){
                    predicates.add(cb.equal(root.get("status"), getStatus()));
                }
                return cb.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
    }

    public Specification<WorkField> getSpecificationAutoComplete() {
        return new Specification<WorkField>() {
            private static final long serialVersionUID = 1L;
            @Override
            public Predicate toPredicate(Root<WorkField> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<>();
                if(!StringUtils.isEmpty(getTitle())){
                    predicates.add(cb.like(cb.lower(root.get("title")), "%"+getTitle().toLowerCase()+"%"));
                }
                predicates.add(cb.equal(root.get("status"), DigiConstant.STATUS_ACTIVE));
                return cb.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
    }
}
