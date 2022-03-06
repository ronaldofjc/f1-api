package br.com.api.f1.component;

import br.com.api.f1.dtos.ErgastDataDto;
import br.com.api.f1.infrastructure.rest.client.ErgastClient;
import br.com.api.f1.models.DriversData;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.Objects;

import static io.restassured.RestAssured.given;
import static org.apache.commons.codec.CharEncoding.UTF_8;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class DriverComponentTest {

    @Mock
    private ErgastClient ergastClient = mock(ErgastClient.class);

    ObjectMapper mapper = new ObjectMapper();

    @BeforeAll
    static void setup() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 8090;
    }

    @Test
    void givenValidRequestWhenCallListAllDriversEndpointThenReturnDriversList() throws IOException {
        // given
        ErgastDataDto dataDtoList = mapper.readValue(loadFile("/client/drivers.json"), ErgastDataDto.class);
        final DriversData driversData = mapper.readValue(loadFile("/client/drivers_response_success.json"), DriversData.class);
        // when
        when(ergastClient.getAllDrivers()).thenReturn(dataDtoList);
        // and execute
        final Response response = given().get("/drivers");
        // then
        assertEquals(driversData, mapper.readValue(response.getBody().asString(), DriversData.class));
        assertEquals(200, response.getStatusCode());
    }

    private String loadFile(final String file) throws IOException {
        return IOUtils.toString(Objects.requireNonNull(FileUtils.class.getResourceAsStream(file)), UTF_8);
    }

}
