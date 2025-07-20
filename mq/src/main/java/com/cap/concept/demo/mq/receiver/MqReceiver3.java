package com.cap.concept.demo.mq.receiver;

import com.cap.concept.demo.mq.model.Person3;
import com.cap.concept.demo.mq.repository.Person3Repository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class MqReceiver3 {

    private final ObjectMapper objectMapper;
    Person3Repository personRepository;

    public MqReceiver3(ObjectMapper objectMapper, Person3Repository personRepository) {
        this.objectMapper = objectMapper;
        this.personRepository = personRepository;
    }

    @JmsListener(destination = "${spring.mq.queue3}")
    public void receive(String message) throws JsonProcessingException {

        Person3 person = objectMapper.readValue(message, Person3.class);
        personRepository.save(person);

        System.out.println("Message received: " + message);
    }

}
