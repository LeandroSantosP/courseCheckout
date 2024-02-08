package com.courseapi.domain.entities;

import java.util.UUID;

import lombok.Getter;

@Getter
public class Course {
    private String id;
    private String name;
    private String description;
    private int duration;
    private double price;
    private String ref;
    private double iofPersentage;

    public Course(String id, String name, String description, int duration, double price, String ref,
            double iof_persentage) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.duration = duration;
        this.price = price * 100;
        this.ref = ref;
        this.iofPersentage = iof_persentage;
    }

    public static Course create(
            String name,
            String description,
            int duration,
            double price,
            String ref,
            double iof_persentage) {
        return new Course(UUID.randomUUID().toString(), name, description, duration, price / 100, ref, iof_persentage);
    }

    public double getPrice() {
        return this.price / 100;
    }
}
