package com.letscode.java.springbank.produto;

import com.letscode.java.springbank.produto.rendimento.Rendimento;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Immutable;

@NoArgsConstructor
@Data
@Immutable
@Entity
public class Produto {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String titulo;
    private String descricao;
    private Double valor;
    
    @OneToMany(mappedBy = "produto", fetch = FetchType.LAZY)
    private List<Rendimento> rendimentos;
    
    public Produto(String titulo, String descricao, Double valor) {
        this.id = null;
        this.titulo = titulo;
        this.descricao = descricao;
        this.valor = valor;
    }
    
    public Produto(Integer id, String titulo, String descricao, Double valor) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.valor = valor;
    }
    
}
