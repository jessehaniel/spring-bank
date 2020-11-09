package com.letscode.java.springbank.cliente;

import org.junit.jupiter.api.Test;

class ClienteTest {
    
    @Test
    void nameValidation(){
        //@NotBlank
        String name = null;
        name = ""; name.isEmpty();
        name = "   "; name.trim().isEmpty();
    }
    
}
