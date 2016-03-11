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
    public int id;

    @Column(nullable = false)
    public String make;

    @Column(nullable = false)
    public String model;

    @Column(nullable = false)
    public int year;

    @Column(nullable = false)
    public String color;

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
