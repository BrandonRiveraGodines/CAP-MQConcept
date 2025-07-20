package com.cap.concept.demo.mq.receiver;

import com.cap.concept.demo.mq.model.Person;
import com.cap.concept.demo.mq.repository.PersonRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class MqReceiver {

    private final ObjectMapper objectMapper;
    PersonRepository personRepository;

    public MqReceiver(ObjectMapper objectMapper, PersonRepository personRepository) {
        this.objectMapper = objectMapper;
        this.personRepository = personRepository;
    }

    @JmsListener(destination = "${spring.mq.queue}")
    public void receive(String message) throws JsonProcessingException {

        Person person = objectMapper.readValue(message, Person.class);
        personRepository.save(person);

        System.out.println("Message received: " + message);
    }

}
