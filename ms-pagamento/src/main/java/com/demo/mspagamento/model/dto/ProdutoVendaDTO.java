package com.demo.mspagamento.model.dto;

import com.demo.mspagamento.model.entity.Venda;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProdutoVendaDTO {

    private String id;
    private String idProduto;
    private Integer quantidade;
    private Venda venda;
}
