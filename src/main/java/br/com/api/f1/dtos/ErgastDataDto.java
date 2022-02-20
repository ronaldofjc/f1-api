package br.com.api.f1.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ErgastDataDto(
    @JsonProperty("MRData") MrDataDto mrDataDto) {
}
