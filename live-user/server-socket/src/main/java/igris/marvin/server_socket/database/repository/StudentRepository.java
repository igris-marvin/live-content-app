package igris.marvin.server_socket.database.repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import igris.marvin.server_socket.database.dao.RepositoryDAO;
import igris.marvin.server_socket.database.entity.Student;
import igris.marvin.server_socket.database.firestore.FireStudent;
import igris.marvin.server_socket.database.resolver.StudentResolver;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public class StudentRepository implements RepositoryDAO<Student, FireStudent> {

    @Autowired
    private FirebaseDatabase firedb;

    @Autowired
    private StudentResolver solv;

    private final String path = "student";

    private final Logger logger = Logger.getLogger(this.getClass().getName());

    @Override
    public Mono<Student> save(
        FireStudent f
    ) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'save'");
    }

    @Override
    public Flux<Student> saveAll(List<FireStudent> list) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'saveAll'");
    }

    @Override
    public Flux<Student> findAll() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAll'");
    }

    @Override
    public Mono<Student> findByRefId(
        String refId
    ) {

        Mono<Optional<FireStudent>> optMono = Mono
            .create(
                sink -> {
                    try {

                        firedb.getReference().child(path).child(refId).addListenerForSingleValueEvent(
                            new ValueEventListener() {

                                @Override
                                public void onDataChange(DataSnapshot snapshot) {

                                    Optional<FireStudent> opt = Optional
                                        .ofNullable(
                                            snapshot
                                                .getValue(
                                                    FireStudent.class
                                                )
                                        );

                                    if(opt.isPresent()) {
                                        FireStudent val = opt.get();

                                        val.setStudentId(snapshot.getKey());

                                        sink.success(Optional.ofNullable(val));
                                    } else {
                                        sink.success(Optional.empty());
                                    }
                                }

                                @Override
                                public void onCancelled(DatabaseError e) {
                                    logger.log(Level.SEVERE, "\n\n -> [ Error Class: " + e.getClass() + " || Message: " + e.getMessage() + " || Origin Class: " + this.getClass().getName() + " ] ");
                                    e.toException().printStackTrace();
                                    sink.error(e.toException());
                                }
                            }
                        );
                            
                    } catch (Exception e) {
                        logger.log(Level.SEVERE, "\n\n -> [ Error Class: " + e.getClass() + " || Message: " + e.getMessage() + " || Origin Class: " + this.getClass().getName() + " ] ");
                        e.printStackTrace();
                        sink.error(e);
                    }
                }
            );

        return optMono
            .flatMap(
                opt -> {
                    return opt.isPresent()
                        ? solv.resolveToDefinedEntity(opt.get())
                        : Mono.error(new Throwable("Could not find student")); // TODO customize for each repository class
                }
            );
    }

    @Override
    public Flux<Student> findAllByRefId(List<String> refIdList) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAllByRefId'");
    }

    @Override
    public Mono<Student> update(String refId, FireStudent f) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public Flux<Student> updateAll(Map<String, FireStudent> fMap) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateAll'");
    }

    @Override
    public Mono<Boolean> updateFieldsByRefId(String refId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateFieldsByRefId'");
    }

    @Override
    public void deleteByRefId(String refId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteByRefId'");
    }

    @Override
    public void deleteAll() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteAll'");
    }

    @Override
    public Mono<Boolean> existsById(String refId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'existsById'");
    }

    @Override
    public Mono<Integer> count() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'count'");
    }
    
}
