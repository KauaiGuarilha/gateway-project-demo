package com.demo.msrepository.model.repository;

import com.demo.msrepository.model.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, UUID> {

    @Query("select p from Produto p where p.id = ?1")
    Produto findByProdutoId(UUID id);
}
