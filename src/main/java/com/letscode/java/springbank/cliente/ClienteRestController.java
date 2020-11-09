package com.letscode.java.springbank.cliente;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/clientes")
@RestController
public class ClienteRestController {
    
    private final ClienteService service;
    
    @GetMapping
    public List<ClienteDTO> getAll() {
        return this.service.listarTodos();
    }
    
    @GetMapping("/{id:[\\d+]}")
    public ClienteDTO getById(@PathVariable Integer id) {
        return this.service.getPorId(id);
    }
    
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ClienteDTO insert(@RequestBody ClienteDTO cliente) {
        return this.service.inserir(cliente);
    }
    
    @PutMapping("/{id:[\\d]+}")
    public ClienteDTO update(@PathVariable Integer id, @RequestBody ClienteDTO cliente) {
        return this.service.atualizar(id, cliente);
    }
    
    @DeleteMapping("/{id:[\\d]+}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) {
        this.service.excluir(id);
    }
}
