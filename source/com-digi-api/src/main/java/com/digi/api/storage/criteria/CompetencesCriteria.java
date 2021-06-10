package com.digi.api.storage.criteria;

import com.digi.api.constant.DigiConstant;
import com.digi.api.storage.model.Competences;
import com.digi.api.storage.model.Question;
import com.digi.api.storage.model.SurveyProfile;
import com.digi.api.storage.model.WorkField;
import lombok.Data;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

@Data
public class CompetencesCriteria {
    private Integer id;
    private String competencesName;
    private Integer status;
    private Long workFieldId;
    private Long surveyProfileId;

    public Specification<Competences> getSpecification() {
        return new Specification<Competences>() {
            private static final long serialVersionUID = 1L;
            @Override
            public Predicate toPredicate(Root<Competences> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<>();
                if(getId() != null){
                    predicates.add(cb.equal(root.get("id"), getId()));
                }
                if(!StringUtils.isEmpty(getCompetencesName())){
                    predicates.add(cb.like(cb.lower(root.get("name")), "%"+getCompetencesName().toLowerCase()+"%"));
                }
                if(getStatus() != null){
                    predicates.add(cb.equal(root.get("status"), getStatus()));
                }
                if(getWorkFieldId() != null){
                    Join<Competences, WorkField> joinWorkField = root.join("workField", JoinType.INNER);
                    predicates.add(cb.equal(joinWorkField.get("id"), getWorkFieldId()));
                }
                if(getSurveyProfileId() != null){
                    Join<Competences, SurveyProfile> joinSurveyProfile = root.join("surveyProfile", JoinType.INNER);
                    predicates.add(cb.equal(joinSurveyProfile.get("id"), getSurveyProfileId()));
                }
                return cb.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
    }

    public Specification<Competences> getSpecificationAutoComplete() {
        return new Specification<Competences>() {
            private static final long serialVersionUID = 1L;
            @Override
            public Predicate toPredicate(Root<Competences> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<>();
                if(!StringUtils.isEmpty(getCompetencesName())){
                    predicates.add(cb.like(cb.lower(root.get("name")), "%"+getCompetencesName().toLowerCase()+"%"));
                }
                if(getWorkFieldId() != null){
                    Join<Competences, WorkField> joinWorkField = root.join("workField", JoinType.INNER);
                    predicates.add(cb.equal(joinWorkField.get("id"), getWorkFieldId()));
                }
                if(getSurveyProfileId() != null){
                    Join<Competences, SurveyProfile> joinSurveyProfile = root.join("surveyProfile", JoinType.INNER);
                    predicates.add(cb.equal(joinSurveyProfile.get("id"), getSurveyProfileId()));
                }
                predicates.add(cb.equal(root.get("status"), DigiConstant.STATUS_ACTIVE));
                return cb.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
    }
}
