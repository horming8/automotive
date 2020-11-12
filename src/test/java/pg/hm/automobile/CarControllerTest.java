package pg.hm.automobile;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import pg.hm.automobile.controller.CarController;
import pg.hm.automobile.domain.Car;
import pg.hm.automobile.exception.CarNotFoundException;
import pg.hm.automobile.service.CarService;

@WebMvcTest(CarController.class)
public class CarControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CarService carService;

    @Test
    public void getCar_shouldReturnNotFound() throws Exception {

        given(carService.getCarDetails(anyString())).willThrow(new CarNotFoundException());

        mockMvc.perform(get("/cars/accord"))
            .andDo(print())
            .andExpect(status().isNotFound());
    }

    @Test
    public void getCar_shouldReturnCar() throws Exception {

        given(carService.getCarDetails(anyString())).willReturn(new Car("accord", "hybrid"));
        
        mockMvc.perform(get("/cars/accord"))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.name").value("accord"))
            .andExpect(jsonPath("$.type").value("hybrid"));
    }

}
