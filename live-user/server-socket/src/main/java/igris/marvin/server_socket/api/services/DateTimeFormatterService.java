package igris.marvin.server_socket.api.services;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Optional;

import org.springframework.stereotype.Service;

import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Service
public class DateTimeFormatterService {
    
    // convert String to date UTC "dd-MM-yyyy'T'hh:mm:ss'Z'"
    public Mono<Optional<Date>> convertStringToDate(
        String s
    ) {
        return Mono.just(Optional.ofNullable(s))
            .map(
                optStr -> {
                    return optStr.isPresent()
                        ? Optional.ofNullable(
                            Date
                                .from(
                                DateTimeFormatter.ISO_INSTANT
                                    .parse(
                                        optStr.get(), 
                                        Instant::from
                                    )
                                )
                        )
                        : Optional.empty();
                }
            );
            
    }

    // convertDateToString UTC "dd-MM-yyyy'T'hh:mm:ss'Z'"
    public Mono<Optional<String>> convertDateToString(
        Date d
    ) {
        return Mono
            .just(Optional.ofNullable(d))
            .map(
                optDate -> {
                    
                    return optDate.isPresent()
                        ? Optional
                            .ofNullable(
                                optDate
                                .get()
                                .toInstant()
                                .atZone(
                                    ZoneId.of("UTC")
                                )
                                .format(
                                    DateTimeFormatter.ISO_INSTANT
                                )
                            )
                        : Optional.empty();
                }
                        
            );
    }

    // convertStringToTime
    public Mono<Optional<String>> convertTimeToString(
        LocalTime t
    ) {
        return Mono
            .just(Optional.ofNullable(t))
            .map(
                optTime -> {
                    return optTime.isPresent()
                        ? Optional
                            .ofNullable(
                                optTime
                                    .get()
                                    .format(
                                        DateTimeFormatter
                                            .ofPattern("HH:mm:ss")
                                    )
                            )
                        : Optional.empty();
                }
            );
    }

    // convertTimeToString
    public Mono<Optional<LocalTime>> convertStringToTime(
        String s
    ) {
        return Mono
            .just(Optional.ofNullable(s))
            .map(
                optStr -> {
                    return optStr.isPresent()
                        ? Optional
                            .ofNullable(
                                LocalTime
                                    .parse(
                                        optStr.get(), 
                                        DateTimeFormatter
                                            .ofPattern(
                                                "HH:mm:ss"
                                            )
                                    )
                            )
                        : Optional.empty();
                }
            );
    }

    public Mono<Optional<String>> convertExpDateToString(
        LocalDate expDate
    ) {
        return Mono
            .just(expDate)
            .map(
                exp -> {
                    return Optional
                        .ofNullable(
                            exp
                                .format(
                                    DateTimeFormatter.ofPattern("MM/yy")
                                )
                        );
                }
            )
            .subscribeOn(Schedulers.parallel());
    }

    public Mono<Optional<LocalDate>> convertStringToExpDate(
        String expDate
    ) {

        return Mono
            .just(
                expDate
            )
            .map(
                exp -> {
                    return Optional
                        .ofNullable(
                            LocalDate
                                .parse(
                                    exp, 
                                    DateTimeFormatter.ofPattern("MM/yy")
                                )
                        );
                }
            );
    }
}
