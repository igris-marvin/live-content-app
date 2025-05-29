package igris.marvin.server_socket.database.dao;

import reactor.core.publisher.Mono;

public interface ResolverDAO<T, F> {
    public Mono<T> resolveToDefinedEntity(F f);
}
