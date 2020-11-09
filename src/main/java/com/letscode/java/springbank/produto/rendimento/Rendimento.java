package com.letscode.java.springbank.produto.rendimento;

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
public class Rendimento {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    private LocalDate data;
    private Double valor;
    
    @ManyToOne
    @JoinColumn(name = "produto_id")
    private Produto produto;
}
