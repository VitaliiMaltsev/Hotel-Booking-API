package com.vvm.booking.controller;

import com.vvm.booking.model.Booking;
import com.vvm.booking.model.Room;
import com.vvm.booking.model.User;
import com.vvm.booking.service.BookingService;
import com.vvm.booking.service.RoomService;
import com.vvm.booking.util.CustomErrorType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Set;

@RestController
@RequestMapping("/bookings")
public class BookingController {

    private final BookingService bookingService;

    @Autowired
    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @GetMapping
    public ResponseEntity<? extends Set<Booking>> getAllBookings() {
        Set<Booking> bookings = bookingService.getAllBookings();
        if (bookings.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(bookings, HttpStatus.OK);

    }
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createBooking(@RequestBody Booking booking, UriComponentsBuilder ucBuilder) {
//        if (userService.isUserExist(user)) {
//            return new ResponseEntity<>(new CustomErrorType("Unable to create. A User with name " +
//                    user.getUsername() + " already exist."),HttpStatus.CONFLICT);
//        }
        if(!bookingService.save(booking)){
            return new ResponseEntity<>(new CustomErrorType("Unable to create booking. A Room with this ID not exists"),HttpStatus.CONFLICT);
        };
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/bookings/{id}").buildAndExpand(booking.getId()).toUri());
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);

    }
}
