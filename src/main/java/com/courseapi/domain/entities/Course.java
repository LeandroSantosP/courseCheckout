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

    public double getPrice() {
        return this.price / 100;
    }
}
