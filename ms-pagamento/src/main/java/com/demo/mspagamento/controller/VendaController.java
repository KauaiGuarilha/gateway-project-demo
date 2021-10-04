package com.demo.mspagamento.controller;

import com.demo.mspagamento.model.dto.VendaDTO;
import com.demo.mspagamento.model.parser.VendaParser;
import com.demo.mspagamento.model.service.VendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/venda")
public class VendaController {

    @Autowired private VendaService vendaService;
    @Autowired private VendaParser parser;

    @PostMapping
    public ResponseEntity<?> criarVenda(@RequestBody VendaDTO dto){
        return new ResponseEntity<>(parser.toDTO(vendaService.criarVenda(parser.toVenda(dto))), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<?> atualizarVenda(@RequestBody VendaDTO dto){
        return new ResponseEntity<>(parser.toDTO(vendaService.criarVenda(parser.toVenda(dto))), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> retornarVendas(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                              @RequestParam(value = "limit", defaultValue = "12") Integer limit,
                                              @RequestParam(value = "direction", defaultValue = "asc") String direction){
        return new ResponseEntity<>(vendaService.listAll(page, limit, direction), HttpStatus.OK);
    }
}
