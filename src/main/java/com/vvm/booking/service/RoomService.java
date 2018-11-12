package com.vvm.booking.service;

import com.vvm.booking.model.Booking;
import com.vvm.booking.model.Room;
import com.vvm.booking.model.User;
import com.vvm.booking.repository.BookingRepository;
import com.vvm.booking.repository.RoomRepository;
import com.vvm.booking.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;

@Service
public class RoomService {

    private final RoomRepository roomRepository;
    private final BookingRepository bookingRepository;

    @Autowired
    public RoomService(RoomRepository roomRepository, BookingRepository bookingRepository) {
        this.roomRepository = roomRepository;
        this.bookingRepository = bookingRepository;
    }

    public Set<Room> getAllRooms() {

        return getRooms();
    }

    private Set<Room> getRooms() {
        Set<Room> rooms = new HashSet<>();
        roomRepository.findAll().forEach(rooms::add);
        for (Room room : rooms) {
            for (Booking booking : bookingRepository.findAll()) {
                if (room.getBookings().contains(booking) && !LocalDate.now().isAfter(booking.getEnd_date())) {
                    room.setAvailable(false);
                    room.setBusyPeriod(booking.getBegin_date().datesUntil(booking.getEnd_date()).collect(Collectors.toList()));
                    break;
                } else {
                    room.setAvailable(true);
                }
                roomRepository.save(room);
            }
        }
        return rooms;
    }

    public Set<Room> getAvailableRooms() {
        return getRooms().stream().filter(Room::isAvailable).collect(Collectors.toSet());
    }

    public Set<Room> findByCategory(String category) {
        Set<Room> roomsByCategory = roomRepository.findByCategoryDescription(category);
        return roomsByCategory.stream().filter(Room::isAvailable).collect(Collectors.toSet());
    }

    public Set<Room> findRoomsForCurrentPeriod(LocalDate beginDate, LocalDate endDate) {
        List<LocalDate>userDates=beginDate.datesUntil(endDate).collect(Collectors.toList());
        Set<Room> availableRoomsForDates = new HashSet<>();
            for (Room room : getRooms()) {
                if (!Collections.disjoint(userDates, room.getBusyPeriod())) {
                    room.setAvailable(false);
                    } else {
                    room.setAvailable(true);
                    availableRoomsForDates.add(room);
                    }
                }
//            }
        return availableRoomsForDates;
    }
}
