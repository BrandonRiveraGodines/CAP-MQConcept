package com.cap.concept.demo.mq.controller;

import com.cap.concept.demo.mq.model.Person;
import com.cap.concept.demo.mq.sender.MqSender;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/messages")
public class MessageController {

    private final MqSender mqSender;

    public MessageController(MqSender mqSender) {
        this.mqSender = mqSender;
    }

    @PostMapping
    public ResponseEntity<String> sendMessage(@RequestBody Person message) throws JsonProcessingException {
        System.out.println("Mensaje recibido: " + message);
        mqSender.send(message);
        return ResponseEntity.ok("Mensaje enviado a MQ correctamente");
    }

}
