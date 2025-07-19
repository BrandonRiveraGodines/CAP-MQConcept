package com.cap.concept.demo.mq.sender;


import com.ibm.mq.jakarta.jms.MQConnectionFactory;
import com.ibm.msg.client.jakarta.wmq.WMQConstants;
import com.ibm.msg.client.jakarta.wmq.compat.jms.internal.JMSC;
import jakarta.jms.ConnectionFactory;
import jakarta.jms.JMSException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.core.JmsTemplate;

@Configuration
public class MqConfig {

    @Value("${ibm.mq.queue-manager}")
    private String queueManager;

    @Value("${ibm.mq.channel}")
    private String channel;

    @Value("${ibm.mq.conn-name}")
    private String connName;

    @Value("${ibm.mq.user}")
    private String user;

    @Value("${ibm.mq.password}")
    private String password;

    @Bean
    public ConnectionFactory connectionFactory() {
        MQConnectionFactory factory = new MQConnectionFactory();
        try {
            factory.setTransportType(JMSC.MQJMS_TP_CLIENT_MQ_TCPIP);
            factory.setQueueManager(queueManager);
            factory.setChannel(channel);
            factory.setConnectionNameList(connName);
            factory.setStringProperty(WMQConstants.USERID, user);
            factory.setStringProperty(WMQConstants.PASSWORD, password);
        } catch (JMSException e) {
            throw new RuntimeException("Error al configurar la conexión MQ", e);
        }
        return factory;
    }

    @Bean
    public JmsTemplate jmsTemplate(ConnectionFactory connectionFactory) {
        return new JmsTemplate(connectionFactory);
    }

}
