package pg.hm.automobile;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import pg.hm.automobile.domain.Car;
import pg.hm.automobile.repository.CarRepository;

@DataJpaTest
public class CarRepositoryTest {

    @Autowired
    private CarRepository repository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void findByName_shouldReturnNull() throws Exception {
        Car car = repository.findByName("accord");

        assertThat(car).isNull();
    }

    @Test
    public void findByName_shouldReturnCar() throws Exception {
        Car persistedCar = entityManager.persistFlushFind(new Car("accord", "hybrid"));
        Car car = repository.findByName("accord");

        assertThat(car.getName()).isEqualTo(persistedCar.getName());
        assertThat(car.getType()).isEqualTo(persistedCar.getType());
    }
}
