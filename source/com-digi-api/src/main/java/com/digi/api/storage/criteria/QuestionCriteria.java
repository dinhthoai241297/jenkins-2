package com.digi.api.storage.criteria;

import com.digi.api.constant.DigiConstant;
import com.digi.api.storage.model.Category;
import com.digi.api.storage.model.Competences;
import com.digi.api.storage.model.Question;
import lombok.Data;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

@Data
public class QuestionCriteria {
    private Integer id;
    private String questName;
    private Integer status;
    private Integer kind;
    private Long competencesId;

    public Specification<Question> getSpecification() {
        return new Specification<Question>() {
            private static final long serialVersionUID = 1L;
            @Override
            public Predicate toPredicate(Root<Question> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<>();
                if(getId() != null){
                    predicates.add(cb.equal(root.get("id"), getId()));
                }
                if(!StringUtils.isEmpty(getQuestName())){
                    predicates.add(cb.like(cb.lower(root.get("name")), "%"+getQuestName().toLowerCase()+"%"));
                }
                if(getStatus() != null){
                    predicates.add(cb.equal(root.get("status"), getStatus()));
                }
                if(getKind() != null){
                    predicates.add(cb.equal(root.get("kind"), getKind()));
                }
                if(getCompetencesId() != null){
                    Join<Question, Competences> joinCompetences = root.join("competences", JoinType.INNER);
                    predicates.add(cb.equal(joinCompetences.get("id"), getCompetencesId()));
                }
                return cb.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
    }

    public Specification<Question> getSpecificationAutoComplete() {
        return new Specification<Question>() {
            private static final long serialVersionUID = 1L;
            @Override
            public Predicate toPredicate(Root<Question> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<>();
                if(!StringUtils.isEmpty(getQuestName())){
                    predicates.add(cb.like(cb.lower(root.get("title")), "%"+getQuestName().toLowerCase()+"%"));
                }
                if(getCompetencesId() != null){
                    Join<Question, Competences> joinCompetences = root.join("competences", JoinType.INNER);
                    predicates.add(cb.equal(joinCompetences.get("id"), getCompetencesId()));
                }
                predicates.add(cb.equal(root.get("status"), DigiConstant.STATUS_ACTIVE));
                return cb.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
    }
}
