package com.cap.concept.demo.mq.sender;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
public class MqSender {

    private final JmsTemplate jmsTemplate;

    private final ObjectMapper objectMapper;

    public MqSender(JmsTemplate jmsTemplate, ObjectMapper objectMapper) {
        this.jmsTemplate = jmsTemplate;
        this.objectMapper = objectMapper;
    }

    public void send(Object message, String queueName) throws JsonProcessingException {
        String jsonMessage = objectMapper.writeValueAsString(message);
        jmsTemplate.convertAndSend(queueName, jsonMessage);
    }

}
