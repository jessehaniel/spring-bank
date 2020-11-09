package com.letscode.java.springbank.exemplos.user.specification;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.data.jpa.domain.Specification;

public class UserSpecificationBuilder {
    
    private final List<SearchCriteria> params;
    
    public UserSpecificationBuilder() {
        params = new ArrayList<>();
    }
    
    public UserSpecificationBuilder with(String key, String operation, Object value) {
        params.add(new SearchCriteria(key, operation, value));
        return this;
    }
    
    public Specification<MyUser> build() {
        if (params.size() == 0) {
            return null;
        }
        
        List<Specification<MyUser>> specs = params.stream()
            .map(UserSpecification::new)
            .collect(Collectors.toList());
    
        Specification<MyUser> result = specs.get(0);
        
        for (int i = 1; i < params.size(); i++) {
            result = Specification.where(result).and(specs.get(i));
        }
        return result;
    }
}
