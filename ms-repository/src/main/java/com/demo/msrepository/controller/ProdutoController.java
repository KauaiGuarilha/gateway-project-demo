package com.demo.msrepository.controller;

import com.demo.msrepository.model.dto.ProdutoDTO;
import com.demo.msrepository.model.parser.ProdutoParser;
import com.demo.msrepository.model.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/produto")
public class ProdutoController {

    @Autowired private ProdutoService produtoService;

    @PostMapping
    public ResponseEntity<?> criarProduto(@RequestBody ProdutoDTO dto){
        return new ResponseEntity<>(produtoService.criaAtualizaProduto(dto), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<?> atualizarProduto(@RequestBody ProdutoDTO dto){
        return new ResponseEntity<>(produtoService.criaAtualizaProduto(dto), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> retornarProdutos(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                              @RequestParam(value = "limit", defaultValue = "12") Integer limit,
                                              @RequestParam(value = "direction", defaultValue = "asc") String direction){
        return new ResponseEntity<>(produtoService.listAll(page, limit, direction), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> retornarProdutos(@PathVariable String id){
        return new ResponseEntity<>(produtoService.findById(id), HttpStatus.OK);
    }
}
