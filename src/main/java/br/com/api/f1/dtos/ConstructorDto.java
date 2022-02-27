package br.com.api.f1.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ConstructorDto(
    @JsonProperty("constructorId") String constructorId,
    @JsonProperty("url") String url,
    @JsonProperty("name") String name,
    @JsonProperty("nationality") String nationality
) {
}
