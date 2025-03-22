package marvin.igris.live.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import marvin.igris.live.api.dto.request.PostMessageRequest;
import marvin.igris.live.api.dto.response.PostMessageResponse;
import marvin.igris.live.api.service.MessageService;
import marvin.igris.live.database.entity.Message;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@CrossOrigin(origins = {"http://localhost:5173"})
@RequestMapping(value = "/api/message")
public class MessageController {
    
    @Autowired
    private MessageService msServ;

    // post a message
    @PostMapping(
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Mono<PostMessageResponse> postMessage(
        @RequestBody PostMessageRequest req
    ) {
        return msServ.postNewMessage(req);
    }

    // actively - read all messages
    @GetMapping(
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Flux<Message> getMessages() {
        return msServ.getAllMessages();
    }

    // delete message
    @GetMapping(
        produces = MediaType.APPLICATION_JSON_VALUE,
        value = "/delete/:id"
    )
    public void removeMessage(
        @PathVariable("id") String id
    ) {
        msServ.deleteMessage(id);
    }
}
