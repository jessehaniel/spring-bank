package com.letscode.java.springbank.cliente;

import com.letscode.java.springbank.gerente.GerenteDTO;
import com.letscode.java.springbank.produto.investimento.Investimento;
import com.letscode.java.springbank.produto.solicitacao.SolicitacaoAdesao;
import java.time.LocalDate;
import java.util.List;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClienteDTO {
    
    private Integer id;
    @NotBlank
    @Length(min = 5, max = 50)
    private String nome;
    @Email
    private String email;
    @Positive
    private Double capital;
    private LocalDate clienteDesde;
    @NotNull
    private GerenteDTO gerente;
    
    private List<Investimento> investimentos;
    private List<SolicitacaoAdesao> solicitacaoAdesaoList;
    
}
