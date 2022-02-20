package br.com.api.f1.models;

public record Driver(
    String driverId,
    String url,
    String givenName,
    String familyName,
    String dateOfBirth,
    String nationality
) {
}
