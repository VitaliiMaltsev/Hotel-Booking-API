package com.vvm.booking.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class AdditionalOption {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private String option;
    private int price;

    @JsonBackReference
    @ManyToOne
    private Room room;

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public AdditionalOption(int price, String option, Room room) {
        this.price = price;
        this.option = option;
        this.room = room;

    }

    public AdditionalOption() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option;
    }

    public int getPrice() {
        return price;
    }

}
