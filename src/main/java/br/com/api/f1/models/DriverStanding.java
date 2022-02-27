package br.com.api.f1.models;

import br.com.api.f1.dtos.DriverStandingDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public record DriverStanding(
    String position,
    String points,
    String wins,
    List<Constructor> constructors
) {
    public static List<DriverStanding> of(final List<DriverStandingDto> driverStandings) {
        final List<DriverStanding> driverStandingList = new ArrayList<>();
        driverStandings.forEach(d -> driverStandingList.add(DriverStanding.of(d)));
        return driverStandingList;
    }

    static DriverStanding of(final DriverStandingDto driverStandingDto) {
        return new DriverStanding(
            driverStandingDto.position(),
            driverStandingDto.points(),
            driverStandingDto.wins(),
            Optional.ofNullable(driverStandingDto.constructors()).map(Constructor::of).orElse(null)
        );
    }
}
