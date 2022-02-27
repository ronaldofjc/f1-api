package br.com.api.f1.models;

import br.com.api.f1.dtos.DriversDto;
import br.com.api.f1.dtos.StandingDto;

import java.util.List;
import java.util.Optional;

public record DriverHistory(
    Driver driver,
    String seasons,
    Integer wins,
    List<Standing> historic
) {
    public static DriverHistory of(final String seasons, final List<StandingDto> standings) {
        Optional<Driver> driver = Optional.empty();
        Optional<DriversDto> driversDto = Optional.empty();
        int wins = 0;

        if (standings.stream().findFirst().isPresent() && standings.stream().findFirst().get().driverStanding().stream().findFirst().isPresent()) {
            driversDto = Optional.ofNullable(standings.get(0).driverStanding().get(0).driver());
            wins = standings.stream().mapToInt(s -> Integer.parseInt(s.driverStanding().get(0).wins())).sum();
        }

        if (driversDto.isPresent()) {
            driver = Optional.of(Driver.of(driversDto.get()));
        }

        return new DriverHistory(driver.orElse(null), seasons, wins, Standing.of(standings));
    }
}
