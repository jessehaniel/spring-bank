package com.letscode.java.springbank.gerente.gerentegeral;

import com.letscode.java.springbank.gerente.Gerente;
import com.letscode.java.springbank.gerente.GerenteConverter;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Entity
public class GerenteGeral{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    private String nome;
    private String matricula;
    
    @OneToMany(mappedBy = "gerenteGeral")
    private List<Gerente> subordinados;
    
    public GerenteGeralDTO parseDTO() {
        return GerenteConverter.parseGerenteGeralDto(this);
    }
}

