package marvin.igris.live.database.dao;

import reactor.core.publisher.Mono;

public interface ResolverDAO<T, F> {
    public Mono<T> resolveToDefinedEntity(F f);
    public Mono<F> resolveToMappingEntity(T t);
}
