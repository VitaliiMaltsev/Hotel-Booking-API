package com.vvm.booking.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.hibernate.annotations.Fetch;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.*;

@Entity
public class Room implements Comparable<Object>{

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    private int floor;
    private String room_number;

    public Set<AdditionalOption> getAdditionalOptions() {
        return additionalOptions;
    }

    public void setAdditionalOptions(Set<AdditionalOption> additionalOptions) {
        this.additionalOptions = additionalOptions;
    }

    @JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<AdditionalOption> additionalOptions=new HashSet<>();

//    @JsonManagedReference
    @ManyToOne(fetch = FetchType.EAGER)
    private Category category;

    private int price;
//    @Column
    private Boolean available;

    public Boolean isAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

//    @ElementCollection
//    private Map<LocalDate, Long> days_reserved = new HashMap<LocalDate, Long>();

    @ElementCollection
    private List<LocalDate>busyPeriod =new ArrayList<>();

    public List<LocalDate> getBusyPeriod() {
        return busyPeriod;
    }

    public void setBusyPeriod(List<LocalDate> busyPeriod) {
        this.busyPeriod = busyPeriod;
    }

    @JsonBackReference
    @ManyToMany(mappedBy="rooms")
    private Set<Booking> bookings = new HashSet<Booking>();

    public Set<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(Set<Booking> bookings) {
        this.bookings = bookings;
    }

    public Room() {}

    public Room (Long id, int floor, String room_number, Category category, int price, Boolean available, List<LocalDate>busyPeriod, Set<AdditionalOption> additionalOptions) {
        this.floor = floor;
        this.id = id;
        this.room_number = room_number;
        this.category = category;
        this.available = available;
        this.additionalOptions = additionalOptions;
        this.setPrice(price);

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public String getRoom_number() {
        return room_number;
    }

    public void setRoom_number(String room_number) {
        this.room_number = room_number;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

//    public Map<LocalDate, Long> getDays_reserved() {
//        return days_reserved;
//    }
//
//    public void setDays_reserved(Map<LocalDate, Long> days_reserved) {
//        this.days_reserved = days_reserved;
//    }


    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public int compareTo(Object o) {
        return getRoom_number().compareTo(((Room) o).getRoom_number());
    }
}
