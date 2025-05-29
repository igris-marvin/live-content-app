

# class anno
@Repository

# interface

implements EazyDAO<, Fire>

# field declarations

    @Autowired
    private FirebaseDatabase firedb;

    @Autowired
    private Resolver solv;

    private final String path

    private final Logger logger = Logger.getLogger(this.getClass().getName());

# INFO

logger.log(Level.INFO, "\n\n -> [ Error Class: " + e.getClass() + " || wMessage: " + e.getMessage() + " || Origin Class: " + this.getClass().getName() + " ] ");

# ERROR

logger.log(Level.SEVERE, "\n\n -> [ Error Class: " + e.getClass() + " || Message: " + e.getMessage() + " || Origin Class: " + this.getClass().getName() + " ] ");
e.printStackTrace();

logger.log(Level.INFO, "\n\n -> [ Strike 1 ] ");

# save

Mono<Optional<String>> optMono = Mono.create(
            sink -> {
                DatabaseReference ref = firedb
                    .getReference()
                    .child(path)
                    .push();

                ApiFuture<Void> future = ref
                    .setValueAsync(f);

                future
                    .addListener(
                        () -> {
                            try {
                                future.get();
                                sink.success(Optional.ofNullable(ref.getKey()));
                            } catch (Exception e) {
                                logger.log(Level.INFO, " -> [ " + e.getMessage() + " ] ");
                                e.printStackTrace();
                                sink.error(e);
                            }
                        },
                        Executors.newSingleThreadExecutor()
                    );
                }
            );

            return optMono
                .flatMap(
                    opt -> {
                        return opt.isPresent() ?
                            findByRefId(opt.get()) :
                            Mono.error(new Throwable("Could not create !" + this.toString())); // TODO customize for each repository class
                    }
                );

# findAll()

Flux<Optional<___>> flux = Flux
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

                                        snapshot
                                            .getChildren()
                                            .forEach(
                                                snap -> {
                                                    Optional<___> opt = Optional
                                                        .ofNullable(snap.getValue(___.class));
                                                
                                                    if(opt.isPresent()) {
                                                        ___ val = opt.get();

                                                        val.set/////////(snap.getKey());

                                                        sink.next(Optional.ofNullable(val));
                                                    } else {
                                                        sink.next(Optional.empty());
                                                    }
                                                }
                                            );

                                        sink.complete();
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

        return flux
            .flatMap(
                opt -> {
                    return opt.isPresent() ?
                        solv.resolveToDefinedEntity(opt.get()) :
                        Mono.error(new Throwable("Could not find "));// TODO customize for each repository class
                }
            );

# findByRefId

Mono<Optional<___>> optMono = Mono
            .create(
                sink -> {
                    try {

                        firedb.getReference().child(path).child(refId).addListenerForSingleValueEvent(
                            new ValueEventListener() {

                                @Override
                                public void onDataChange(DataSnapshot snapshot) {

                                    Optional<___> opt = Optional
                                        .ofNullable(
                                            snapshot
                                                .getValue(
                                                    ___.class
                                                )
                                        );

                                    if(opt.isPresent()) {
                                        ___ val = opt.get();

                                        val.set////////(snapshot.getKey());

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
                        : Mono.error(new Throwable(""));// TODO customize for each repository class
                }
            );

# findAllByRefId

return Flux
    .fromIterable(refIdList)
    .flatMap(
        refId -> this.findByRefId(refId)
    )
    .onErrorContinue(
        (e, data) -> {
            logger.log(Level.SEVERE, "\n\n -> [ Error Class: " + e.getClass() + " || Message: " + e.
            e.printStackTrace();
            getMessage() + " || Origin Class: " + this.getClass().getName() + " ] ");
        }
    );

# updateFieldsByRefId

public Mono<Boolean> updateFieldsByRefId(
        String refId,
        Map<String, Object> updates
    ) {
        return Mono
            .create(
                sink -> {
                    try {
                        
                        ApiFuture<Void> fut = firedb
                            .getReference()
                            .child(path)
                            .child(refId)
                            .updateChildrenAsync(updates);

                        fut
                            .addListener(
                                () -> {
                                    try {
                                        fut.get();
                                        sink.success(true);

                                    } catch (Exception e) {
                                        e.printStackTrace();
                                        sink.error(e);
                                    }
                                },
                                Executors.newSingleThreadExecutor()
                            );
                        
                    } catch (Exception e) {
                        logger.log(Level.SEVERE, "\n\n -> [ Error Class: " + e.getClass() + " || Message: " + e.getMessage() + " || Origin Class: " + this.getClass().getName() + " ] ");
                        e.printStackTrace();
                        Mono.error(e);
                    }
                }
            );
    }

# deleteByRefId

ApiFuture<Void> fut = firedb
            .getReference()
            .child(path)
            .child(refId)
            .removeValueAsync();

        fut
            .addListener(
                () -> {
                    try {
                        fut.get();
                    } catch (Exception e) {
                        logger.log(Level.SEVERE, "\n\n -> [ Error Class: " + e.getClass() + " || Message: " + e.getMessage() + " || Origin Class: " + this.getClass().getName() + " ] ");
                        e.printStackTrace();
                    }
                }, 
                Executors.newSingleThreadExecutor()
            );

# get mapping array list

    public Mono<List<String>> getAccAccBookingMappingArrayByRefId(
        String refId
    ) {
        return Mono
            .create(
                sink -> {

                    try {

                        firedb
                            .getReference()
                            .child(path)
                            .child(refId)
                            .addListenerForSingleValueEvent(
                                new ValueEventListener() {

                                    @Override
                                    public void onDataChange(DataSnapshot snapshot) {

                                        Optional<___> opt = Optional.ofNullable(snapshot.getValue(___.class));

                                        if(opt.isPresent()) {
                                            ___ val = opt.get();

                                            if (val.\\\\\() != null) {

                                                sink.success(val.\\\\\());
                                            } else {
                                                List<String> list = new ArrayList<>();

                                                sink.success(list);
                                            }
                                        } else {
                                            sink.error(new Throwable("|||||"));
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
    }

# get mapping value

public Mono<String> getProperty_MappingValueByRefId(
        String refId
    ) {
        return Mono
            .create(
                sink -> {

                    try {

                        firedb
                            .getReference()
                            .child(path)
                            .child(refId)
                            .addListenerForSingleValueEvent(
                                new ValueEventListener() {

                                    @Override
                                    public void onDataChange(DataSnapshot snapshot) {

                                        Optional<FireAccommodation> opt = Optional.ofNullable(snapshot.getValue(FireAccommodation.class));

                                        if(opt.isPresent()) {
                                            FireAccommodation val = opt.get();

                                            if (val.getPropertyType() != null) {

                                                sink.success(val.getPropertyType());
                                            } else {

                                                sink.error(new Throwable("Could not find property type on accommodation!"));
                                            }
                                        } else {
                                            sink.error(new Throwable("Could not find accommodation!"));
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
    }

# try catch

try {

} catch (Exception e) {
    logger.log(Level.SEVERE, "\n\n -> [ Error Class: " + e.getClass() + " || Message: " + e.getMessage() + " || Origin Class: " + this.getClass().getName() + " ] ");
    e.printStackTrace();
    sink.error(e);
}