package marvin.igris.live.api.service;

import java.time.Instant;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import marvin.igris.live.api.dto.request.PostMessageRequest;
import marvin.igris.live.api.dto.response.PostMessageResponse;
import marvin.igris.live.database.entity.Message;
import marvin.igris.live.database.repository.MessageRepository;
import marvin.igris.live.firebase.mapping.FireMessage;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class MessageService {

    @Autowired
    private MessageRepository msRepo;

    @Autowired
    private DateTimeFormatterService dtfs;

    // SAVE NEW MESSAGE
    public Mono<PostMessageResponse> postNewMessage(
        PostMessageRequest req
    ) {
        return Mono
            .just(req)
            .flatMap(
                data -> {
                    Mono<Optional<String>> ocaMono = dtfs.convertDateToString(Date.from(Instant.now()));

                    return ocaMono
                        .flatMap(
                            oca -> {
                                return msRepo
                                    .save(
                                        new FireMessage(
                                            null, 
                                            data.getText(), 
                                            data.getIpAddress(), 
                                            data.getYear(), 
                                            data.getMonth().toUpperCase().trim(), 
                                            oca.orElse(null), 
                                            null
                                        )
                                    );
                            }
                        )
                        .map(
                            ms -> {
                                return new PostMessageResponse(
                                    true, 
                                    "Message saved successfully!"
                                );
                            }
                        );
                }
            )
            .onErrorResume(
                e -> {
                    return Mono
                        .just(
                            new PostMessageResponse(
                                false, 
                                "Error occured while saving a new message!"
                            )
                        );
                }
            );
    }

    public Flux<Message> getAllMessages() {
        return msRepo.findAll();
    }

    // DELETE MESSAGE
    public void deleteMessage(
        String id
    ) {
        msRepo.delete(id);
    }
}
