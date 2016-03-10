package com.theironyard.entities;

import javax.persistence.*;

/**
 * Created by Caroline on 3/10/16.
 */
@Entity
@Table(name = "cars")
public class Car {
    @Id
    @GeneratedValue
    int id;

    @Column(nullable = false)
    String make;

    @Column(nullable = false)
    String model;

    @Column(nullable = false)
    int year;

    @Column(nullable = false)
    String color;

    @ManyToOne
    User user;

    public Car() {
    }

    public Car(String make, String model, int year, String color, User user) {
        this.make = make;
        this.model = model;
        this.year = year;
        this.color = color;
        this.user = user;
    }
}
