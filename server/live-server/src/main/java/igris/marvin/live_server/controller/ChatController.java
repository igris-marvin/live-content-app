package igris.marvin.live_server.controller;

import java.util.Date;

import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import igris.marvin.live_server.model.ChatMessage;

@Controller
public class ChatController {
    
    @MessageMapping("/chat")
    @SendTo("/topic/messages")
    public ResponseEntity<ChatMessage> sendMessage(
        @Payload ChatMessage cm
    ) {
        cm.setTimestamp(new Date());
        return ResponseEntity.ok(cm);
    }
}
