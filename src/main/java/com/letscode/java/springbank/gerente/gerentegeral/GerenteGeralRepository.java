package com.letscode.java.springbank.gerente.gerentegeral;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GerenteGeralRepository extends JpaRepository<GerenteGeral, Integer> {

}
