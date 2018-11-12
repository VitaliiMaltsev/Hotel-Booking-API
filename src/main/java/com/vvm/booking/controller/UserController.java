package com.vvm.booking.controller;

import com.vvm.booking.model.Booking;
import com.vvm.booking.model.User;
import com.vvm.booking.service.UserService;
import com.vvm.booking.util.CustomErrorType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<? extends List<User>> listAllUsers() {
        List<User> users = userService.getAllUsers();
        if (users.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(users, HttpStatus.OK);

    }
    @GetMapping("{id}/bookings")
    public ResponseEntity<?> getUserBookings(@PathVariable Long id){
        User user = userService.findById(id);
        if (user == null) {
            return new ResponseEntity<Object>(new CustomErrorType("User with id " + id
                    + " not found"), HttpStatus.NOT_FOUND);
        }
        List<Booking> userBookings = userService.getUserBookings(id);
        return new ResponseEntity<>(userBookings, HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody User user, UriComponentsBuilder ucBuilder) {
        if (userService.isUserExist(user)) {
            return new ResponseEntity<>(new CustomErrorType("Unable to create. A User with name " +
                    user.getUsername() + " already exist."),HttpStatus.CONFLICT);
        }
        userService.save(user);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/users/{id}").buildAndExpand(user.getId()).toUri());
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);

    }
}
