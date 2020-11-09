package com.letscode.java.springbank.produto;

import com.letscode.java.springbank.produto.rendimento.RendimentoDTO;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProdutoDTO {
    
    private Integer id;
    private String titulo;
    private String descricao;
    private Double valor;
    
    private List<RendimentoDTO> rendimentos;
}
