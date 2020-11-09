package com.letscode.java.springbank.gerente;

import com.letscode.java.springbank.cliente.ClienteDTO;
import com.letscode.java.springbank.gerente.gerentegeral.GerenteGeralDTO;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GerenteDTO {
    
    private Integer id;
    private String nome;
    private String matricula;
    
    private GerenteGeralDTO gerenteGeral;
    private List<ClienteDTO> clientes;
}

