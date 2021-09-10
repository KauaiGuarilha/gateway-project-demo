package com.demo.msrepository.model.service;

import com.demo.msrepository.model.dto.ProdutoDTO;
import com.demo.msrepository.model.entity.Produto;
import com.demo.msrepository.model.exceptions.ResourceNotFoundException;
import com.demo.msrepository.model.repository.ProdutoRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
public class ProdutoService {

    @Autowired private ProdutoRepository produtoRepository;

    public Produto criaAtualizaProduto(Produto produto){
        try {
            if (Objects.isNull(produto.getId())) return produtoRepository.save(produto);

            Optional<Produto> optional = produtoRepository.findById(produto.getId());
            Produto pd = new Produto();

            if (optional.isPresent()) pd = optional.get();
            pd.setNome(produto.getNome());
            pd.setPreco(produto.getPreco());
            pd.setEstoque(produto.getEstoque());

            return produtoRepository.save(pd);
        } catch (Exception e){
            log.error("Ocorreu algum erro genérico na tentativa de salvar ou atualizar o produto na base.", ExceptionUtils.getStackTrace(e));
            throw new ResourceNotFoundException("");
        }
    }

    public Page<Produto> listAll(Integer page, Integer limit, String direction) {
        try {
            var sortDirection = "desc".equalsIgnoreCase(direction) ? Sort.Direction.DESC : Sort.Direction.ASC;
            Pageable pageable = PageRequest.of(page, limit, Sort.by(sortDirection, "nome"));

            return produtoRepository.findAll(pageable);
        } catch (Exception e){
            log.error("Ocorreu algum problema genérco na tentativa de listar os produtos.", ExceptionUtils.getStackTrace(e));
            throw new ResourceNotFoundException("");
        }
    }

    public Produto findById(String id){
        try {
            verificarIdProduto(id);
            Produto produto = produtoRepository.findByProdutoId(UUID.fromString(id));

            if (Objects.isNull(produto))
                throw new ResourceNotFoundException("");

            return produto;
        } catch (ResourceNotFoundException e){
            throw e;
        } catch (Exception e){
            log.error("Ocorreu algum problema genérco na tentativa de buscar o produto por ID.", ExceptionUtils.getStackTrace(e));
            throw new ResourceNotFoundException("");
        }
    }

    private void verificarIdProduto(String id) {
        if (Objects.isNull(UUID.fromString(id)))
            throw new ResourceNotFoundException("");
    }
}
