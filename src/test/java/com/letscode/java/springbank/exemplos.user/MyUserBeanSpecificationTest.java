package com.letscode.java.springbank.exemplos.user;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.in;
import static org.hamcrest.Matchers.not;

import com.letscode.java.springbank.exemplos.user.specification.MyUser;
import com.letscode.java.springbank.exemplos.user.specification.MyUserRepository;
import com.letscode.java.springbank.exemplos.user.specification.SearchCriteria;
import com.letscode.java.springbank.exemplos.user.specification.UserSpecification;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.jpa.domain.Specification;

@DataJpaTest
public class MyUserBeanSpecificationTest {
    
    @Autowired
    private MyUserRepository repository;
    
    private MyUser userJohn;
    private MyUser userTom;
    
    @BeforeEach
    public void init() {
        userJohn = new MyUser();
        userJohn.setFirstName("John");
        userJohn.setLastName("Doe");
        userJohn.setEmail("john@doe.com");
        userJohn.setAge(22);
        repository.save(userJohn);
        
        userTom = new MyUser();
        userTom.setFirstName("Tom");
        userTom.setLastName("Doe");
        userTom.setEmail("tom@doe.com");
        userTom.setAge(26);
        repository.save(userTom);
    }
    
    @Test
    public void givenLast_whenGettingListOfUsers_thenCorrect() {
        UserSpecification spec =
            new UserSpecification(new SearchCriteria("lastName", ":", "doe"));
        
        List<MyUser> results = repository.findAll(spec);
        
        assertThat(userJohn, in(results));
        assertThat(userTom, in(results));
    }
    
    @Test
    public void givenFirstAndLastName_whenGettingListOfUsers_thenCorrect() {
        UserSpecification spec1 =
            new UserSpecification(new SearchCriteria("firstName", ":", "john"));
        UserSpecification spec2 =
            new UserSpecification(new SearchCriteria("lastName", ":", "doe"));
        
        List<MyUser> results = repository.findAll(Specification.where(spec1).and(spec2));
        
        assertThat(userJohn, in(results));
        assertThat(userTom, not(in(results)));
    }
    
    @Test
    public void givenLastAndAge_whenGettingListOfUsers_thenCorrect() {
        UserSpecification spec1 =
            new UserSpecification(new SearchCriteria("age", ">", "25"));
        UserSpecification spec2 =
            new UserSpecification(new SearchCriteria("lastName", ":", "doe"));
        
        List<MyUser> results =
            repository.findAll(Specification.where(spec1).and(spec2));
        
        assertThat(userTom, in(results));
        assertThat(userJohn, not(in(results)));
    }
    
    @Test
    public void givenWrongFirstAndLast_whenGettingListOfUsers_thenCorrect() {
        UserSpecification spec1 =
            new UserSpecification(new SearchCriteria("firstName", ":", "Adam"));
        UserSpecification spec2 =
            new UserSpecification(new SearchCriteria("lastName", ":", "Fox"));
        
        List<MyUser> results =
            repository.findAll(Specification.where(spec1).and(spec2));
        
        assertThat(userJohn, not(in(results)));
        assertThat(userTom, not(in(results)));
    }
    
    @Test
    public void givenPartialFirst_whenGettingListOfUsers_thenCorrect() {
        UserSpecification spec =
            new UserSpecification(new SearchCriteria("firstName", ":", "jo"));
        
        List<MyUser> results = repository.findAll(spec);
        
        assertThat(userJohn, in(results));
        assertThat(userTom, not(in(results)));
    }
    
}
