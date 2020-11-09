package com.letscode.java.springbank.exemplos.user.specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

public class UserSpecification implements Specification<MyUser> {
    
    private final SearchCriteria criteria;
    
    public UserSpecification(SearchCriteria criteria) {
        this.criteria = criteria;
    }
    
    @Override
    public Predicate toPredicate(Root<MyUser> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        
        if (criteria.getOperation().equalsIgnoreCase(">")) {
            return builder.greaterThanOrEqualTo(
                root.get(criteria.getKey()), criteria.getValue().toString());
        } else if (criteria.getOperation().equalsIgnoreCase("<")) {
            return builder.lessThanOrEqualTo(
                root.get(criteria.getKey()), criteria.getValue().toString());
        } else if (criteria.getOperation().equalsIgnoreCase(":")) {
            if (root.get(criteria.getKey()).getJavaType() == String.class) {
                return builder.like(
                    builder.upper(root.get(criteria.getKey())),
                    "%" + criteria.getValue().toString().toUpperCase() + "%"
                );
            } else {
                return builder.equal(root.get(criteria.getKey()), criteria.getValue());
            }
        }
        return null;
    }
}

