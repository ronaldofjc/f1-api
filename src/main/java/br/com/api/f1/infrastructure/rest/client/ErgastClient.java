package br.com.api.f1.infrastructure.rest.client;

import br.com.api.f1.dtos.ErgastDataDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "${feign.rest.ergast.name}", url = "${feign.rest.ergast.url}")
public interface ErgastClient {

    @GetMapping(value = "/drivers.json?limit=1000", produces = MediaType.APPLICATION_JSON_VALUE)
    ErgastDataDto getAllDrivers();

    @GetMapping(value = "/drivers/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ErgastDataDto getDriverById(@PathVariable("id") String id);

}
