package br.com.api.f1.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

public record DriversDto(
    @JsonProperty("driverId") String driverId,
    @JsonProperty("url") String url,
    @JsonProperty("givenName") String givenName,
    @JsonProperty("familyName") String familyName,
    @JsonProperty("dateOfBirth") String dateOfBirth,
    @JsonProperty("nationality") String nationality
) {
}
