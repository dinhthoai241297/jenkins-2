package com.digi.api.storage.criteria;

import com.digi.api.storage.model.Category;
import lombok.Data;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

@Data
public class CategoryCriteria {
    private Integer id;
    private String name;
    private Integer parentId;
    private Integer status;

    public Specification<Category> getSpecification() {
        return new Specification<Category>() {
            private static final long serialVersionUID = 1L;
            @Override
            public Predicate toPredicate(Root<Category> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<>();
                if(getId() != null){
                    predicates.add(cb.equal(root.get("id"), getId()));
                }
                if(getParentId() != null){
                    Join<Category, Category> joinParent = root.join("parentCategory", JoinType.INNER);
                    predicates.add(cb.equal(joinParent.get("id"), getParentId()));
                }
                if(!StringUtils.isEmpty(getName())){
                    predicates.add(cb.like(cb.lower(root.get("name")), "%"+getName().toLowerCase()+"%"));
                }
                if(getStatus() != null){
                    predicates.add(cb.equal(root.get("status"), getStatus()));
                }
                return cb.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
    }
}
