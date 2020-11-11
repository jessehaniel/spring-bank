package com.letscode.java.springbank.bootstrap;

import com.letscode.java.springbank.security.userauthority.Authority;
import com.letscode.java.springbank.security.userauthority.AuthorityRepository;
import com.letscode.java.springbank.security.userauthority.User;
import com.letscode.java.springbank.security.userauthority.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Log4j2
@RequiredArgsConstructor
@Component
public class UserDataLoader implements CommandLineRunner {

    private final AuthorityRepository authorityRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    private void loadSecurityData() {
        Authority admin = authorityRepository.save(Authority.builder().role("ADMIN").build());
        Authority userRole = authorityRepository.save(Authority.builder().role("PROFESSOR").build());
        Authority customer = authorityRepository.save(Authority.builder().role("ALUNO").build());
        Authority guest = authorityRepository.save(Authority.builder().role("GUEST").build());

        userRepository.save(User.builder()
                .username("letscode")
                .password(passwordEncoder.encode("admin123"))
                .authority(admin)
                .build());

        userRepository.save(User.builder()
                .username("professor")
                .password(passwordEncoder.encode("password"))
                .authority(userRole)
                .build());

        userRepository.save(User.builder()
                .username("aluno")
                .password(passwordEncoder.encode("aluno"))
                .authority(customer)
                .build());
        
        userRepository.save(User.builder()
                .username("guest")
                .password(passwordEncoder.encode("guest123"))
                .authority(guest)
                .build());

        log.debug("Users Loaded: " + userRepository.count());
    }

    @Override
    public void run(String... args) throws Exception {
        if (authorityRepository.count() == 0) {
            loadSecurityData();
        }
    }

}
