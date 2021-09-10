package com.demo.msrepository.model.parser;

import com.demo.msrepository.model.dto.ProdutoDTO;
import com.demo.msrepository.model.entity.Produto;
import org.springframework.stereotype.Component;

@Component
public class ProdutoParser {

    public Produto toProduto(ProdutoDTO dto){
        return Produto.builder()
                .id(dto.getId())
                .nome(dto.getNome())
                .estoque(dto.getEstoque())
                .preco(dto.getPreco())
                .build();
    }

    public ProdutoDTO toProduto(Produto produto){
        return ProdutoDTO.builder()
                .id(produto.getId())
                .nome(produto.getNome())
                .estoque(produto.getEstoque())
                .preco(produto.getPreco())
                .build();
    }
}
