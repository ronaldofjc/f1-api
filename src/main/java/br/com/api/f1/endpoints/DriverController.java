package br.com.api.f1.endpoints;

import br.com.api.f1.models.Driver;
import br.com.api.f1.models.DriversData;
import br.com.api.f1.services.DriverService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@Tag(
    name = "API Fórmula 1",
    description = "Dados de pilotos e temporadas da Fórmula 1"
)
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class DriverController {

    private static final Logger log = LoggerFactory.getLogger(DriverController.class);

    private final DriverService service;

    public DriverController(final DriverService service) {
        this.service = service;
    }

    @GetMapping("/drivers")
    @Operation(summary = "Consulta lista de pilotos")
    public DriversData findAll(@RequestParam(value = "page", required = false, defaultValue = "1") final int page,
                               @RequestParam(value = "page-size", required = false, defaultValue = "30") final int size) {
        log.info("Request - Consulta lista de pilotos. Paginação: [Página: {}, Quantidade: {}]", page, size);
        return service.getAll(page, size);
    }

    @GetMapping("/drivers/{id}")
    @Operation(summary = "Consulta um piloto pelo seu id")
    public Driver findById(@PathVariable(value = "id") final String id) {
        log.info("Request - Consulta um piloto pelo id: {}", id);
        return service.getById(id);
    }

}
