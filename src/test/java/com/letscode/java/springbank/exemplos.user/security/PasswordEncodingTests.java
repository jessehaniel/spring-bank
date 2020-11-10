package com.letscode.java.springbank.exemplos.user.security;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.LdapShaPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;

public class PasswordEncodingTests {
    
    static final String PASSWORD = "password";
    
    @Test
    void testBcrypt() {
        PasswordEncoder bcrypt = new BCryptPasswordEncoder();
        
        System.out.println(bcrypt.encode(PASSWORD));
        System.out.println(bcrypt.encode(PASSWORD));
        
        System.out.println(bcrypt.encode("admin123"));
        assertTrue(bcrypt.matches("admin123", "$2a$10$HMiL2rbcF1HjFSMi4mydAu4Uw2T1LxuemoJk9gu.jEWWWsV/oHram"));
    }
    
    @Test
    void testSha256() {
        PasswordEncoder sha256 = new StandardPasswordEncoder();
        
        System.out.println(sha256.encode(PASSWORD));
        System.out.println(sha256.encode(PASSWORD));
        
        assertTrue(sha256.matches(PASSWORD, "5dc411bd616087d9427282f95abe9093529299cb179302becd31d59bacc8ca9585c1054ce8cf959c"));
    }
    
    @Test
    void testLdap() {
        PasswordEncoder ldap = new LdapShaPasswordEncoder();
        
        System.out.println(ldap.encode(PASSWORD));
        System.out.println(ldap.encode(PASSWORD));
        
        System.out.println(ldap.encode("aluno"));
        assertTrue(ldap.matches("aluno", "{SSHA}ub2AAm+7NnZk+b657zQUn59OTy4GZdU6CgkpbA=="));
    }
    
    @Test
    void testNoOp() {
        PasswordEncoder noOp = NoOpPasswordEncoder.getInstance();
        
        System.out.println(noOp.encode(PASSWORD));
    }
}
