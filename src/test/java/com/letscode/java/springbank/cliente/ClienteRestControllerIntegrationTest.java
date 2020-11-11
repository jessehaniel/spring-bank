package com.letscode.java.springbank.cliente;

import static com.letscode.java.springbank.cliente.PageableAssert.assertThat;
import static org.mockito.Mockito.verify;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.letscode.java.springbank.BaseIT;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.test.context.support.WithMockUser;

@SpringBootTest
class ClienteRestControllerIntegrationTest extends BaseIT {
    
    @MockBean
    private ClienteService service;
    
    @WithMockUser
    @Test
    void evaluatesPageableParameter() throws Exception {
        mockMvc.perform(
            get("/clientes/page")
                .param("page", "1")
                .param("size", "11")
                .param("sort", "id,desc")   // <-- no space after comma!
                .param("sort", "nome,asc") // <-- no space after comma!
        ).andExpect(status().isOk());
        ArgumentCaptor<Pageable> pageableCaptor = ArgumentCaptor.forClass(Pageable.class);
        verify(service).listarPaginado(pageableCaptor.capture());
        PageRequest pageable = (PageRequest) pageableCaptor.getValue();
        
        assertThat(pageable).hasPageNumber(1);
        assertThat(pageable).hasPageSize(11);
        assertThat(pageable).hasSort("nome", Sort.Direction.ASC);
        assertThat(pageable).hasSort("id", Sort.Direction.DESC);
    }
    
    @Test
    void listarTodosWithHttpBasicAuthenticationUserGuest() throws Exception {
        mockMvc.perform(get("/clientes")
            .with(httpBasic("professor", "password")))
            .andExpect(status().isOk());
    }
    
}
