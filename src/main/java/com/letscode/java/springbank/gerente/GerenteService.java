package com.letscode.java.springbank.gerente;

import com.letscode.java.springbank.cliente.ClienteDTO;
import com.letscode.java.springbank.exceptions.RecursoNaoEncontradoException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class GerenteService {
    
    private final GerenteRepository repository;
//    private final ClienteService clienteService;
    
    public List<GerenteDTO> listarTodos() {
        return GerenteConverter.parseGerenteDtoList(this.repository.findAll());
    }
    
    public GerenteDTO getPorId(Integer id) {
        return GerenteConverter.parseGerenteDto(this.getGerente(id));
    }
    
    public Gerente getGerente(Integer id) {
        return this.repository.findById(id)
            .orElseThrow(() -> new RecursoNaoEncontradoException(Gerente.class));
    }
    
    public List<ClienteDTO> findAllClientes(GerenteDTO gerente) {
        return this.getPorId(gerente.getId()).getClientes();
    }
    
//    public void enviarMensagemDireta(GerenteDTO remetente, ProdutoDTO produtoDTO) {
//        final List<ClienteDTO> destinatarios = this.getPorId(remetente.getId()).getClientes().stream()
//            .filter(cliente -> !clienteService.contemProdutoInvestimentoNaCarteira(cliente, produtoDTO))
//            .collect(Collectors.toList());
//        enviarMensagemDireta(remetente, produtoDTO, destinatarios);
//    }

//    private void enviarMensagemDireta(GerenteDTO gerente, ProdutoDTO produto, List<ClienteDTO> destinatarios) {
//        MensagemDireta.enviarMalaDireta(gerente, destinatarios, produto);
//    }
    
//    public Double calcularTotalInvestimentosCarteiraClientes(Integer gerenteId) {
//        final GerenteDTO gerenteDTO = this.getPorId(gerenteId);
//        return clienteService.calcularTotalInvestimentosCarteiraClientes(gerenteDTO);
//    }
    
}
