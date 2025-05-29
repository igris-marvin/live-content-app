# class level

@Component

# method level

    @Autowired
    private StorageClient client;
    
    @Autowired
    private DateTimeFormatterService dtfs;

    @Autowired
    private Repository imageobjectRepo;

    private final Logger logger = Logger.getLogger(this.getClass().getName());

    @Scheduled(
        fixedRate = 30,
        timeUnit = TimeUnit.DAYS
    )
    public void scheduled ___________ Update() {
        // get images
        imageobjectRepo
            .findAll()
            .flatMap(
                imageobject -> {
                    
                    Mono<Optional<String>> ouaMono = dtfs.convertDateToString(Date.from(Instant.now()));

                    return ouaMono
                        .flatMap(
                            oua -> {
                                return imageobjectRepo.updateUrlByRefId(
                                    imageobject.get__________(), 
                                    client
                                        .bucket()
                                        .get(imageobject.getPath())
                                        .signUrl(
                                            365,
                                            TimeUnit.DAYS,
                                            SignUrlOption.withV2Signature()
                                        )
                                        .toExternalForm(), 
                                    oua.isPresent() ? oua.get() : null
                                );
                            }
                        );
                }
            )
            .onErrorContinue(
                (e, data) -> {
                    logger.log(Level.SEVERE, "\n\n -> [ Error Class: " + e.getClass() + " || Message: " + e.getMessage() + " || Origin Class: " + this.getClass().getName() + " ] ");
                    e.printStackTrace();
                }
            )
            .subscribe();

            logger.log(Level.INFO, "\n___ Scheduler to update signed URLs every 30 days, the signed URLs are created to last a full year (365 DAYS)");
    }

# printers

# INFO

logger.log(Level.INFO, "\n\n -> [ Error Class: " + e.getClass() + " || Message: " + e.getMessage() + " || Origin Class: " + this.getClass().getName() + " ] ");

# ERROR

logger.log(Level.SEVERE, "\n\n -> [ Error Class: " + e.getClass() + " || Message: " + e.getMessage() + " || Origin Class: " + this.getClass().getName() + " ] ");

# REPOSITORY

public Mono<Boolean> updateUrlByRefId(
        String refId,
        String url,
        String updatedAt
    ) {

        return Mono
            .create(
                sink -> {
                    try {
                        Map<String, Object> updates = new HashMap<>();
                        updates.put("url", url);
                        updates.put("updatedAt", updatedAt);
    
                        firedb
                            .getReference()
                            .child(path)
                            .child(refId)
                            .updateChildrenAsync(updates)
                            .addListener(
                                () -> {
                                    sink.success(true);
                                }, 
                                MoreExecutors.directExecutor()
                            );
                    } catch (Exception e) {
                        logger.log(Level.SEVERE, "\n\n -> [ Error Class: " + e.getClass() + " || Message: " + e.getMessage() + " || Origin Class: " + this.getClass().getName() + " ] ");
                        e.printStackTrace();
                        sink.error(e);
                    } 
                }
            );
    }


# CODE

