package com.letscode.java.springbank.gerente;

import com.letscode.java.springbank.cliente.Cliente;
import com.letscode.java.springbank.gerente.gerentegeral.GerenteGeral;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Entity
public class Gerente {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    private String nome;
    private String matricula;
    
    @ManyToOne
    private GerenteGeral gerenteGeral;
    
    @OneToMany(mappedBy = "gerente")
    private List<Cliente> clientes;
    
    public GerenteDTO parseDTO() {
        return GerenteConverter.parseGerenteDto(this);
    }
}
