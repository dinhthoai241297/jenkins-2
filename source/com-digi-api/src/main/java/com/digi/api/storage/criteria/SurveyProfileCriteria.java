package com.digi.api.storage.criteria;

import com.digi.api.constant.DigiConstant;
import com.digi.api.storage.model.SurveyProfile;
import lombok.Data;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Data
public class SurveyProfileCriteria {
    private Long id;
    private String surveyName;
    private Integer status;

    public Specification<SurveyProfile> getSpecification() {
        return new Specification<SurveyProfile>() {
            private static final long serialVersionUID = 1L;
            @Override
            public Predicate toPredicate(Root<SurveyProfile> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<>();
                if(getId() != null){
                    predicates.add(cb.equal(root.get("id"), getId()));
                }
                if(!StringUtils.isEmpty(getSurveyName())){
                    predicates.add(cb.like(cb.lower(root.get("name")), "%"+getSurveyName().toLowerCase()+"%"));
                }
                if(getStatus() != null){
                    predicates.add(cb.equal(root.get("status"), getStatus()));
                }
                return cb.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
    }

    public Specification<SurveyProfile> getSpecificationAutoComplete() {
        return new Specification<SurveyProfile>() {
            private static final long serialVersionUID = 1L;
            @Override
            public Predicate toPredicate(Root<SurveyProfile> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<>();
                if(!StringUtils.isEmpty(getSurveyName())){
                    predicates.add(cb.like(cb.lower(root.get("name")), "%"+getSurveyName().toLowerCase()+"%"));
                }
                predicates.add(cb.equal(root.get("status"), DigiConstant.STATUS_ACTIVE));
                return cb.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
    }
}
