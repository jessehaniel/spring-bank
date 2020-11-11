package com.letscode.java.springbank.produto;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/produtos")
@RestController
public class ProdutoRestController {
    
    private final ProdutoService service;
    
    @GetMapping
    public List<ProdutoDTO> getAll(){
        return this.service.listarTodos();
    }
    
    @GetMapping("/{id:[\\d]+}")
    public ProdutoDTO getById(@PathVariable Integer id) {
        return this.service.getPorId(id);
    }
    
    @GetMapping(params = {"contains"})// URL -> /produtos?contains={contains}
    public List<ProdutoDTO> queryByPartialDescription(@RequestParam("contains") String partialDescription) {
        return this.service.consultarPorDescricaoParcial(partialDescription);
    }
    
    @Secured({"ROLE_GERENTE_GERAL", "ROLE_GERENTE"})
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProdutoDTO insert(@RequestBody ProdutoDTO produto) {
        return this.service.inserir(produto);
    }
    
    @Secured({"ROLE_GERENTE_GERAL", "ROLE_GERENTE"})
    @PutMapping("/{id}")
    public ProdutoDTO update(@PathVariable Integer id, @RequestBody ProdutoDTO produto) {
        return this.service.atualizar(id, produto);
    }
    
    @Secured({"ROLE_GERENTE_GERAL", "ROLE_GERENTE"})
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) {
        this.service.excluir(id);
    }
}
