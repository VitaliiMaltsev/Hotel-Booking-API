package com.vvm.booking.service;

import com.vvm.booking.model.Booking;
import com.vvm.booking.model.User;
import com.vvm.booking.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        userRepository.findAll().forEach(users::add);
        return users;
    }

    public List<Booking> getUserBookings(Long id) {
        User user = userRepository.findById(id).orElse(null);
        return new ArrayList<>(user.getBookings());
    }

    public void save(User user) {

        userRepository.save(user);
    }

    public User findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public boolean isUserExist(User user) {
        User userFromDB = userRepository.findByUsername(user.getUsername());
        return userFromDB != null;
    }
}
