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
        Authority gerenteGeral = authorityRepository.save(Authority.builder().role("ROLE_GERENTE_GERAL").build());
        Authority gerente = authorityRepository.save(Authority.builder().role("ROLE_GERENTE").build());
        Authority cliente = authorityRepository.save(Authority.builder().role("ROLE_CLIENTE").build());
        Authority guest = authorityRepository.save(Authority.builder().role("ROLE_GUEST").build());

        userRepository.save(User.builder()
                .username("gerenteGeral")
                .password(passwordEncoder.encode("admin123"))
                .authority(gerenteGeral)
                .build());

        userRepository.save(User.builder()
                .username("gerente")
                .password(passwordEncoder.encode("password"))
                .authority(gerente)
                .build());

        userRepository.save(User.builder()
                .username("cliente")
                .password(passwordEncoder.encode("aluno"))
                .authority(cliente)
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
