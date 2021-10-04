package com.demo.mspagamento.model.message;

import com.demo.mspagamento.model.dto.ProdutoDTO;
import com.demo.mspagamento.model.parser.ProdutoParser;
import com.demo.mspagamento.model.repository.ProdutoRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class ProdutoReceiveMessage {

    @Autowired private ProdutoParser parser;
    @Autowired private ProdutoRepository produtoRepository;

    @RabbitListener(queues = {"${crud.rabbitmq.queue}"})
    public void receive(@Payload ProdutoDTO dto){
        produtoRepository.save(parser.toProduto(dto));
    }
}
