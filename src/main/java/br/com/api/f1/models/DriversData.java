package br.com.api.f1.models;

import java.util.List;

public record DriversData(
    List<Driver> data,
    ResponseLinks links,
    Meta meta
) {
}
