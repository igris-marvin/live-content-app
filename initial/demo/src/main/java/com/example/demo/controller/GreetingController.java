package com.example.demo.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

import com.example.demo.model.Greeting;
import com.example.demo.model.HelloMessage;

@Controller
public class GreetingController {


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
        Thread.sleep(1000); // simulated delay

        // the name from the input message is sanitized, 
        // since, in this case, it will be echoed back and 
        // re-rendered in the browser DOM on the client side.
        return new Greeting(
            "Hello, " + HtmlUtils.htmlEscape(message.getName()) + "!"
        );
    }
}
