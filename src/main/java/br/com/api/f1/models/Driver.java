package br.com.api.f1.models;

import br.com.api.f1.dtos.DriversDto;

public record Driver(
    String driverId,
    String url,
    String givenName,
    String familyName,
    String dateOfBirth,
    String nationality
) {
    public static Driver of(final DriversDto driversDto) {
        return new Driver(
            driversDto.driverId(),
            driversDto.url(),
            driversDto.givenName(),
            driversDto.familyName(),
            driversDto.dateOfBirth(),
            driversDto.nationality()
        );
    }
}
