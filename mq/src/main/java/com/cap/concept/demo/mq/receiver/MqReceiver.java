package com.cap.concept.demo.mq.receiver;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class MqReceiver {

    @JmsListener(destination = "${spring.mq.queue}")
    public void receive(String message) {
        System.out.println("Message received: " + message);
    }

}
