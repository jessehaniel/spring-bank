package com.letscode.java.springbank.produto.solicitacao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SolicitacaoAdesaoRepository extends JpaRepository<SolicitacaoAdesao, Integer> {

}
