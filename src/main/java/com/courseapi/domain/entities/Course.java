package com.courseapi.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Course {
    private String id;
    private String name;
    private String description;
    private int duration;
    private double price;

    public static Course rebuild(String id, String name, String description, int duration, double price) {
        return new Course(id, name, description, duration, price * 100);
    }

    public double getPrice() {
        return this.price / 100;
    }
}
