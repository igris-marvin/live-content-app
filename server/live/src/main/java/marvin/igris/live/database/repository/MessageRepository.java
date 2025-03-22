package marvin.igris.live.database.repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.Executors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.google.api.core.ApiFuture;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import marvin.igris.live.database.dao.RepositoryDAO;
import marvin.igris.live.database.entity.Message;
import marvin.igris.live.database.resolver.MessageResolver;
import marvin.igris.live.firebase.mapping.FireMessage;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public class MessageRepository implements RepositoryDAO<Message, FireMessage> {

    @Autowired
    private FirebaseDatabase firedb;

    @Autowired
    private MessageResolver solv;

    private final String path = "message";

    @Override
    public Mono<Message> save(
        FireMessage f 
    ) {
        return Mono
            .create(
                sink -> {
                    try {

                        ApiFuture<Void> fut = firedb
                            .getReference()
                            .child(path)
                            .push()
                            .setValueAsync(f);

                        fut
                            .addListener(
                                () -> {
                                    try {
                                        fut.get();
                                        System.out.println("# [ Message Saved ]");
                                    } catch (Exception e) {
                                        System.out.println("# [ Error Saving Message ]");
                                        sink.error(e);
                                    }
                                }, 
                                Executors.newSingleThreadExecutor()
                            );
                        
                    } catch (Exception e) {
                        System.out.println("# [ Error Saving Message ]");
                        sink.error(e);
                    }
                }
            );
    }

    @Override
    public Mono<Message> findById(
        String id
    ) {
        Mono<Optional<FireMessage>> optMono = Mono
            .create(
                sink -> {
                    try {

                        firedb
                            .getReference()
                            .child(path)
                            .child(id)
                            .addListenerForSingleValueEvent(
                                new ValueEventListener() {

                                    @Override
                                    public void onDataChange(DataSnapshot snapshot) {

                                        Optional<FireMessage> opt = Optional
                                            .ofNullable(snapshot.getValue(FireMessage.class));

                                        if (opt.isPresent()) {

                                            FireMessage val = opt.get();

                                            val.setMessageId(snapshot.getKey());

                                            sink.success(Optional.ofNullable(val));

                                        } else {
                                            sink.success(Optional.empty());
                                        }

                                    }

                                    @Override
                                    public void onCancelled(DatabaseError e) {
                                        System.out.println("\n# [ Error Fetching Message By Id ]" + e.getMessage() + "\n");
                                        sink.error(e.toException());
                                    }
                                    
                                }
                            );
                        
                    } catch (Exception e) {
                        System.out.println("\n# [ Error Fetching Message By Id ]" + e.getMessage() + "\n");
                        sink.error(e);
                    }
                }
            );


        return optMono
            .flatMap(
                opt -> {
                    return opt.isPresent() ?
                        solv.resolveToDefinedEntity(opt.get()) :
                        Mono.error(new Throwable("\n# [ Error Fetching Message By Id ]\n"));
                }
            );
    }

    @Override
    public Flux<Message> findAll() {
        Flux<Optional<FireMessage>> optFlux = Flux
            .create(
                sink -> {
                    try {

                        firedb
                            .getReference()
                            .child(path)
                            .addListenerForSingleValueEvent(
                                new ValueEventListener() {

                                    @Override
                                    public void onDataChange(DataSnapshot snapshot) {

                                        snapshot.getChildren().forEach(
                                            snap -> {
                                                Optional<FireMessage> opt = Optional
                                                    .ofNullable(snap.getValue(FireMessage.class));
        
                                                if (opt.isPresent()) {
        
                                                    FireMessage val = opt.get();
        
                                                    val.setMessageId(snap.getKey());
        
                                                    sink.next(Optional.ofNullable(val));
        
                                                } else {
                                                    sink.next(Optional.empty());
                                                }
                                            }
                                        );
                                    }

                                    @Override
                                    public void onCancelled(DatabaseError e) {
                                        System.out.println("\n# [ Error Fetching Message By Id ]" + e.getMessage() + "\n");
                                        sink.error(e.toException());
                                    }
                                    
                                }
                            );
                        
                    } catch (Exception e) {
                        System.out.println("\n# [ Error Fetching Message By Id ]" + e.getMessage() + "\n");
                        sink.error(e);
                    }
                }
            );


        return optFlux
            .flatMap(
                opt -> {
                    return opt.isPresent() ?
                        solv.resolveToDefinedEntity(opt.get()) :
                        Mono.error(new Throwable("\n# [ Error Fetching Message By Id ]\n"));
                }
            ); 
    }

    @Override
    public Flux<Message> findAllByRefId(
        List<String> list
    ) {
        return Flux
            .fromIterable(list)
            .flatMap(
                id -> {
                    return this.findById(id);
                }
            )
            .onErrorContinue(
                (e, data) -> {
                    System.out.println("\n# [ Error fetching Message ] " + e.getMessage() + "\n");
                }
            );
    }

    @Override
    public void delete(
        String id
    ) {
        Mono
            .just(id)
            .subscribe(
                data -> {
                    ApiFuture<Void> fut = firedb
                        .getReference()
                        .child(path)
                        .child(id)
                        .removeValueAsync();

                    fut
                        .addListener(
                            () -> {
                                try {
                                    fut.get();
                                    System.out.println("\n# [ Message Deleted ]\n");
                                } catch (Exception e) {
                                    System.out.println("\n# [ Error Deleting Message ]\n");
                                }
                            }, 
                            Executors.newSingleThreadExecutor()
                        );
                }
            );
    }

    @Override
    public Mono<Boolean> updateFieldsById(
        String id, 
        Map<String, Object> updates
    ) {
        return Mono
            .create(
                sink -> {
                    try {

                        ApiFuture<Void> fut = firedb
                            .getReference()
                            .child(path)
                            .child(id)
                            .updateChildrenAsync(updates);

                        fut
                            .addListener(
                                () -> {
                                    try {
                                        fut.get();
                                        System.out.println("\n# [ Message with ID: "+id+" Updated ]\n");
                                        sink.success(true);
                                    } catch (Exception e) {
                                        System.out.println("\n# [ Error Updating Message with ID: "+id+" ]\n");
                                        sink.error(e);
                                    }
                                }, 
                                Executors.newSingleThreadExecutor()
                            );
                        
                    } catch (Exception e) {
                        System.out.println("\n# [ Error Updating Message with ID  ]" + e.getMessage() + "\n");
                        sink.error(e);
                    }
                }
            );
    }
}
