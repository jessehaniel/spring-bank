package com.letscode.java.springbank.cliente;

import com.letscode.java.springbank.gerente.Gerente;
import com.letscode.java.springbank.produto.investimento.Investimento;
import com.letscode.java.springbank.produto.solicitacao.SolicitacaoAdesao;
import java.time.LocalDate;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class Cliente {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    private String nome;
    private String email;
    private final Double capital;
    private final LocalDate clienteDesde = LocalDate.now();
    
    @OneToMany(mappedBy = "cliente")
    private List<Investimento> investimentos;
    
    @OneToMany(mappedBy = "cliente")
    private List<SolicitacaoAdesao> solicitacaoAdesaoList;
    
    @ManyToOne
    @JoinColumn(name = "gerente_id", nullable = false)
    private Gerente gerente;
    
    public Cliente() {
        this.capital = 10000.0;
    }
    
    public Cliente(Double capital) {
        this.capital = capital;
    }
    
//    public void validateProdutoJaNaCarteira(Produto produtoInvestimento) {
//        if (this.containsProdutoInvestimentoNaCarteira(produtoInvestimento)) {
//            throw new ClienteJaPossuiProdutoException(this, produtoInvestimento);
//        }
//    }

//    public boolean containsProdutoInvestimentoNaCarteira(Produto produtoInvestimento) {
//        return this.investimentos.stream()
//            .filter(investimento -> Objects.isNull(investimento.getDataEncerramento()))
//            .map(Investimento::getProduto)
//            .anyMatch(produtoInvestimento::equals);
//    }

//    public void adicionarProdutoNaCarteira(Produto produtoInvestimento) {
//        validateProdutoJaNaCarteira(produtoInvestimento);
//        this.investimentos.add(new Investimento(this, produtoInvestimento));
//    }

//    public Double calcularTotalCapitalInvestido() {
//        return this.investimentos.stream()
//            .filter(investimento -> Objects.isNull(investimento.getDataEncerramento()))
//            .map(Investimento::getValor)
//            .reduce(0.0, Double::sum);
//    }

//    public Double getSaldoDisponivel() {
//        return this.capital - calcularTotalCapitalInvestido();
//    }

//    public SolicitacaoAdesao solicitarAdesao(Produto produtoInvestimento) {
//        validateSolicitacaoEmAnalise(produtoInvestimento);
//
//        final SolicitacaoAdesao solicitacaoAdesao = new SolicitacaoAdesao(this, produtoInvestimento);
//        this.solicitacaoAdesaoList.add(solicitacaoAdesao);
//        return solicitacaoAdesao;
//    }

//    private void validateSolicitacaoEmAnalise(Produto produtoInvestimento) {
//        final boolean solicitacaoJaFeita = this.solicitacaoAdesaoList.stream()
//            .filter(solicitacaoAdesao -> StatusSolicitacao.EM_ANALISE.equals(solicitacaoAdesao.getStatusSolicitacao()))
//            .map(SolicitacaoAdesao::getProduto)
//            .anyMatch(prod -> prod.equals(produtoInvestimento));
//        if (solicitacaoJaFeita) {
//            throw new SolicitacaoJaFeitaException(this, produtoInvestimento);
//        }
//    }
}
