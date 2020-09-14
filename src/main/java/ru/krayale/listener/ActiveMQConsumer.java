package ru.krayale.listener;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;

import javax.ejb.Stateful;
import javax.inject.Inject;
import javax.jms.*;

@Stateful
public class ActiveMQConsumer {

    private MessageConsumer consumer;
    @Inject
    private ActiveMQListener listener;



    public void consume() throws JMSException {
        String url = "tcp://localhost:61616";

        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
        Destination destination = new ActiveMQQueue("Logistika");

        Connection connection = connectionFactory.createConnection();

        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        consumer = session.createConsumer(destination);
        consumer.setMessageListener(listener);
        connection.start();
    }

}
