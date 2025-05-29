
@Service

    implements ResolverDAO<, Fire>


    @Autowired
    private DateTimeFormatterService dtfs;

    private final Logger logger = Logger.getLogger(this.getClass().getName());

# FRAME

return Mono
            .just(f)
            .flatMap(
                ___ -> { 

                    Mono<Optional<Date>> ocaMono = dtfs.convertStringToDate(___.getCreatedAt());
                    Mono<Optional<Date>> ouaMono = dtfs.convertStringToDate(___.getUpdatedAt());

                    return Mono
                        .zip(ocaMono, ouaMono)
                        .map(
                            t -> {
                                return new 
                            }
                        )
                        .subscribeOn(Schedulers.parallel());
                }
            );

            
# date values


    t.getT1().orElse(null),
    t.getT2().orElse(null)

# possibly null list

                Mono<Optional<List<UUU>>> olMono = Mono
                        .justOrEmpty(
                            ___()
                        )
                        .flatMapMany(
                            list -> __.findAllByRefId(list)
                        )
                        .collectList()
                        .map(
                            list -> Optional.ofNullable(list)
                        )
                        .onErrorResume(
                            e -> { 
                                logger.log(Level.INFO, "\n\n -> [ Error Class: " + e.getClass() + " || Message: " + e.getMessage() + " || Origin Class: " + this.getClass().getName() + " ] ");

                                return Mono.just(Optional.empty());
                            }
                        )
                        .defaultIfEmpty(
                            Optional.empty()
                        );

# possibly null value

Mono<Optional<UUU>> usMono = Mono
                        .justOrEmpty(
                            ?
                        )
                        .flatMap(
                            refId -> {
                                return ___.findByRefId(refId);
                            }
                        )
                        .map(
                            obj -> {
                                return Optional.ofNullable(obj);
                            }
                        )
                        .defaultIfEmpty(
                            Optional.empty()
                        )
                        .onErrorResume(
                            e -> {
                                logger.log(Level.INFO, "\n\n -> [ Error Class: " + e.getClass() + " || Message: " + e.getMessage() + " || Origin Class: " + this.getClass().getName() + " ] ");
                                return Mono.just(Optional.empty());
                            }
                        );

# basic logging

                        Logger.log(Level.INFO, "\n# [ Error while updating user role ]\n");
