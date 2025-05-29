package com.example.demo.controller;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

import com.example.demo.model.Greeting;
import com.example.demo.model.HelloMessage;

@Controller
public class GreetingController {

    private final List<Greeting> greetings = new ArrayList();

    // The @MessageMapping annotation ensures that, 
    // if a message is sent to the /hello destination, 
    // the greeting() method is called.
    @MessageMapping("/hello") // -> /app/hello -> full endpoint
    @SendTo("/topic/greetings") // -> send to subcribers of
    public Greeting greeting(
        // The payload of the message is bound to a HelloMessage object, 
        // which is passed into greeting().
        @Payload HelloMessage message
    ) throws Exception {

        // After the one-second delay, the greeting() method creates a 
        // Greeting object and returns it. The return value is broadcast 
        // to all subscribers of /topic/greetings
        // as specified in the @SendTo annotation.
        // Thread.sleep(1000); // simulated delay

        // System.out.println(Date.from(Instant.now()));
        // System.out.println(
        //     "\n\n" + 
        //     greetings.toString()
        //     + "\n"
        // );

        // the name from the input message is sanitized, 
        // since, in this case, it will be echoed back and 
        // re-rendered in the browser DOM on the client side.
        
        Greeting greet = new Greeting(
            "Hello, " + HtmlUtils.htmlEscape(message.getName()) + "!",
            Date.from(Instant.now())
        );

        greetings.add(greet);

        return greet;
    }

    // retrieve already existing messages
    @MessageMapping("/history") // "/app/history" publish here
    @SendTo("/topic/greeting/history") // broadcast to those who subscribed here
    public List<Greeting> getHistory(
    ) throws Exception {
        return greetings;
    }
}
