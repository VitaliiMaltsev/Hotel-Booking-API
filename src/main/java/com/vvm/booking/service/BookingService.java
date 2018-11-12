package com.vvm.booking.service;

import com.vvm.booking.model.AdditionalOption;
import com.vvm.booking.model.Booking;
import com.vvm.booking.model.Room;
import com.vvm.booking.repository.BookingRepository;
import com.vvm.booking.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
public class BookingService {

    private final BookingRepository bookingRepository;
    private final RoomRepository roomRepository;

    @Autowired
    public BookingService(BookingRepository bookingRepository, RoomRepository roomRepository) {
        this.bookingRepository = bookingRepository;
        this.roomRepository = roomRepository;
    }


    public Set<Booking> getAllBookings() {

        SortedSet<Booking> bookings = new TreeSet<>(Comparator.comparing(Booking::getId));
        bookingRepository.findAll().forEach(bookings::add);
        for (Booking booking:bookings){
            AtomicInteger optionsPrice = new AtomicInteger();
            booking.getRooms().forEach(room -> {
                if(!room.getAdditionalOptions().isEmpty()){
                    optionsPrice.set(room.getAdditionalOptions().stream().mapToInt(AdditionalOption::getPrice).sum());
                };
            });
            int roomPrice = booking.getRooms().stream().mapToInt(Room::getPrice).sum();
            booking.setTotalPrice(optionsPrice.intValue() + roomPrice);
            if(LocalDate.now().isAfter(booking.getEnd_date())){
                booking.setValid(false);
            }else {
                booking.setValid(true);
            }
            bookingRepository.save(booking);
        }
        return bookings;
    }

    public boolean save(Booking booking) {
        for (Room room: booking.getRooms()){
            if(roomRepository.findById(room.getId()).orElse(null)==null){
                return false;
            }
            for (AdditionalOption option: room.getAdditionalOptions()){
                if(option.getId()<0||option.getId()>3){
                    return false;
                }
            }
            room.setAvailable(false);
            room.setBusyPeriod(booking.getBegin_date().datesUntil(booking.getEnd_date()).collect(Collectors.toList()));
            roomRepository.save(room);

        }

        bookingRepository.save(booking);

        return true;
    }
}
