package igris.marvin.server_socket.database.dao;

import java.util.List;
import java.util.Map;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface RepositoryDAO<T, F> {
    
    // CREATE
    public Mono<T> save(F f);
    public Flux<T> saveAll(List<F> list);

    // READ
    public Flux<T> findAll();
    public Mono<T> findByRefId(String refId);
    public Flux<T> findAllByRefId(List<String> refIdList);

    // UPDATE
    public Mono<T> update(String refId, F f);
    public Flux<T> updateAll(Map<String, F> fMap);
    public Mono<Boolean> updateFieldsByRefId(String refId);

    // DELETE
    public void deleteByRefId(String refId);
    public void deleteAll();

    // EXISTENCE
    public Mono<Boolean> existsById(String refId);

    // COUNT
    public Mono<Integer> count();
    

}