package marvin.igris.live.api.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import java.util.TimeZone;

import org.springframework.stereotype.Service;

import reactor.core.publisher.Mono;

@Service
public class DateTimeFormatterService {
    
    public Mono<Optional<String>> convertDateToString(Date d) {
        return Mono
            .justOrEmpty(d)
            .map(
                date -> {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
                    sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
                    return Optional.ofNullable(sdf.format(date));
                }
            )
            .defaultIfEmpty(Optional.empty())
            .onErrorResume(
                e -> {
                    System.out.println("# [ Error Converting Date to String ] ");
                    return Mono.just(Optional.empty());
                }
            );
    }

    public Mono<Optional<Date>> convertStringToDate(String s) {
        return Mono
            .justOrEmpty(s)
            .flatMap(
                sDate -> {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
                    sdf.setTimeZone(TimeZone.getTimeZone("UTC"));

                    try {
                        
                        return Mono.just(Optional.ofNullable(sdf.parse(sDate)));
                        
                    } catch (Exception e) {
                        System.err.println("Error parsing date: " + e.getMessage());
                        return Mono.error(e);
                    }
                }
            )
            .defaultIfEmpty(Optional.empty())
            .onErrorResume(
                e -> {
                    System.out.println("# [ Error Converting String to Date ] ");
                    return Mono.just(Optional.empty());
                }
            );
    }
}
