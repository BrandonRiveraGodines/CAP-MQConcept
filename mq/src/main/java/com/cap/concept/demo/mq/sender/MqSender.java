package com.cap.concept.demo.mq.sender;

import com.cap.concept.demo.mq.model.Person;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
public class MqSender {

    @Value("${spring.mq.queue}")
    private String queueName;

    private final JmsTemplate jmsTemplate;

    private final ObjectMapper objectMapper;

    public MqSender(JmsTemplate jmsTemplate, ObjectMapper objectMapper) {
        this.jmsTemplate = jmsTemplate;
        this.objectMapper = objectMapper;
    }

    public void send(Object message) throws JsonProcessingException {
        String jsonMessage = objectMapper.writeValueAsString(message);
        jmsTemplate.convertAndSend(queueName, jsonMessage);
    }

}
