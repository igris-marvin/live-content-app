package igris.marvin.server_socket.database.resolver;

import java.util.Date;
import java.util.Optional;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import igris.marvin.server_socket.api.services.DateTimeFormatterService;
import igris.marvin.server_socket.database.dao.ResolverDAO;
import igris.marvin.server_socket.database.entity.Student;
import igris.marvin.server_socket.database.firestore.FireStudent;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Service
public class StudentResolver implements ResolverDAO<Student, FireStudent> {

    @Autowired
    private DateTimeFormatterService dtfs;

    private final Logger logger = Logger.getLogger(this.getClass().getName());

    @Override
    public Mono<Student> resolveToDefinedEntity(
        FireStudent f
    ) {
        return Mono
            .just(f)
            .flatMap(
                fs -> { 

                    Mono<Optional<Date>> ocaMono = dtfs.convertStringToDate(fs.getTimestamp());

                    return ocaMono
                        .map(
                            oca -> {
                                return new Student(
                                    fs.getStudentId(), 
                                    fs.getName(), 
                                    fs.getAge(), 
                                    oca.orElse(null)
                                );
                            }
                        )
                        .subscribeOn(
                            Schedulers.parallel()
                        );
                }
            );

    }
    
}
