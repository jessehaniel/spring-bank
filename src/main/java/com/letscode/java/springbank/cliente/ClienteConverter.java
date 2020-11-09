package com.letscode.java.springbank.cliente;

import java.util.List;
import java.util.stream.Collectors;

public class ClienteConverter {
    
    private ClienteConverter() {
        super();
    }
    
    public static Cliente getCliente(ClienteDTO dto) {
        final Cliente cliente = new Cliente();
        cliente.setId(dto.getId());
        cliente.setNome(dto.getNome());
        cliente.setEmail(dto.getEmail());
        return cliente;
    }
    
    public static ClienteDTO parseClienteDto(Cliente cliente) {
        return ClienteDTO.builder()
            .id(cliente.getId())
            .nome(cliente.getNome())
            .email(cliente.getEmail())
            .gerente(cliente.getGerente().parseDTO())
            .build();
    }
    
    public static List<Cliente> getClienteList(List<ClienteDTO> dtoList) {
        return dtoList.parallelStream()
            .map(ClienteConverter::getCliente)
            .collect(Collectors.toList());
    }
    
    public static List<ClienteDTO> parseClienteDtoList(List<Cliente> clienteList) {
        return clienteList.parallelStream()
            .map(ClienteConverter::parseClienteDto)
            .collect(Collectors.toList());
    }
}
