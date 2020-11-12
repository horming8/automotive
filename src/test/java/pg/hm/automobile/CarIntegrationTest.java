package pg.hm.automobile;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import pg.hm.automobile.domain.Car;
import pg.hm.automobile.service.CarService;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class CarIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @MockBean
    private CarService carService;

    @Test
    public void getCar() throws Exception {

        when(carService.getCarDetails(anyString())).thenReturn(new Car("accord", "hybrid"));

        ResponseEntity<Car> response = restTemplate.getForEntity("/cars/accord", Car.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody().getName()).isEqualTo("accord");
        assertThat(response.getBody().getType()).isEqualTo("hybrid");
    }
}
