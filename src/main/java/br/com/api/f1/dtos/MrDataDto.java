package br.com.api.f1.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.lang.Nullable;

public record MrDataDto(
    String xmlns,
    String series,
    String url,
    String limit,
    String offset,
    String total,
    @Nullable @JsonProperty("DriverTable") DriverTableDto driverTable,
    @Nullable @JsonProperty("StandingsTable") StandingTableDto standingTable
) {
}
