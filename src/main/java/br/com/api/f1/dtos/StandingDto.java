package br.com.api.f1.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.lang.Nullable;

import java.util.List;

public record StandingDto(
    @Nullable @JsonProperty("season") String season,
    @Nullable @JsonProperty("round") String round,
    @Nullable @JsonProperty("DriverStandings") List<DriverStandingDto> driverStanding
) {

}
