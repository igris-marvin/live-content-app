package com.example.demo.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

//WebSocketConfig is annotated with @Configuration to 
// indicate that it is a Spring configuration class. 
@Configuration
// @EnableWebSocketMessageBroker enables WebSocket message handling, 
// backed by a message broker.
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    // CONFIGURE MESSAGE BROKER

    // The configureMessageBroker() method implements the default method in 
    // WebSocketMessageBrokerConfigurer to configure the message broker. 
    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {

        // It starts by calling enableSimpleBroker() to enable a 
        // simple memory-based message broker to carry the greeting messages back 
        // to the client on (destinations) prefixed with /topic. 
        config.enableSimpleBroker("/topic");

        // It also designates the /app prefix for messages that are bound 
        // for methods annotated with @MessageMapping.
        config.setApplicationDestinationPrefixes("/app");

        // This prefix will be used to define all the message mappings. 
        // For example, /app/hello is the endpoint that 
        // the GreetingController.greeting() method is mapped to handle.
    }

    // REGISTER STOMP ENDPOINTS


    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {

        final String client = "http://localhost:5173";

        // The registerStompEndpoints() method registers the /ws 
        // endpoint for websocket connections.
        registry.addEndpoint("/ws")
            .setAllowedOriginPatterns(client)
            .setAllowedOrigins(client);
    }

}
