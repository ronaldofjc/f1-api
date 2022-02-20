package br.com.api.f1.services;

import br.com.api.f1.dtos.DriversDto;
import br.com.api.f1.dtos.ErgastDataDto;
import br.com.api.f1.exception.BusinessException;
import br.com.api.f1.infrastructure.rest.client.ErgastClient;
import br.com.api.f1.models.Driver;
import br.com.api.f1.models.DriversData;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class DriverService {

    private final ErgastClient client;
    private final PopulateResponse populateResponse;

    public DriverService(final ErgastClient client, final PopulateResponse populateResponse) {
        this.client = client;
        this.populateResponse = populateResponse;
    }

    public Driver getById(final String id) {
        final String driverId = id.concat(".json");
        final ErgastDataDto dataDto = client.getDriverById(driverId);
        return Objects.requireNonNull(dataDto.mrDataDto().driverTable()).drivers().stream().findFirst()
            .map(d ->  new Driver(d.driverId(), d.url(), d.givenName(), d.familyName(), d.dateOfBirth(), d.nationality()))
            .orElseThrow(() -> new BusinessException("Piloto não encontrado!"));
    }

    public DriversData getAll(final int page, final int size) {
        final ErgastDataDto dataDto = client.getAllDrivers();
        final List<DriversDto> drivers = dataDto.mrDataDto().driverTable().drivers();
        final int fromIndex = (page -1) * size;
        final List<DriversDto> driversList = drivers.subList(fromIndex, Math.min(fromIndex + size, drivers.size()));
        List<Driver> driverList = new ArrayList<>();
        driversList.forEach(d -> {
            Driver driver = new Driver(d.driverId(), d.url(), d.givenName(), d.familyName(), d.dateOfBirth(), d.nationality());
            driverList.add(driver);
        });
        return populateResponse.execute(driverList, page, size, drivers.size());
    }
}
