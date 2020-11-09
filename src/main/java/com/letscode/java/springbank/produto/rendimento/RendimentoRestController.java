package com.letscode.java.springbank.produto.rendimento;

import java.time.LocalDate;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class RendimentoRestController {
   
    private final RendimentoService service;
    
    //localhost:8080/produtos/5/rendimentos?dataInicio=2020-10-01&dataFim=2020-10-26
    @GetMapping(value = "/produtos/{id}/rendimentos", params = {"dataInicio", "dataFim"})
    public Double calcularRendimentoAcumulado(@PathVariable Integer id,
        @RequestParam("dataInicio") @DateTimeFormat(iso = ISO.DATE) LocalDate dataInicio,
        @RequestParam("dataFim") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataFim) {
        
        return this.service.calcularRendimentoAcumulado(id, dataInicio, dataFim);
    }
}
