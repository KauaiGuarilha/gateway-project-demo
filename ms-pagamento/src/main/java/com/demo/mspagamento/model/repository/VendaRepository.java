package com.demo.mspagamento.model.repository;

import com.demo.mspagamento.model.entity.Venda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface VendaRepository extends JpaRepository<Venda, UUID> {

    @Query("select v from Venda v where v.id = ?1")
    Venda findByVendaId(UUID id);
}
