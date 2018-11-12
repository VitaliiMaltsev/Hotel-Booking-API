package com.vvm.booking.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Entity
public class User  implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String username;

    private String email;

    public void setId(Long id) {
        this.id = id;
    }

    public Set<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(Set<Booking> bookings) {
        this.bookings = bookings;
    }

    @OneToMany(fetch = FetchType.LAZY)
//    @JoinColumn(name = "user_id")
    @JsonIgnore
    @JoinTable(
            name = "user_bookings",
            joinColumns = {@JoinColumn(name = "user_id")}

    )
//    @JsonIgnore
    private Set<Booking> bookings =new HashSet<>();

    public User() {}

    public User(String name, String username, String password, String email) {
        this.username = username;
        this.setEmail(email);
    }

    public String getEmail() {
        return email;
    }

    public Long getId() {
        return id;
    }


    public String getUsername() {
        return username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setId(long id) {
        this.id = id;
    }

     public void setUsername(String username) {
        this.username = username;
    }

}
