package com.demo.msrepository.model.service;

import com.demo.msrepository.model.dto.ProdutoDTO;
import com.demo.msrepository.model.entity.Produto;
import com.demo.msrepository.model.exceptions.ResourceNotFoundException;
import com.demo.msrepository.model.message.ProdutoSendMessage;
import com.demo.msrepository.model.parser.ProdutoParser;
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

    @Autowired private ProdutoParser parser;
    @Autowired private ProdutoRepository produtoRepository;
    @Autowired private ProdutoSendMessage produtoSendMessage;

    public ProdutoDTO criaAtualizaProduto(ProdutoDTO produtoDTO){
        try {
            if (Objects.isNull(produtoDTO.getId())) {
                Produto produtoSave = produtoRepository.save(parser.toProduto(produtoDTO));
                produtoSendMessage.sendMessage(parser.toProduto(produtoSave));
                return parser.toProduto(produtoSave);
            }

            Optional<Produto> optional = produtoRepository.findById(parser.toProduto(produtoDTO).getId());
            Produto pd = new Produto();

            if (optional.isPresent()) pd = optional.get();
            pd.setNome(produtoDTO.getNome());
            pd.setPreco(produtoDTO.getPreco());
            pd.setEstoque(produtoDTO.getEstoque());

            Produto produtoUpdate = produtoRepository.save(pd);
            produtoSendMessage.sendMessage(parser.toProduto(produtoUpdate));
            return parser.toProduto(produtoUpdate);
        } catch (Exception e){
            log.error("Ocorreu algum erro genérico na tentativa de salvar ou atualizar o produtoDTO na base.", ExceptionUtils.getStackTrace(e));
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

    public ProdutoDTO findById(String id){
        try {
            verificarIdProduto(id);
            Produto produto = produtoRepository.findByProdutoId(UUID.fromString(id));

            if (Objects.isNull(produto))
                throw new ResourceNotFoundException("");

            return parser.toProduto(produto);
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
