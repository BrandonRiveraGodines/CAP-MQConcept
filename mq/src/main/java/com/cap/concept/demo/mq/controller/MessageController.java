package com.cap.concept.demo.mq.controller;

import com.cap.concept.demo.mq.model.Person;
import com.cap.concept.demo.mq.model.Person2;
import com.cap.concept.demo.mq.model.Person3;
import com.cap.concept.demo.mq.model.Person4;
import com.cap.concept.demo.mq.sender.MqSender;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/messages")
public class MessageController {

    private final MqSender mqSender;
    private final ObjectMapper objectMapper;

    @Value("${spring.mq.queue}")
    private String queueName;

    @Value("${spring.mq.queue2}")
    private String queueName2;

    @Value("${spring.mq.queue3}")
    private String queueName3;

    @Value("${spring.mq.queue4}")
    private String queueName4;

    public MessageController(MqSender mqSender, ObjectMapper objectMapper) {
        this.mqSender = mqSender;
        this.objectMapper = objectMapper;
    }

    @PostMapping
    public ResponseEntity<String> sendMessage(@RequestBody Person message) throws JsonProcessingException {
        System.out.println("Mensaje recibido: " + message);
        mqSender.send(message, queueName);
        return ResponseEntity.ok("Mensaje enviado a MQ correctamente");
    }

    @GetMapping
    public ResponseEntity<String> sendBatch() throws JsonProcessingException {

        new Thread(() -> {
            for (int i = 1; i <= 1000; i++) {
                try {
                    Person person = new Person();
                    person.setName("Brandon");
                    person.setAge(29);
                    person.setAddress("Street");
                    person.setPhone("phone");
                    person.setEmail("email");
                    mqSender.send(person, queueName);
                    Thread.sleep(1000);
                } catch (Exception e) {
                    System.err.println("Error enviando mensaje " + e.getMessage());
                }
            }
        }).start();

        new Thread(() -> {
            for (int i = 1; i <= 1000; i++) {
                try {
                    Person2 person = new Person2();
                    person.setName("Brandon");
                    person.setAge(29);
                    person.setAddress("Street");
                    person.setPhone("phone");
                    person.setEmail("email");
                    mqSender.send(person, queueName2);
                    Thread.sleep(1500);
                } catch (Exception e) {
                    System.err.println("Error enviando mensaje " + e.getMessage());
                }
            }
        }).start();

        new Thread(() -> {
            for (int i = 1; i <= 1000; i++) {
                try {
                    Person3 person = new Person3();
                    person.setName("Brandon");
                    person.setAge(29);
                    person.setAddress("Street");
                    person.setPhone("phone");
                    person.setEmail("email");
                    mqSender.send(person, queueName3);
                    Thread.sleep(2000);
                } catch (Exception e) {
                    System.err.println("Error enviando mensaje " + e.getMessage());
                }
            }
        }).start();

        new Thread(() -> {
            for (int i = 1; i <= 1000; i++) {
                try {
                    Person4 person = new Person4();
                    person.setName("Brandon");
                    person.setAge(29);
                    person.setAddress("Street");
                    person.setPhone("phone");
                    person.setEmail("email");
                    mqSender.send(person, queueName4);
                    Thread.sleep(2500);
                } catch (Exception e) {
                    System.err.println("Error enviando mensaje " + e.getMessage());
                }
            }
        }).start();

        return ResponseEntity.ok("Se enviaron 4000 mensajes a la MQ.");
    }

}
