package com.cap.concept.demo.mq.receiver;

import com.cap.concept.demo.mq.model.Person4;
import com.cap.concept.demo.mq.repository.Person4Repository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class MqReceiver4 {

    private final ObjectMapper objectMapper;
    Person4Repository personRepository;

    public MqReceiver4(ObjectMapper objectMapper, Person4Repository personRepository) {
        this.objectMapper = objectMapper;
        this.personRepository = personRepository;
    }

    @JmsListener(destination = "${spring.mq.queue4}")
    public void receive(String message) throws JsonProcessingException {

        Person4 person = objectMapper.readValue(message, Person4.class);
        personRepository.save(person);

        System.out.println("Message received: " + message);
    }

}
