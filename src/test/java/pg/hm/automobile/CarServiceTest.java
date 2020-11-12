package pg.hm.automobile;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import pg.hm.automobile.domain.Car;
import pg.hm.automobile.exception.CarNotFoundException;
import pg.hm.automobile.repository.CarRepository;
import pg.hm.automobile.service.CarService;

@ExtendWith(MockitoExtension.class)
public class CarServiceTest {

    @Mock
    private CarRepository carRepository;

    private CarService carService;

    @BeforeEach
    public void setup() throws Exception {
        carService = new CarService(carRepository);
    }

    @Test
    public void getCarDetails_shouldReturnNull() throws Exception {
        given(carRepository.findByName(anyString())).willThrow(new CarNotFoundException());

        Exception e = assertThrows(CarNotFoundException.class, () -> {
            carService.getCarDetails("accord");
        });

        assertThat(e).isInstanceOf(CarNotFoundException.class);
    }

    @Test
    public void getCarDetails_shouldReturnCar() throws Exception {

        given(carRepository.findByName(anyString())).willReturn(new Car("accord", "hybrid"));

        Car car = carService.getCarDetails("accord");

        assertThat(car.getName()).isEqualTo("accord");
        assertThat(car.getType()).isEqualTo("hybrid");
    }

}
