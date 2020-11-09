package com.letscode.java.springbank.produto;

import com.letscode.java.springbank.exceptions.RecursoJaExisteException;
import com.letscode.java.springbank.exceptions.RecursoNaoEncontradoException;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ProdutoService {
    
    private final ProdutoRepository repository;
    
    public ProdutoDTO inserir(ProdutoDTO produtoDTO) {
        Produto produto = validateProduto(produtoDTO);
        return ProdutoConverter.parseProdutoDto(repository.save(produto));
    }
    
    private Produto validateProduto(ProdutoDTO produtoDTO) {
        if (Optional.ofNullable(produtoDTO.getId()).isPresent()) {
            validateAlreadyExists(produtoDTO.getId());
        }
        produtoDTO.setId(null);
        return ProdutoConverter.getProduto(produtoDTO);
    }
    
    private void validateAlreadyExists(Integer id) {
        final Optional<Produto> optionalProduto = this.repository.findById(id);
        if (optionalProduto.isPresent()) {
            throw new RecursoJaExisteException(Produto.class);
        }
    }
    
    public ProdutoDTO atualizar(Integer id, ProdutoDTO produto) {
        return this.findOptionalById(id)
            .map(p -> inserir(id, produto))
            .orElseThrow(() -> new RecursoNaoEncontradoException(Produto.class));
    }
    
    private ProdutoDTO inserir(Integer id, ProdutoDTO produto) {
        final Produto produtoCompleto = new Produto(id, produto.getTitulo(), produto.getDescricao(),
            produto.getValor());
        return ProdutoConverter.parseProdutoDto(repository.save(produtoCompleto));
    }
    
    public void excluir(Integer id) {
        final Produto produto = this.getProduto(id);
        this.repository.delete(produto);
    }
    
    public List<ProdutoDTO> listarTodos() {
        return ProdutoConverter.parseProdutoDtoList(this.repository.findAll());
    }
    
    public Produto getProduto(Integer id) {
        return findOptionalById(id)
            .orElseThrow(() -> new RecursoNaoEncontradoException(Produto.class));
    }
    
    private Optional<Produto> findOptionalById(Integer id) {
        return this.repository.findById(id);
    }
    
    public List<ProdutoDTO> consultarPorDescricaoParcial(String descricaoParcial) {
        return ProdutoConverter.parseProdutoDtoList(
            this.repository.findByDescricaoContaining(descricaoParcial)
        );
    }
    
    public ProdutoDTO getPorId(Integer id) {
        return this.findOptionalById(id)
            .map(ProdutoConverter::parseProdutoDto)
            .orElseThrow(() -> new RecursoNaoEncontradoException(Produto.class));
    }
}
