package com.letscode.java.springbank.cliente;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
    
    List<Cliente> findAllByGerenteId(Integer id);
}
