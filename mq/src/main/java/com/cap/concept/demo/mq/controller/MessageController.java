package com.cap.concept.demo.mq.controller;

import com.cap.concept.demo.mq.model.Person;
import com.cap.concept.demo.mq.sender.MqSender;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RestController
@RequestMapping("/api/messages")
public class MessageController {

    private final MqSender mqSender;
    private final ObjectMapper objectMapper;

    public MessageController(MqSender mqSender, ObjectMapper objectMapper) {
        this.mqSender = mqSender;
        this.objectMapper = objectMapper;
    }

    @PostMapping
    public ResponseEntity<String> sendMessage(@RequestBody Person message) throws JsonProcessingException {
        System.out.println("Mensaje recibido: " + message);
        mqSender.send(message);
        return ResponseEntity.ok("Mensaje enviado a MQ correctamente");
    }

    @GetMapping
    public ResponseEntity<String> sendBatch() throws JsonProcessingException {
        ExecutorService executor = Executors.newFixedThreadPool(1);

        for (int i = 1; i <= 100; i++) {
            Long id = (long) i;
            executor.submit(() -> {
                try {
                    Person person = new Person();
                    person.setName("Brandon");
                    person.setAge(29);
                    person.setAddress("Street");
                    person.setPhone("phone");
                    person.setEmail("email");
                    mqSender.send(person);
                    Thread.sleep(1000);
                } catch (Exception e) {
                    System.err.println("Error enviando mensaje " + id + ": " + e.getMessage());
                }
            });
        }

        executor.shutdown();
        return ResponseEntity.ok("Se enviaron 100 mensajes a la MQ.");
    }

}
