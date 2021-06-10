package com.digi.api.storage.criteria;

import com.digi.api.storage.model.Category;
import com.digi.api.storage.model.News;
import lombok.Data;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

@Data
public class NewsCriteria {
    private Integer id;
    private String title;
    private Integer kind;
    private Integer ordering;
    private Integer categoryId;
    private Integer status;

    public Specification<News> getSpecification() {
        return new Specification<News>() {
            private static final long serialVersionUID = 1L;

            @Override
            public Predicate toPredicate(Root<News> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<>();
                if (getId() != null) {
                    predicates.add(cb.equal(root.get("id"), getId()));
                }
                if (getKind() != null) {
                    predicates.add(cb.equal(root.get("kind"), getKind()));
                }
                if (getOrdering() != null) {
                    predicates.add(cb.equal(root.get("ordering"), getOrdering()));
                }
                if(getCategoryId() != null){
                    Join<News, Category> joinCategory = root.join("category", JoinType.INNER);
                    predicates.add(cb.equal(joinCategory.get("id"), getCategoryId()));
                }
                if (!StringUtils.isEmpty(getTitle())) {
                    predicates.add(cb.like(cb.lower(root.get("title")), "%" + getTitle().toLowerCase() + "%"));
                }
                if(getStatus() != null){
                    predicates.add(cb.equal(root.get("status"), getStatus()));
                }
                return cb.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
    }
}
