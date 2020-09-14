package ru.krayale.controller;

import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import ru.krayale.dto.SystemInfoBean;
import ru.krayale.listener.ActiveMQConsumer;


import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Startup;
import javax.ejb.Stateful;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;

import java.io.Serializable;

@Named
@Stateful
@Getter
@Setter
@Startup
@ApplicationScoped
public class MyEJBApplication  implements Serializable {

    @EJB
    private ActiveMQConsumer consumer;

    private SystemInfoBean systemInfoBean;

    private static final String REST_URI
            = "http://localhost:8081/info";

    private Client client = ClientBuilder.newClient();

    @SneakyThrows
    @PostConstruct
    private void init(){
        this.systemInfoBean = client
                        .target(REST_URI)
                        .request(MediaType.APPLICATION_JSON)
                        .get(SystemInfoBean.class);
        consumer.consume();
    }

}
