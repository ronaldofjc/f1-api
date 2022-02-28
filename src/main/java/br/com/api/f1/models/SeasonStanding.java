package br.com.api.f1.models;

import br.com.api.f1.dtos.DriverStandingDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public record SeasonStanding(
    String position,
    String points,
    String wins,
    Driver driver,
    List<Constructor> constructors
) {
    public static List<SeasonStanding> of(final List<DriverStandingDto> driverStandings) {
        final List<SeasonStanding> driverStandingList = new ArrayList<>();
        driverStandings.forEach(d -> driverStandingList.add(SeasonStanding.of(d)));
        return driverStandingList;
    }

    static SeasonStanding of(final DriverStandingDto driverStandingDto) {
        return new SeasonStanding(
            driverStandingDto.position(),
            driverStandingDto.points(),
            driverStandingDto.wins(),
            Driver.of(driverStandingDto.driver()),
            Optional.ofNullable(driverStandingDto.constructors()).map(Constructor::of).orElse(null)
        );
    }
}
