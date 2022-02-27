package br.com.api.f1.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.lang.Nullable;

import java.util.List;

public record StandingTableDto(
    @Nullable @JsonProperty("driverId") String driverId,
    @Nullable @JsonProperty("StandingsLists") List<StandingDto> standings
) {
}
