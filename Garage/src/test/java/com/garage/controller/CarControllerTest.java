package com.garage.controller;


import static org.hamcrest.Matchers.hasSize;    
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.springframework.http.MediaType;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.garage.GarageApplication;
import com.garage.entity.Car;
import com.garage.entity.Mechanic;
import com.garage.service.CarService;
import com.garage.service.MechanicService;

import utils.Utils;



@SpringBootTest
@WebMvcTest(GarageApplication.class)
public class CarControllerTest {
	
	private MockMvc mockMvc;

	@Mock
	private CarService carService;
	
	@Mock
	private MechanicService mechanicService;

	@InjectMocks
	private CarController carController;
	
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(carController).build();
	}
	
//	@Test
//	public void updateCarTest() throws Exception {
//		Mechanic mechanic = new Mechanic(1, "Jack");
//		mechanicService.createMechanic(mechanic);
//		Car car = new Car(1, "BMW", "2019");
//		carController.createCar(mechanic.getId(), car);
//		Car car1 = new Car("Mercedes", "2018");		
//		when(carService.updateCar(car1, mechanic.getId(), car.getId()) ).thenReturn(car);
//		mockMvc.perform(put("/api/mechanic/{MechanicId}/updateCar/{CarId}", car1, mechanic.getId(), 1).contentType(MediaType.APPLICATION_JSON)
//				.content(Utils.asJsonString(car))).andExpect(status().isOk());
//	}
	

	


	@Test
	public void getMechanicsTest() throws Exception {
		// given
		List<Car> cars = Arrays.asList(new Car(1, "BMW", "2019"),
				new Car(2,"Mercedes", "2018"));

		// when
		when(carService.getCars()).thenReturn(cars);
		mockMvc.perform(get("/api//allCars")).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andExpect(jsonPath("$", hasSize(2)))
				.andExpect(jsonPath("$[0].id", is(1)))
				.andExpect(jsonPath("$[0].builder", is("BMW")))
				.andExpect(jsonPath("$[0].model", is("2019")))
				.andExpect(jsonPath("$[1].id", is(2)))
				.andExpect(jsonPath("$[1].builder", is("Mercedes")))
				.andExpect(jsonPath("$[1].model", is("2018")));
		verify(carService, times(1)).getCars();
		verifyNoMoreInteractions(carService);
	}
//	
//	
//	
	@Test
	public void createCarTest() throws Exception {
		Car car = new Car(1,"Mercedes", "2018");
		Mechanic mechanic = new Mechanic(1, "Jack");
		mechanicService.createMechanic(mechanic);
		when(carService.createCar(car)).thenReturn(car);
		mockMvc.perform(
				post("/api/mechanic/{mechanic_id}/cars",mechanic.getId(), car  ).contentType(MediaType.APPLICATION_JSON).content(Utils.asJsonString(car)))
				.andExpect(status().isOk());
	}
//	
//	@Test
//	public void DeleteMechanicTest() throws Exception {
//		Mechanic mechanic = new Mechanic(1, "Sonia");
//		
//		//when
//		when(mechanicService.deleteMechanic(mechanic.getId())).thenReturn(mechanic);
//		mockMvc.perform(delete("/api/deleteMechanic/{MechanicId}", mechanic.getId()))
//		.andExpect(status().isOk())
//		.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
//		.andExpect(jsonPath("$.name", is("Sonia")))
//		.andExpect(jsonPath("$.id", is(1)));
//		verify(mechanicService, times(1)).deleteMechanic(mechanic.getId());
//		verifyNoMoreInteractions(mechanicService);
//	}
//	
	
	@Test
	public void getCarTest() throws Exception {
		Car car = new Car(1,"Mercedes", "2018");
		when(carService.getCar1(1)).thenReturn(car);
		mockMvc.perform(get("/api/car1/{CarId}", 1)).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andExpect(jsonPath("$.id", is(1)))
				.andExpect(jsonPath("$.builder", is("Mercedes")))
				.andExpect(jsonPath("$.model", is("2018")));
		verify(carService, times(1)).getCar1(1);
		verifyNoMoreInteractions(carService);
	}
	
	
	
	
	
}