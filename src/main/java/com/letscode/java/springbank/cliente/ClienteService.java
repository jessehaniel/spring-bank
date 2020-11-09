package com.letscode.java.springbank.cliente;

import com.letscode.java.springbank.exceptions.RecursoJaExisteException;
import com.letscode.java.springbank.exceptions.RecursoNaoEncontradoException;
import com.letscode.java.springbank.gerente.GerenteConverter;
import com.letscode.java.springbank.gerente.GerenteDTO;
import com.letscode.java.springbank.produto.ProdutoConverter;
import com.letscode.java.springbank.produto.ProdutoDTO;
import com.letscode.java.springbank.produto.investimento.Investimento;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ClienteService {
    
    private final ClienteRepository repository;
    
    public List<ClienteDTO> listarTodos() {
        return ClienteConverter.parseClienteDtoList(this.repository.findAll());
    }
    
    public ClienteDTO getPorId(Integer id) {
        return this.findByIdOptional(id)
            .map(ClienteConverter::parseClienteDto)
            .orElseThrow(() -> new RecursoNaoEncontradoException(Cliente.class));
    }
    
    private Cliente getCliente(Integer id) {
        return this.findByIdOptional(id)
            .orElseThrow(() -> new RecursoNaoEncontradoException(Cliente.class));
    }
    
    private Optional<Cliente> findByIdOptional(Integer id) {
        return this.repository.findById(id);
    }
    
    public ClienteDTO inserir(ClienteDTO clienteDTO) {
        Cliente cliente = validateCliente(clienteDTO);
        return ClienteConverter.parseClienteDto(this.repository.save(cliente));
    }
    
    private Cliente validateCliente(ClienteDTO clienteDTO) {
        if (Optional.ofNullable(clienteDTO.getId()).isPresent()) {
            validateAlreadyExists(clienteDTO.getId());
        }
        clienteDTO.setId(null);
        return ClienteConverter.getCliente(clienteDTO);
    }
    
    private void validateAlreadyExists(Integer id) {
        final Optional<Cliente> optionalCliente = this.repository.findById(id);
        if (optionalCliente.isPresent()) {
            throw new RecursoJaExisteException(Cliente.class);
        }
    }
    
    public ClienteDTO atualizar(Integer id, ClienteDTO clienteDTO) {
        final Cliente cliente = updateFieldsNomeEmailGerente(this.getCliente(id), clienteDTO);
        return ClienteConverter.parseClienteDto(this.repository.save(cliente));
    }
    
    private Cliente updateFieldsNomeEmailGerente(Cliente cliente, ClienteDTO clienteDTO) {
        cliente.setNome(clienteDTO.getNome());
        cliente.setEmail(clienteDTO.getEmail());
        cliente.setGerente(GerenteConverter.getGerente(clienteDTO.getGerente()));
        return cliente;
    }
    
    public void excluir(Integer id) {
        final Cliente cliente = this.getCliente(id);
        this.repository.delete(cliente);
    }
    
    public List<ClienteDTO> getPorGerente(GerenteDTO gerenteDTO){
        return ClienteConverter.parseClienteDtoList(this.repository.findAllByGerenteId(gerenteDTO.getId()));
    }
    
    public Double calcularTotalInvestimentosCarteiraClientes(GerenteDTO gerenteDTO) {
        return this.getPorGerente(gerenteDTO).stream()
            .map(this::calcularTotalCapitalInvestido)
            .reduce(0.0, Double::sum);
    }
    
    private Double calcularTotalCapitalInvestido(ClienteDTO clienteDTO) {
        return clienteDTO.getInvestimentos().stream()
            .filter(investimento -> Objects.isNull(investimento.getDataEncerramento()))
            .map(Investimento::getValor)
            .reduce(0.0, Double::sum);
    }
    
    public boolean contemProdutoInvestimentoNaCarteira(ClienteDTO clienteDTO, ProdutoDTO produtoDTO) {
        return this.getPorId(clienteDTO.getId()).getInvestimentos().stream()
            .filter(investimento -> Objects.isNull(investimento.getDataEncerramento()))
            .map(Investimento::getProduto)
            .anyMatch(produto -> ProdutoConverter.getProduto(produtoDTO).equals(produto));
    }

}
