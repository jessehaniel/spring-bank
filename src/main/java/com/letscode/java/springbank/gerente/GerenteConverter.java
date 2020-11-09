package com.letscode.java.springbank.gerente;

import com.letscode.java.springbank.cliente.ClienteConverter;
import com.letscode.java.springbank.gerente.gerentegeral.GerenteGeral;
import com.letscode.java.springbank.gerente.gerentegeral.GerenteGeralDTO;
import java.util.List;
import java.util.stream.Collectors;

public class GerenteConverter {
    
    private GerenteConverter() {
        super();
    }
    
    public static Gerente getGerente(GerenteDTO dto) {
        final Gerente gerente = new Gerente();
        gerente.setId(dto.getId());
        gerente.setNome(dto.getNome());
        gerente.setMatricula(dto.getMatricula());
        gerente.setClientes(ClienteConverter.getClienteList(dto.getClientes()));
        gerente.setGerenteGeral(getGerenteGeral(dto.getGerenteGeral()));
        return gerente;
    }
    
    public static GerenteGeral getGerenteGeral(GerenteGeralDTO dto) {
        final GerenteGeral gerenteGeral = new GerenteGeral();
        gerenteGeral.setId(dto.getId());
        gerenteGeral.setNome(dto.getNome());
        gerenteGeral.setMatricula(dto.getMatricula());
        gerenteGeral.setSubordinados(getGerenteList(dto.getSubordinados()));
        return gerenteGeral;
    }
    
    public static List<Gerente> getGerenteList(List<GerenteDTO> gerenteDTOList) {
        return gerenteDTOList.parallelStream()
            .map(GerenteConverter::getGerente)
            .collect(Collectors.toList());
    }
    
    public static GerenteDTO parseGerenteDto(Gerente gerente) {
        return GerenteDTO.builder()
            .id(gerente.getId())
            .nome(gerente.getNome())
            .matricula(gerente.getMatricula())
            .clientes(ClienteConverter.parseClienteDtoList(gerente.getClientes()))
            .gerenteGeral(GerenteConverter.parseGerenteGeralDto(gerente.getGerenteGeral()))
            .build();
    }
    
    public static GerenteGeralDTO parseGerenteGeralDto(GerenteGeral gerenteGeral) {
        return GerenteGeralDTO.builder()
            .id(gerenteGeral.getId())
            .nome(gerenteGeral.getNome())
            .matricula(gerenteGeral.getMatricula())
            .subordinados(GerenteConverter.parseGerenteDtoList(gerenteGeral.getSubordinados()))
            .build();
    }
    
    public static List<GerenteDTO> parseGerenteDtoList(List<Gerente> gerenteList) {
        return gerenteList.parallelStream()
            .map(GerenteConverter::parseGerenteDto)
            .collect(Collectors.toList());
    }
}
