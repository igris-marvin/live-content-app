package marvin.igris.live.database.dao;

import java.util.List;
import java.util.Map;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface RepositoryDAO<T, F> {
    public Mono<T> save(F f);
    public Mono<T> findById(String id);
    public Flux<T> findAll();
    public Flux<T> findAllByRefId(List<String> list);
    public void delete(String id);
    public Mono<Boolean> updateFieldsById(String id, Map<String, Object> updates);
}
