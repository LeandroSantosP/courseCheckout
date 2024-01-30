package com.courseapi.domain.messages;

public record PaymentFinashedMessage(String orderId, String status, String code) {
}