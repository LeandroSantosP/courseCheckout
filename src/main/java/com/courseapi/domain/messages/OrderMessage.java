package com.courseapi.domain.messages;

public record OrderMessage(String orderId, double price, String creditCardToken, String courseId) {
}