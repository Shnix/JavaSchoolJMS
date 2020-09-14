package ru.krayale.listener;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import ru.krayale.controller.MyEJBApplication;
import ru.krayale.dto.SystemInfoBean;

import javax.annotation.PostConstruct;
import javax.ejb.LocalBean;

import javax.ejb.Stateful;
import javax.inject.Inject;
import javax.jms.*;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import java.io.Serializable;

@Stateful
@LocalBean
public class ActiveMQListener implements MessageListener, Serializable {

    private SystemInfoBean systemInfoBean;

    @Inject
    private MyEJBApplication application;

    @Inject
    private PushBean pushBean;


    @SneakyThrows
    @Override
    public void onMessage(Message message) {
        try {
            TextMessage textMessage = (TextMessage) message;
            systemInfoBean = new ObjectMapper().readValue(textMessage.getText(),SystemInfoBean.class);
            application.setSystemInfoBean(systemInfoBean);
            pushBean.pushUpdate();

        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
