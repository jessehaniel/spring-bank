package com.letscode.java.springbank.produto.investimento;

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

@NoArgsConstructor
@Data
@Entity
public class Investimento {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable=false)
    private Cliente cliente;
    
    @ManyToOne
    @JoinColumn(name = "produto_id", nullable=false)
    private Produto produto;
    
    private final LocalDate dataAdesao = LocalDate.now();
    private LocalDate dataEncerramento;
    
    public Investimento(Cliente cliente, Produto produto) {
        this.cliente = cliente;
        this.produto = produto;
    }
    
    public Investimento(Cliente cliente, Produto produto, LocalDate dataEncerramento) {
        this.cliente = cliente;
        this.produto = produto;
        this.dataEncerramento = dataEncerramento;
    }
    
    public Double getValor() {
        return produto.getValor();
    }
    
    public void encerrarInvestimento() {
        this.dataEncerramento = LocalDate.now();
    }
    
}
