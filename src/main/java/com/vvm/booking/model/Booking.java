package com.vvm.booking.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Booking/* implements Comparable<Object> */{

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;

    private LocalDate begin_date, end_date;

    private Boolean valid;

    private Integer totalPrice;

    public Boolean isValid() {
        return valid;
    }

    public void setValid(Boolean valid) {
        this.valid = valid;
    }

    public Integer getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Integer totalPrice) {
        this.totalPrice = totalPrice;
    }

    @JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Room> rooms = new HashSet<Room>();

    public Booking(){}

    public Booking(long id, LocalDate begin_date, LocalDate end_date, Boolean valid, Integer totalPrice){
        this.id = id;
        this.begin_date = begin_date;
        this.end_date = end_date;
        this.totalPrice = totalPrice;
        this.valid = valid;
    }

    public String getRoomType()
    {
        return rooms.iterator().next().getCategory().toString();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDate getBegin_date() {
        return begin_date;
    }

    public void setBegin_date(LocalDate begin_date) {
        this.begin_date = begin_date;
    }

    public LocalDate getEnd_date() {
        return end_date;
    }

    public void setEnd_date(LocalDate end_date) {
        this.end_date = end_date;
    }

    public Set<Room> getRooms() {
        return rooms;
    }

    public void setRooms(Set<Room> rooms) {
        this.rooms = rooms;
    }

//    @Override
//    public int compareTo(Object o) {
//        return 0;
//    }

//    @Override
//    public int compareTo(Object o) {
//        return getId().compareTo(((Booking) o).getId());
//    }

}