.flatMap(
                                                    new_room -> {

                                                        // save room
                                                            // create acc room amentities, amenity id is tored in the acc room amenity object id
                                                            // reflect on room type
                                                            // reflect on room bed types

                                                        // CREATE ROOM
                                                        return arRepo
                                                            .save( 
                                                                new FireAccommodationRoom(
                                                                    null,
                                                                    new_room.getReferenceNumber(),
                                                                    new_room.getTitle(),
                                                                    new_room.getDescription(),
                                                                    new_room.getAvailableRooms(),
                                                                    new_room.getRemaining(),
                                                                    new_room.getWidth(),
                                                                    new_room.getHeight(),
                                                                    new_room.getLength(),
                                                                    new_room.getArea(),
                                                                    new_room.getPricePerNight(),
                                                                    new_room.getBreakfastAdditionalCost(),
                                                                    new_room.getDiscount(),
                                                                    new_room.getIsChildrenDiscount(),
                                                                    new_room.getIsPercentageDiscount(),
                                                                    new_room.getBreakfastPolicy(),
                                                                    new_room.getChildrenPolicy(),
                                                                    new_room.getCancellationPolicy(),
                                                                    new_room.getFlexibleTicket(),
                                                                    new_room.getPetsAllowed(),
                                                                    new_room.getSmokingAllowed(),
                                                                    new_room.getMaxAdults(),
                                                                    new_room.getMaxChildren(),
                                                                    new_room.getMaxOccupancy(),
                                                                    new_room.getNumOfBeds(),
                                                                    new_room.getNumOfBedRooms(),
                                                                    t1.getT5().orElse(null),
                                                                    null,
                                                                    null, // LEAVE
                                                                    null, // DONE - REFLECTED RELS AFTER ROOM CREATION
                                                                    new_room.getRoomType().getRoomTypeId(), // DONE
                                                                    null, // DONE - REFLECTED BED TYPES RELS AFTER CREATION
                                                                    acc.getAccommodationId(), // DONE - REFLECTED ACC REL ON CREATION
                                                                    null // LEAVE
                                                                )
                                                            )
                                                            .flatMap(
                                                                room -> {

                                                                    // create acc room amenities
                                                                    Mono<Optional<List<String>>> araIdListMono = Mono
                                                                        .justOrEmpty(new_room.getAccommodationRoomAmenities())
                                                                        .flatMapMany(
                                                                            new_acc_room_amenity_list -> { // from chosen amenities list to asociate with the room, create a flux stream from them
                                                                                return Flux
                                                                                    .fromIterable(new_acc_room_amenity_list);
                                                                            }
                                                                        )
                                                                        .flatMap(
                                                                            new_acc_room_amenity -> { 
                                                                                // for each chosen amenity 
                                                                                    // create an acc room amenity, include REL and save instance to the db
                                                                                    // reflect the new acc room amenity to the amenity to establish a REL
                                                                                return araRepo
                                                                                    .save( 
                                                                                        // create acc room amenity
                                                                                        new FireAccommodationRoomAmenity(
                                                                                            null, 
                                                                                            null, // LEAVE
                                                                                            t1.getT5().orElse(null), 
                                                                                            null, 
                                                                                            new_acc_room_amenity.getAccommodationRoomAmenityId(), // the amenity id was saved in this field
                                                                                            room.getAccommodationRoomId()
                                                                                        )
                                                                                    )
                                                                                    .flatMap(
                                                                                        // reflect REL to amenity
                                                                                        acc_room_amenity -> {

                                                                                            return amRepo
                                                                                                // get acc room amenities that already exist in  this amenity, otherwise get an empty list
                                                                                                .getAccRoomAmenityMappingArrayByRefId(
                                                                                                    new_acc_room_amenity.getAccommodationRoomAmenityId()
                                                                                                )
                                                                                                .flatMap(
                                                                                                    aralist -> { // add the newly created acc room amenity id to the list the update the list for amenity in the DB

                                                                                                        Map<String, Object> ramUpdates = new HashMap<>();

                                                                                                        aralist.add(acc_room_amenity.getAccommodationRoomAmenityId());

                                                                                                        ramUpdates.put("updatedAt", t1.getT5().orElse(null));
                                                                                                        ramUpdates.put("accommodationRoomAmenities", aralist);

                                                                                                        return amRepo
                                                                                                            .updateFieldsByRefId(
                                                                                                                new_acc_room_amenity.getAccommodationRoomAmenityId(),
                                                                                                                ramUpdates
                                                                                                            )
                                                                                                            .flatMap(
                                                                                                                amenity_for_rooms_updated -> amenity_for_rooms_updated 
                                                                                                                    ? Mono.just(acc_room_amenity.getAccommodationRoomAmenityId()) 
                                                                                                                    : Mono.error(new Exception("Error, while updating room amenity"))
                                                                                                            );
                                                                                                    }
                                                                                                );
                                                                                        }
                                                                                    );
                                                                            }
                                                                        )
                                                                        .collectList()
                                                                        .map(
                                                                            list -> {
                                                                                return Optional.ofNullable(list);
                                                                            }
                                                                        )
                                                                        .defaultIfEmpty(
                                                                            Optional.empty()
                                                                        )
                                                                        .onErrorResume(
                                                                            e -> {
                                                                                logger.log(Level.SEVERE, "\n\n -> [ Error Class: " + e.getClass() + " || Message: " + e.getMessage() + " || Origin Class: " + this.getClass().getName() + " ] ");

                                                                                return Mono.just(Optional.empty());
                                                                            }
                                                                        );

                                                                    // reflect rel on room type, return Boolean
                                                                    Mono<Boolean> roomTypesMono = rtRepo
                                                                        .getAccRoomMappingArrayByRefId(
                                                                            new_room.getRoomType().getRoomTypeId()
                                                                        )
                                                                        .flatMap(
                                                                            acc_room_id_list -> {
                                                                                Map<String, Object> rtUpdates = new HashMap<>();

                                                                                acc_room_id_list.add(room.getAccommodationRoomId());

                                                                                rtUpdates.put("updatedAt", t1.getT5().orElse(null));
                                                                                rtUpdates.put("accommodationRooms", acc_room_id_list);

                                                                                return rtRepo
                                                                                    .updateFieldsByRefId(
                                                                                        new_room.getRoomType().getRoomTypeId(),
                                                                                        rtUpdates
                                                                                    );
                                                                            }
                                                                        );

                                                                    // update, reflect acc room on room bed types, return a list ids of the related 
                                                                    // room bed types
                                                                    Mono<Optional<List<String>>> rbtListMono = Mono
                                                                            .justOrEmpty(new_room.getRoomBedTypes())
                                                                            .flatMapMany(
                                                                                list -> {
                                                                                    return Flux
                                                                                        .fromIterable(
                                                                                            list
                                                                                        );
                                                                                }
                                                                            )
                                                                            .flatMap(
                                                                                room_bed_type -> {
                                                                                    return rbtRepo
                                                                                        .getAccRoomMappingArrayByRefId(
                                                                                            room_bed_type.getRoomBedTypeId()
                                                                                        )
                                                                                        .flatMap(
                                                                                            list -> {
                                                                                                Map<String, Object> rbtUpdates = new HashMap<>();

                                                                                                list.add(room.getAccommodationRoomId());

                                                                                                rbtUpdates.put("updatedAt", t1.getT5().orElse(null));
                                                                                                rbtUpdates.put("accommodationRooms", list);

                                                                                                return rbtRepo
                                                                                                    .updateFieldsByRefId(
                                                                                                        room_bed_type.getRoomBedTypeId(),
                                                                                                        rbtUpdates
                                                                                                    )
                                                                                                    .flatMap(
                                                                                                        room_bed_type_updated -> room_bed_type_updated 
                                                                                                            ? Mono.just(room_bed_type.getRoomBedTypeId()) 
                                                                                                            : Mono.error(new Exception("Error, while updating room amenity"))
                                                                                                    );
                                                                                            }
                                                                                        );
                                                                                }
                                                                            )
                                                                            .collectList()
                                                                            .map(
                                                                                list -> {
                                                                                    return Optional.ofNullable(list);
                                                                                }
                                                                            )
                                                                            .defaultIfEmpty(
                                                                                Optional.empty()
                                                                            )
                                                                            .onErrorResume(
                                                                                e -> {
                                                                                    logger.log(Level.SEVERE, "\n\n -> [ Error Class: " + e.getClass() + " || Message: " + e.getMessage() + " || Origin Class: " + this.getClass().getName() + " ] ");
    
                                                                                    return Mono.just(Optional.empty());
                                                                                }
                                                                            );

                                                                    // ZIP RESULTS, update the acc room RELS then return the id of the acc room
                                                                    return Mono
                                                                        .zip(
                                                                            araIdListMono,
                                                                            roomTypesMono,
                                                                            rbtListMono
                                                                        )
                                                                        .flatMap(
                                                                            t3 -> {
                                                                                Map<String, Object> accRoomUpdates = new HashMap<>();

                                                                                accRoomUpdates.put("accommodationRoomAmenities", t3.getT1().orElse(null));
                                                                                accRoomUpdates.put("roomBedTypes", t3.getT3().orElse(null));
                                                                                accRoomUpdates.put("updatedAt", t1.getT5().orElse(null));

                                                                                return arRepo
                                                                                    .updateFieldsByRefId(
                                                                                        room.getAccommodationRoomId(), 
                                                                                        accRoomUpdates
                                                                                    )
                                                                                    .flatMap(
                                                                                        acc_room_updated -> {
                                                                                            if (acc_room_updated && t3.getT2()) {
                                                                                                return Mono.just(room.getAccommodationRoomId());
                                                                                            } else {
                                                                                                return Mono.error(new Exception("Error while updating room"));
                                                                                            }
                                                                                        }
                                                                                    );
                                                                            }
                                                                        );
                                                                }
                                                            );
                                                    }