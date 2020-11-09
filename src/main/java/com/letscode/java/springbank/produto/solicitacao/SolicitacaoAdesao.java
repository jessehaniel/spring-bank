package com.letscode.java.springbank.produto.solicitacao;

import com.letscode.java.springbank.cliente.Cliente;
import com.letscode.java.springbank.produto.Produto;
import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Data
@Entity
public class SolicitacaoAdesao {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable=false)
    private Cliente cliente;
    
    @ManyToOne
    @JoinColumn(name = "produto_id", nullable=false)
    private Produto produto;
    
    private final LocalDate dataSolicitacao = LocalDate.now();
    
    @Setter
    private StatusSolicitacao statusSolicitacao = StatusSolicitacao.EM_ANALISE;
    
    public SolicitacaoAdesao(Cliente cliente, Produto produto) {
        this.cliente = cliente;
        this.produto = produto;
    }
    
    public void aprovarSolicitacao() {
        this.statusSolicitacao = StatusSolicitacao.CONCEDIDO;
    }
    
    public void rejeitarSolicitacao() {
        this.statusSolicitacao = StatusSolicitacao.REJEITADO;
    }
}

