package com.demo.mspagamento.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "produto_venda")
public class ProdutoVenda {

    @Id
    @GenericGenerator(name = "uuid-gen", strategy = "uuid2")
    @GeneratedValue(generator = "uuid-gen")
    @Type(type = "org.hibernate.type.PostgresUUIDType")
    @Column(columnDefinition = "uuid")
    private UUID id;

    @Column(name = "id_produto", nullable = false)
    private UUID idProduto;

    @Column(nullable = false)
    private Integer quantidade;

    @JoinColumn(name = "id_venda")
    @ManyToOne(fetch = FetchType.LAZY)
    private Venda venda;
}
