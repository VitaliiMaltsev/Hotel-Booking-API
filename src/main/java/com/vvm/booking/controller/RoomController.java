package com.vvm.booking.controller;

import com.vvm.booking.model.Booking;
import com.vvm.booking.model.Room;
import com.vvm.booking.model.User;
import com.vvm.booking.service.RoomService;
import com.vvm.booking.service.UserService;
import com.vvm.booking.util.CustomErrorType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/rooms")
public class RoomController {

    private final RoomService roomService;

    @Autowired
    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @GetMapping
    public ResponseEntity<? extends Set<Room>> listAllRooms() {
        Set<Room> rooms = roomService.getAllRooms();
        if (rooms.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(rooms, HttpStatus.OK);

    }

    @GetMapping("/available")
    public ResponseEntity<?> listAvailableRooms() {
        Set<Room> rooms = roomService.getAvailableRooms();
        if (rooms.isEmpty()) {
            return new ResponseEntity<>(new CustomErrorType("All rooms are busy"), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(rooms, HttpStatus.OK);

    }

    @GetMapping("/category/{category}")
    public ResponseEntity<?> getRoomsByCategory(@PathVariable String category) {
        Set<Room> rooms = roomService.findByCategory(category);
        if (rooms.isEmpty()) {
            return new ResponseEntity<Object>(new CustomErrorType("Rooms with category " + category
                    + " not found"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(rooms, HttpStatus.OK);
    }

    @GetMapping("/{beginDate}/{endDate}")
    public ResponseEntity<?> getRoomsByDate(
            @DateTimeFormat(pattern="yyyy-MM-dd") @PathVariable("beginDate") LocalDate beginDate,
            @DateTimeFormat(pattern="yyyy-MM-dd") @PathVariable("endDate") LocalDate endDate) {
        Set<Room> rooms = roomService.findRoomsForCurrentPeriod(beginDate, endDate);
        if (rooms.isEmpty()) {
            return new ResponseEntity<Object>(new CustomErrorType("No available rooms for current period " +
                    beginDate + " - " + endDate), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(rooms, HttpStatus.OK);
    }

}
