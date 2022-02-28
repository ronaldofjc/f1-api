package br.com.api.f1.models;

import br.com.api.f1.dtos.StandingDto;

import java.util.List;

public record SeasonHistory(
    String season,
    String races,
    String drivers,
    List<SeasonStanding> history
) {
    public static SeasonHistory of(final String drivers, final List<StandingDto> standingDtoList) {
        String season = standingDtoList.get(0).season();
        String races = standingDtoList.get(0).round();
        return new SeasonHistory(season, races, drivers, SeasonStanding.of(standingDtoList.get(0).driverStanding()));
    }
}
