package com.cap.concept.demo.mq.receiver;

import com.cap.concept.demo.mq.model.Person2;
import com.cap.concept.demo.mq.repository.Person2Repository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class MqReceiver2 {

    private final ObjectMapper objectMapper;
    Person2Repository personRepository;

    public MqReceiver2(ObjectMapper objectMapper, Person2Repository personRepository) {
        this.objectMapper = objectMapper;
        this.personRepository = personRepository;
    }

    @JmsListener(destination = "${spring.mq.queue2}")
    public void receive(String message) throws JsonProcessingException {

        Person2 person = objectMapper.readValue(message, Person2.class);
        personRepository.save(person);

        System.out.println("Message received: " + message);
    }

}
