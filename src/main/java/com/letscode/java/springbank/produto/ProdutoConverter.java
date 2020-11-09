package com.letscode.java.springbank.produto;

import com.letscode.java.springbank.produto.rendimento.RendimentoConverter;
import java.util.List;
import java.util.stream.Collectors;

public class ProdutoConverter {
    
    private  ProdutoConverter() {
        super();
    }
    
    public static Produto getProduto(ProdutoDTO dto) {
        final Produto produto = new Produto();
        produto.setId(dto.getId());
        produto.setDescricao(dto.getDescricao());
        produto.setTitulo(dto.getTitulo());
        produto.setValor(dto.getValor());
        produto.setRendimentos(RendimentoConverter.getRendimentoList(dto.getRendimentos()));
        return produto;
    }
    
    public static ProdutoDTO parseProdutoDto(Produto produto) {
        return ProdutoDTO.builder()
            .id(produto.getId())
            .descricao(produto.getDescricao())
            .titulo(produto.getTitulo())
            .valor(produto.getValor())
            .rendimentos(RendimentoConverter.parseRendimentoDtoList(produto.getRendimentos()))
            .build();
    }
    
    public static List<Produto> getProdutoList(List<ProdutoDTO> produtoDTOList) {
        return produtoDTOList.parallelStream()
            .map(ProdutoConverter::getProduto)
            .collect(Collectors.toList());
    }
    
    public static List<ProdutoDTO> parseProdutoDtoList(List<Produto> produtoList) {
        return produtoList.parallelStream()
            .map(ProdutoConverter::parseProdutoDto)
            .collect(Collectors.toList());
    }
}
