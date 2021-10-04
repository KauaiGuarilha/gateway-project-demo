package com.demo.mspagamento.model.parser;

import com.demo.mspagamento.model.dto.ProdutoDTO;
import com.demo.mspagamento.model.entity.Produto;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class ProdutoParser {

    public Produto toProduto(ProdutoDTO dto){
        return Produto.builder()
                .id(dto.getId())
                .nome(dto.getNome())
                .estoque(Integer.valueOf(dto.getEstoque()))
                .preco(dto.getPreco())
                .build();
    }

    public ProdutoDTO toDTO(Produto produto){
        return ProdutoDTO.builder()
                .id(produto.getId())
                .nome(produto.getNome())
                .estoque(String.valueOf(produto.getEstoque()))
                .preco(produto.getPreco())
                .build();
    }
}
