package com.demo.mspagamento.model.dto;

import com.demo.mspagamento.model.entity.ProdutoVenda;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VendaDTO {

    private String id;
    private Date data;
    private List<ProdutoVenda> produtos;
    private Double valorTotal;
}
