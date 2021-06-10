package com.digi.api.storage.criteria;

import com.digi.api.storage.model.Account;
import com.digi.api.storage.model.Customer;
import lombok.Data;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.*;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Data
public class CustomerAutoCompleteCriteria{
    @NotEmpty
    private String phone;

    public Specification<Customer> getSpecification() {
        return new Specification<Customer>() {
            private static final long serialVersionUID = 1L;

            @Override
            public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<>();

               Join<Customer, Account> joinAccount = root.join("account", JoinType.INNER);

                if (!StringUtils.isEmpty(getPhone())) {
                    predicates.add(cb.like(cb.lower(joinAccount.get("phone")), "%" + getPhone().toLowerCase() + "%"));
                    predicates.add(cb.like(cb.lower(joinAccount.get("phone")), "%" + ("ADMIN_"+getPhone()).toLowerCase() + "%"));
                }
                return cb.or(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
    }
}
