package com.demo.mspagamento.model.parser;

import com.demo.mspagamento.model.dto.VendaDTO;
import com.demo.mspagamento.model.entity.Venda;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class VendaParser {

    public Venda toVenda(VendaDTO dto){
        return Venda.builder()
                .id(UUID.fromString(dto.getId()))
                .data(dto.getData())
                .produtos(dto.getProdutos().stream().collect(Collectors.toList()))
                .valorTotal(dto.getValorTotal())
                .build();
    }

    public VendaDTO toDTO(Venda venda){
        return VendaDTO.builder()
                .id(venda.getId().toString())
                .data(venda.getData())
                .produtos(venda.getProdutos().stream().collect(Collectors.toList()))
                .valorTotal(venda.getValorTotal())
                .build();
    }
}
