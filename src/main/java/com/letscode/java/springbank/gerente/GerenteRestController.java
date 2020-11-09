package com.letscode.java.springbank.gerente;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/gerentes")
@RestController
public class GerenteRestController {

    private final GerenteService service;
    
    @GetMapping
    public List<GerenteDTO> getAll() {
        return this.service.listarTodos();
    }
    
    @GetMapping("/{id:[\\d+]}")
    public GerenteDTO getPorId(@PathVariable Integer id){
        return this.service.getPorId(id);
    }
}
