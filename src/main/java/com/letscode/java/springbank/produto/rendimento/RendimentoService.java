package com.letscode.java.springbank.produto.rendimento;

import java.time.LocalDate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RendimentoService {
    
    private final RendimentoRepository repository;
    
    public Double calcularRendimentoAcumulado(Integer idProduto, LocalDate dataInicio, LocalDate dataFim) {
        return this.repository.findAllByProdutoIdAndDataBetween(idProduto, dataInicio, dataFim).parallelStream()
            .map(Rendimento::getValor)
            .reduce(0.0, Double::sum);
    }
}
