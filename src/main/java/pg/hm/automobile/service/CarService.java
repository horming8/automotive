package pg.hm.automobile.service;

import org.springframework.stereotype.Service;

import pg.hm.automobile.domain.Car;
import pg.hm.automobile.exception.CarNotFoundException;
import pg.hm.automobile.repository.CarRepository;

@Service
public class CarService {

    private CarRepository carRepository;

	public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
	}

	public Car getCarDetails(String name) {
        Car result = carRepository.findByName(name);
        if (result == null) {
            throw new CarNotFoundException();
        }
        return result;
	}

}
