package br.com.api.f1.models;

import br.com.api.f1.dtos.ConstructorDto;

import java.util.ArrayList;
import java.util.List;

public record Constructor(
    String constructorId,
    String url,
    String name,
    String nationality
) {
    static List<Constructor> of(final List<ConstructorDto> constructorDtoList) {
        final List<Constructor> constructors = new ArrayList<>();
        constructorDtoList.forEach(c -> constructors.add(Constructor.of(c)));
        return constructors;
    }

    private static Constructor of(final ConstructorDto constructorDto) {
        return new Constructor(constructorDto.constructorId(), constructorDto.url(), constructorDto.name(), constructorDto.nationality());
    }
}
