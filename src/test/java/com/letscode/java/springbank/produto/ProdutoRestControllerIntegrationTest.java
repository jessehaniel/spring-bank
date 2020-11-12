package com.letscode.java.springbank.produto;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.letscode.java.springbank.BaseIT;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
public class ProdutoRestControllerIntegrationTest extends BaseIT {
    
    @MockBean
    private ProdutoService service;
    
    @Test
    void listarTodosProdutosUnauthorized() throws Exception {
        mockMvc.perform(get("/produtos"))
            .andExpect(status().isUnauthorized());
    }
    
    @Test
    void listarTodosProdutos() throws Exception {
        mockMvc.perform(
            get("/produtos")
                .with(httpBasic("guest", "guest123")))
            .andExpect(status().isOk());
    }
    
    @Test
    void listarTodosProdutosHttpBasicAuthentication() throws Exception {
        mockMvc.perform(
            get("/produtos")
                .with(httpBasic("gerenteGeral", "admin123")))
            .andExpect(status().isOk());
    }
    
    @Test
    void listarTodosProdutosInvalidAuthentication() throws Exception {
        mockMvc.perform(
            get("/produtos")
                .with(httpBasic("gerenteGeral", "adminnnnnn")))
            .andExpect(status().isUnauthorized());
    }
}
