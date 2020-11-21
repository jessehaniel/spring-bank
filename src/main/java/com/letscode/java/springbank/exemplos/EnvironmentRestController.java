package com.letscode.java.springbank.exemplos;

import java.util.Arrays;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/env")
@RestController
public class EnvironmentRestController {

    @Value("${spring.application.name}")
    private String applicationName;
    
    private final Environment env;
    
//    public EnvironmentRestController(Environment env) {
//        this.env = env;
//    }
    
    @GetMapping
    public String getApplicationName() {
        return this.applicationName;
    }
    
    @GetMapping("/property")
    public String getApplicationNameByEnvironment() {
        return this.env.getProperty("spring.application.name");
    }
    
    @GetMapping("/active-profiles")
    public List<String> getActiveProfiles() {
        return Arrays.asList(this.env.getActiveProfiles());
    }

}




