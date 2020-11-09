package com.letscode.java.springbank.exemplos.user.specification;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface MyUserRepository extends JpaRepository<MyUser, Long>, JpaSpecificationExecutor<MyUser> {

}
