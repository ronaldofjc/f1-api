package br.com.api.f1.models;

import br.com.api.f1.dtos.StandingDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public record Standing(
    String season,
    String races,
    List<DriverStanding> driverStanding
) {
    static Standing of(final StandingDto dto) {
        return new Standing(dto.season(), dto.round(), Optional.ofNullable(dto.driverStanding()).map(DriverStanding::of).orElse(null));
    }

    public static List<Standing> of(final List<StandingDto> dtos) {
        List<Standing> standings = new ArrayList<>();
        dtos.forEach(dto -> standings.add(Standing.of(dto)));
        return standings;
    }
}
