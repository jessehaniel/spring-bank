package com.letscode.java.springbank.exemplos.user;

import com.letscode.java.springbank.exemplos.user.specification.MyUser;
import com.letscode.java.springbank.exemplos.user.specification.MyUserRepository;
import com.letscode.java.springbank.exemplos.user.specification.UserSpecificationBuilder;
import com.letscode.java.springbank.exemplos.user.validation.UserBean;
import com.letscode.java.springbank.exemplos.user.validation.UserBeanRepository;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class UserBeanRestController {
    
    private final UserBeanRepository repository;
    private final MyUserRepository myUserRepository;
    
    @PostMapping("/users")
    ResponseEntity<String> addUser(@Valid @RequestBody UserBean userBean) {
        this.repository.save(userBean);
        return ResponseEntity.ok("UserBean is valid");
    }
    
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        return ex.getBindingResult().getAllErrors().stream()
            .collect(Collectors.toMap(error -> ((FieldError) error).getField(), error -> error.getDefaultMessage())
        );
    }
    
    @GetMapping("/users")
    @ResponseBody
    public List<MyUser> search(@RequestParam(value = "search") String search) {
        UserSpecificationBuilder builder = new UserSpecificationBuilder();
        Pattern pattern = Pattern.compile("(\\w+?)(:|<|>)(\\w+?),");
        Matcher matcher = pattern.matcher(search + ",");
        while (matcher.find()) {
            builder.with(matcher.group(1), matcher.group(2), matcher.group(3));
        }
        
        Specification<MyUser> spec = builder.build();
        return this.myUserRepository.findAll(spec);
    }
}
