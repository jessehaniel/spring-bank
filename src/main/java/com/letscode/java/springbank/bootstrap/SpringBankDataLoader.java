package com.letscode.java.springbank.bootstrap;

import com.letscode.java.springbank.cliente.ClienteRepository;
import com.letscode.java.springbank.gerente.GerenteRepository;
import com.letscode.java.springbank.gerente.gerentegeral.GerenteGeralRepository;
import com.letscode.java.springbank.produto.ProdutoRepository;
import com.letscode.java.springbank.produto.investimento.InvestimentoRepository;
import com.letscode.java.springbank.produto.rendimento.RendimentoRepository;
import com.letscode.java.springbank.produto.solicitacao.SolicitacaoAdesaoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Log4j2
@RequiredArgsConstructor
@Component
public class SpringBankDataLoader implements CommandLineRunner {
    
    private final ClienteRepository clienteRepository;
    private final GerenteRepository gerenteRepository;
    private final GerenteGeralRepository gerenteGeralRepository;
    private final ProdutoRepository produtoRepository;
    private final RendimentoRepository rendimentoRepository;
    private final InvestimentoRepository investimentoRepository;
    private final SolicitacaoAdesaoRepository solicitacaoRepository;
    
    @Override
    public void run(String... args) throws Exception {
        loadProdutos();
        loadRendimentos();
        loadGerentes();
        loadClientes();
        loadSolicitacoes();
        loadInvestimentos();
    }
    
    private void loadProdutos() {
        //INSERT INTO Produto (id, titulo, descricao, valor) VALUES (1, 'PETR4.SA', 'Ação PETROBRAS PN', 201.0)
        //INSERT INTO Produto (id, titulo, descricao, valor) VALUES (2, 'VALE3.SA', 'Ação VALE ON', 629.0)
        //INSERT INTO Produto (id, titulo, descricao, valor) VALUES (3, 'ITUB4.SA', 'Ação ITAUUNIBANCO PN', 247.0)
        //INSERT INTO Produto (id, titulo, descricao, valor) VALUES (4, 'BBDC4.SA', 'BRADESCO PN', 219.0)
        //INSERT INTO Produto (id, titulo, descricao, valor) VALUES (5, 'CDB', 'Certificado de Depósito Bancário', 10000.0)
        //INSERT INTO Produto (id, titulo, descricao, valor) VALUES (6, 'Tesouro Direto', 'Tesouro Direto', 30.0)
    }
    
    private void loadRendimentos() {
    
    }
    
    private void loadGerentes() {
    
    }
    
    private void loadClientes() {
    
    }
    
    private void loadSolicitacoes() {
    
    }
    
    private void loadInvestimentos() {
    
    }
}
