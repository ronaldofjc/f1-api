package br.com.api.f1.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.lang.Nullable;

import java.util.List;

public record DriverStandingDto(
    @Nullable @JsonProperty("position") String position,
    @Nullable @JsonProperty("positionText") String positionText,
    @Nullable @JsonProperty("points") String points,
    @Nullable @JsonProperty("wins") String wins,
    @Nullable @JsonProperty("Driver") DriversDto driver,
    @Nullable @JsonProperty("Constructors") List<ConstructorDto> constructors
) {
}
