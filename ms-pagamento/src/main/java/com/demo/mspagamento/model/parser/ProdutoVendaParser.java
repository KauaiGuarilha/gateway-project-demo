package com.demo.mspagamento.model.parser;

import com.demo.mspagamento.model.dto.ProdutoVendaDTO;
import com.demo.mspagamento.model.entity.ProdutoVenda;
import com.demo.mspagamento.model.entity.Venda;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class ProdutoVendaParser {

    public ProdutoVenda toProdutoVenda(ProdutoVendaDTO dto){
        return ProdutoVenda.builder()
                .id(UUID.fromString(dto.getId()))
                .idProduto(UUID.fromString(dto.getIdProduto()))
                .quantidade(dto.getQuantidade())
                .venda(Venda.builder()
                        .id(dto.getVenda().getId())
                        .data(dto.getVenda().getData())
                        .produtos(dto.getVenda().getProdutos().stream().collect(Collectors.toList()))
                        .valorTotal(dto.getVenda().getValorTotal())
                        .build())
                .build();
    }

    public ProdutoVendaDTO toDTO(ProdutoVenda produtoVenda){
        return ProdutoVendaDTO.builder()
                .id(produtoVenda.getId().toString())
                .idProduto(produtoVenda.getIdProduto().toString())
                .quantidade(produtoVenda.getQuantidade())
                .venda(Venda.builder()
                        .id(produtoVenda.getVenda().getId())
                        .data(produtoVenda.getVenda().getData())
                        .produtos(produtoVenda.getVenda().getProdutos().stream().collect(Collectors.toList()))
                        .valorTotal(produtoVenda.getVenda().getValorTotal())
                        .build())
                .build();
    }
}
