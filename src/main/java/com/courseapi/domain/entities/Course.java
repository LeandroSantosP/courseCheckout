package com.courseapi.domain.entities;

public record Course(
    String id,
    String name,
    String description,
    int duration,
    int price) {

}
