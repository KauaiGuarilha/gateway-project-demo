package com.demo.mspagamento.model.service;

import com.demo.mspagamento.model.dto.VendaDTO;
import com.demo.mspagamento.model.entity.ProdutoVenda;
import com.demo.mspagamento.model.entity.Venda;
import com.demo.mspagamento.model.exceptions.ResourceNotFoundException;
import com.demo.mspagamento.model.repository.ProdutoRepository;
import com.demo.mspagamento.model.repository.ProdutoVendaRepository;
import com.demo.mspagamento.model.repository.VendaRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Service
public class VendaService {

    @Autowired private VendaRepository vendaRepository;
    @Autowired private ProdutoVendaRepository produtoVendaRepository;

    public Venda criarVenda(Venda venda){
        try {
            Venda venda1 = vendaRepository.save(venda);

            List<ProdutoVenda> produtoSalvos = new ArrayList<>();
            venda.getProdutos().forEach(p -> {
                ProdutoVenda pv = p;
                pv.setVenda(venda1);
                produtoSalvos.add(produtoVendaRepository.save(pv));
            });

            venda.setProdutos(produtoSalvos);
            return venda;

            /*if (Objects.isNull(venda)) return vendaRepository.save(venda);

            Optional<Venda> optional = vendaRepository.findById(venda.getId());
            Venda vendaDB = new Venda();

            if (optional.isPresent()) vendaDB = optional.get();
            vendaDB.setData(venda.getData());
            vendaDB.setProdutos(venda.getProdutos());
            vendaDB.setValorTotal(venda.getValorTotal());

            return vendaRepository.save(vendaDB);*/
        } catch (Exception e){
            log.error("Ocorreu algum erro genérico na tentativa de salvar ou atualizar a venda na base.", ExceptionUtils.getStackTrace(e));
            throw new ResourceNotFoundException("");
        }
    }

    public Venda findById(String id){
        try {
            verificarIdVenda(id);
            Venda venda = vendaRepository.findByVendaId(UUID.fromString(id));

            if (Objects.isNull(venda))
                throw new ResourceNotFoundException("");

            return venda;
        } catch (ResourceNotFoundException e){
            throw e;
        } catch (Exception e){
            log.error("Ocorreu algum problema genérco na tentativa de buscar o produto por ID.", ExceptionUtils.getStackTrace(e));
            throw new ResourceNotFoundException("");
        }
    }

    public Page<Venda> listAll(Integer page, Integer limit, String direction) {
        try {
            var sortDirection = "desc".equalsIgnoreCase(direction) ? Sort.Direction.DESC : Sort.Direction.ASC;
            Pageable pageable = PageRequest.of(page, limit, Sort.by(sortDirection, "nome"));

            return vendaRepository.findAll(pageable);
        } catch (Exception e){
            log.error("Ocorreu algum problema genérco na tentativa de listar os produtos.", ExceptionUtils.getStackTrace(e));
            throw new ResourceNotFoundException("");
        }
    }

    private void verificarIdVenda(String id){
        if (Objects.isNull(UUID.fromString(id)))
            throw new ResourceNotFoundException("");
    }
}
