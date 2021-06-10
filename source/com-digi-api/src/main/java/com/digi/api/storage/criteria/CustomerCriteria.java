package com.digi.api.storage.criteria;

import com.digi.api.storage.model.Account;
import com.digi.api.storage.model.Customer;
import com.digi.api.storage.model.Province;
import lombok.Data;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

@Data
public class CustomerCriteria{
    private Long id;
    private Integer provinceId;
    private Integer sex;
    private String email;
    private String fullName;
    private String phone;

    public Specification<Customer> getSpecification() {
        return new Specification<Customer>() {
            private static final long serialVersionUID = 1L;

            @Override
            public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<>();

                if (getId() != null) {
                    predicates.add(cb.equal(root.get("id"), getId()));
                }

                Join<Customer, Province> joinProvince = root.join("province", JoinType.INNER);
                if (getProvinceId() != null) {
                    predicates.add(cb.equal(joinProvince.get("id"), getProvinceId()));
                }
                if (getSex() != null) {
                    predicates.add(cb.equal(root.get("sex"), getSex()));
                }

               Join<Customer, Account> joinAccount = root.join("account", JoinType.INNER);
                if (!StringUtils.isEmpty(getEmail())) {
                    predicates.add(cb.like(cb.lower(joinAccount.get("email")), "%" + getEmail().toLowerCase() + "%"));
                }

                if (!StringUtils.isEmpty(getPhone())) {
                    predicates.add(cb.like(cb.lower(joinAccount.get("phone")), "%" + getPhone().toLowerCase() + "%"));
                }

                if (!StringUtils.isEmpty(getFullName())) {
                    predicates.add(cb.like(cb.lower(joinAccount.get("fullName")), "%" + getFullName().toLowerCase() + "%"));
                }

                return cb.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
    }
}
