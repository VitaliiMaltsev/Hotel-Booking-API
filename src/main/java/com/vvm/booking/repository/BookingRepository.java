package com.vvm.booking.repository;

import com.vvm.booking.model.Booking;
import com.vvm.booking.model.Room;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Set;

@RepositoryRestResource
public interface BookingRepository extends CrudRepository<Booking, Long> {
    Set<Booking> findByRooms(Set<Room>rooms);
}
