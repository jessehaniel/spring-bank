package com.letscode.java.springbank.gerente.gerentegeral;

import com.letscode.java.springbank.gerente.GerenteDTO;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GerenteGeralDTO {
        
        private Integer id;
        private String nome;
        private String matricula;
        private List<GerenteDTO> subordinados;
}
