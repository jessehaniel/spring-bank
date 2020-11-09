package com.letscode.java.springbank.produto.rendimento;

import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RendimentoRepository extends JpaRepository<Rendimento, Integer> {
    
    List<Rendimento> findAllByProdutoIdAndDataBetween(Integer idProduto, LocalDate dataInicio, LocalDate dataFim);

}
