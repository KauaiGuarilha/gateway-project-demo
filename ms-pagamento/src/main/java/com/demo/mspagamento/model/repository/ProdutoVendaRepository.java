package com.demo.mspagamento.model.repository;

import com.demo.mspagamento.model.entity.ProdutoVenda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProdutoVendaRepository extends JpaRepository<ProdutoVenda, UUID> {
}
