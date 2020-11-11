package com.letscode.java.springbank.security;

import com.letscode.java.springbank.security.userauthority.Authority;
import com.letscode.java.springbank.security.userauthority.User;
import com.letscode.java.springbank.security.userauthority.UserRepository;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

@Log4j2
@RequiredArgsConstructor
@Service
public class JpaUserDetailsService implements UserDetailsService {
    
    private final UserRepository userRepository;
    
    @Transactional // para poder carregar as informações de Authrorities -> Set<Authority> authorities
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        
        log.debug("Getting UserBean info via JPA");
        
        User user = userRepository.findByUsername(username)
            .orElseThrow(() -> new UsernameNotFoundException("UserBean name: " + username + " not found"));
        
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
            user.getEnabled(), user.getAccountNonExpired(), user.getCredentialsNonExpired(),
            user.getAccountNonLocked(), convertToSpringAuthorities(user.getAuthorities()));
    }
    
    private Collection<? extends GrantedAuthority> convertToSpringAuthorities(Set<Authority> authorities) {
        if (CollectionUtils.isEmpty(authorities)) {
            return new HashSet<>();
        }
        
        return authorities.stream()
            .map(Authority::getRole)
            .map(SimpleGrantedAuthority::new)
            .collect(Collectors.toSet());
    }
}
