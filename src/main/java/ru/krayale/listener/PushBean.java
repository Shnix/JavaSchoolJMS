package ru.krayale.listener;


import javax.enterprise.context.ApplicationScoped;
import javax.faces.push.Push;
import javax.faces.push.PushContext;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@ApplicationScoped
public class PushBean {

    @Inject
    @Push(channel = "websocket")
    private PushContext push;


    public void pushUpdate() {
        push.send("update");
    }

}
