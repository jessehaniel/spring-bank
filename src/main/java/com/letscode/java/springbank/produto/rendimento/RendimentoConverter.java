package com.letscode.java.springbank.produto.rendimento;

import com.letscode.java.springbank.produto.ProdutoConverter;
import java.util.List;
import java.util.stream.Collectors;

public class RendimentoConverter {
    
    public static RendimentoDTO parseRendimentoDto(Rendimento rendimento) {
        return RendimentoDTO.builder()
            .id(rendimento.getId())
            .produto(ProdutoConverter.parseProdutoDto(rendimento.getProduto()))
            .data(rendimento.getData())
            .valor(rendimento.getValor())
            .build();
    }
    
    private static Rendimento getRendimento(RendimentoDTO rendimentoDTO) {
        final Rendimento rendimento = new Rendimento();
        rendimento.setId(rendimentoDTO.getId());
        rendimento.setData(rendimentoDTO.getData());
        rendimento.setValor(rendimentoDTO.getValor());
        rendimento.setProduto(ProdutoConverter.getProduto(rendimentoDTO.getProduto()));
        return rendimento;
    }
    
    public static List<RendimentoDTO> parseRendimentoDtoList(List<Rendimento> rendimentos) {
        return rendimentos.parallelStream()
            .map(RendimentoConverter::parseRendimentoDto)
            .collect(Collectors.toList());
    }
    
    public static List<Rendimento> getRendimentoList(List<RendimentoDTO> rendimentoDTOList) {
        return rendimentoDTOList.parallelStream()
            .map(RendimentoConverter::getRendimento)
            .collect(Collectors.toList());
    }
}
