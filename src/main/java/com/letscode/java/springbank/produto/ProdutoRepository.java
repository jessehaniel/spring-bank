package com.letscode.java.springbank.produto;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer> {
    
    List<Produto> findByDescricaoContaining(String descricaoParcial);
}
