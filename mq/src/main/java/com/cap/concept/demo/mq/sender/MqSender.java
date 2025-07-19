package com.cap.concept.demo.mq.sender;

import com.cap.concept.demo.mq.model.Person;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
public class MqSender {

    @Value("${spring.mq.queue}")
    private String queueName;

    private final JmsTemplate jmsTemplate;

    public MqSender(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    public void send(String message) {
        jmsTemplate.convertAndSend(queueName, message);
    }

}
