package com.vvm.booking.repository;

import com.vvm.booking.model.AdditionalOption;
import com.vvm.booking.model.Room;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;
import java.util.Set;

@RepositoryRestResource
public interface RoomRepository extends CrudRepository<Room, Long> {
    Set<Room> findByCategoryDescription(String description);

    Optional<Room> findById(Long id);

    AdditionalOption findByAdditionalOptionsId(Long id);
}
