package com.courseapi.domain.entities;

import lombok.Getter;

@Getter
public class Course {
    private String id;
    private String name;
    private String description;
    private int duration;
    private double price;
    private String ref;

    public Course(String id, String name, String description, int duration, double price, String ref) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.duration = duration;
        this.price = price * 100;
        this.ref = ref;
    }

    public static Course create(String name, String description, int duration, double price, String ref) {
        return new Course(null, name, description, duration, price / 100, ref);
    }

    public double getPrice() {
        return this.price / 100;
    }
}
