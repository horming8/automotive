package pg.hm.automobile.repository;

import org.springframework.data.repository.CrudRepository;

import pg.hm.automobile.domain.Car;

public interface CarRepository extends CrudRepository<Car, Long> {

    Car findByName(String name);
    
}
