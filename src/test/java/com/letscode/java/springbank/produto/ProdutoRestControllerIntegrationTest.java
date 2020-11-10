package com.letscode.java.springbank.produto;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(controllers = ProdutoRestController.class)
public class ProdutoRestControllerIntegrationTest {
    
    @Autowired
    private MockMvc mockMvc;
    
    @MockBean
    private ProdutoService service;
    
    @Test
    void listarTodosProdutosUnauthorized() throws Exception {
        mockMvc.perform(get("/produtos"))
            .andExpect(status().isUnauthorized());
    }
    
    @WithMockUser
    @Test
    void listarTodosProdutos() throws Exception {
        mockMvc.perform(get("/produtos"))
            .andExpect(status().isOk());
    }
    
    @Test
    void listarTodosProdutosHttpBasicAuthentication() throws Exception {
        mockMvc.perform(
            get("/produtos")
            .with(httpBasic("letscode", "admin123")))
            .andExpect(status().isOk());
    }
    
    @Test
    void listarTodosProdutosInvalidAuthentication() throws Exception {
        mockMvc.perform(
            get("/produtos")
            .with(httpBasic("letscode", "adminnnnnn")))
            .andExpect(status().isUnauthorized());
    }
}
