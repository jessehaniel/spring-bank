package com.letscode.java.springbank.produto.rendimento;

import com.letscode.java.springbank.produto.ProdutoDTO;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RendimentoDTO {
    
    private Integer id;
    private LocalDate data;
    private Double valor;
    
    private ProdutoDTO produto;
}
