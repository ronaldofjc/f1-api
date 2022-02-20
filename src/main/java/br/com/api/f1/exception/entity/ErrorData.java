package br.com.api.f1.exception.entity;

public record ErrorData(
    String errorCode,
    String errorData
) {
}
