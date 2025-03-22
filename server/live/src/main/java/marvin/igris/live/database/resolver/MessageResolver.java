package marvin.igris.live.database.resolver;

import java.time.Month;
import java.time.Year;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import marvin.igris.live.api.service.DateTimeFormatterService;
import marvin.igris.live.database.dao.ResolverDAO;
import marvin.igris.live.database.entity.Message;
import marvin.igris.live.firebase.mapping.FireMessage;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

public class MessageResolver implements ResolverDAO<Message, FireMessage> {

    @Autowired
    private DateTimeFormatterService dtfs;

    @Override
    public Mono<Message> resolveToDefinedEntity(
        FireMessage f
    ) {
        return Mono
            .just(f)
            .flatMap(
                fire -> {
                    Mono<Optional<Date>> ocaMono = dtfs.convertStringToDate(fire.getCreatedAt());
                    Mono<Optional<Date>> ouaMono = dtfs.convertStringToDate(fire.getUpdatedAt());

                    return Mono
                        .zip(ocaMono, ouaMono)
                        .map(
                            t -> {
                                return new Message(
                                    fire.getMessageId(), 
                                    fire.getText(), 
                                    fire.getIpAddress(), 
                                    Year.of(fire.getYear()), 
                                    Month.valueOf(fire.getMonth()), 
                                    t.getT1().orElse(null), 
                                    t.getT2().orElse(null)
                                );
                            }
                        )
                        .subscribeOn(Schedulers.parallel());
                }
            );
    }

    @Override
    public Mono<FireMessage> resolveToMappingEntity(
        Message t
    ) {
        return Mono
            .just(t)
            .flatMap(
                obj -> {
                    Mono<Optional<String>> ocaMono = dtfs.convertDateToString(obj.getCreatedAt());
                    Mono<Optional<String>> ouaMono = dtfs.convertDateToString(obj.getUpdatedAt());

                    return Mono
                        .zip(ocaMono, ouaMono)
                        .map(
                            z -> {
                                return new FireMessage(
                                    obj.getMessageId(), 
                                    obj.getText(), 
                                    obj.getIpAddress(), 
                                    obj.getYear().getValue(),
                                    obj.getMonth().name(),
                                    z.getT1().orElse(null), 
                                    z.getT2().orElse(null)
                                );
                            }
                        )
                        .subscribeOn(Schedulers.parallel());
                }
            );
    }
    
}
