package com.letscode.java.springbank.gerente.gerentegeral;

import com.letscode.java.springbank.cliente.Cliente;
import com.letscode.java.springbank.gerente.Gerente;
import com.letscode.java.springbank.gerente.GerenteDTO;
import java.util.Collections;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class GerenteGeralService {
    
    private final GerenteGeralRepository repository;
    
    public List<GerenteDTO> getSubordinadosList(Integer gerenteGeralId) {
//        return this.repository.findAllBySubordinados();
        return Collections.emptyList();
    }
    
    public List<Cliente> rankingClientes() {
//        return this.getClienteList().stream()
//            .sorted(Comparator.comparingDouble(Cliente::calcularTotalCapitalInvestido))
//            .collect(Collectors.toList());
        return Collections.emptyList();
    }
    
    public List<Gerente> rankingGerentes() {
//        this.getSubordinadosList().stream()
//            .sorted(Comparator.comparingDouble(Gerente::calcularTotalInvestimentosCarteiraCliente))
//            .collect(Collectors.toList());
        return Collections.emptyList();
    }
    
    public void analisarSolicitacoesAdesao() {
        //1- listar todos os clientes
        //2- filtrar as solicitacoes pendentes
        //3- se há saldo disponivel, concenda o investimento, senão rejeite
        //4- cadastrar o investimento na carteira do cliente
        
//        getClienteList().forEach(this::analisarSolicitacoesAdesao);
    }
    
//    private List<Cliente> getClienteList() {
//        return this.getSubordinadosList().stream()
//            .map(Gerente::getClientes)
//            .flatMap(Collection::stream)
//            .collect(Collectors.toList());
//    }
    
//    private void analisarSolicitacoesAdesao(@NonNull Cliente cliente) {
//        cliente.getSolicitacaoAdesaoList().stream()
//            .filter(solicitacaoAdesao -> StatusSolicitacao.EM_ANALISE.equals(solicitacaoAdesao.getStatusSolicitacao()))
//            .forEach(this::analisarSolicitacoesAdesao);
//    }
    
//    private void analisarSolicitacoesAdesao(SolicitacaoAdesao solicitacaoAdesao) {
//        final Cliente cliente = solicitacaoAdesao.getCliente();
//        cliente.validateProdutoJaNaCarteira(solicitacaoAdesao.getProduto());
//        this.validateSaldoDisponivel(solicitacaoAdesao, cliente);
//        this.aprovarSolicitacao(solicitacaoAdesao, cliente);
//    }
    
//    private void validateSaldoDisponivel(SolicitacaoAdesao solicitacaoAdesao, Cliente cliente) {
//        if (cliente.getSaldoDisponivel() < solicitacaoAdesao.getProduto().getValor()) {
//            solicitacaoAdesao.rejeitarSolicitacao();
//            throw new CapitalInsuficienteException();
//        }
//    }
    
//    private void aprovarSolicitacao(SolicitacaoAdesao solicitacaoAdesao, Cliente cliente) {
//        solicitacaoAdesao.aprovarSolicitacao();
//        cliente.adicionarProdutoNaCarteira(solicitacaoAdesao.getProduto());
//    }
}
