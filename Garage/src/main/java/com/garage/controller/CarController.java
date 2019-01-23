package com.garage.controller;

import java.util.List;  
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.garage.entity.Car;
import com.garage.entity.Mechanic;
import com.garage.exception.ResourceNotFoundException;
import com.garage.repository.CarRepository;
import com.garage.service.CarService;
import com.garage.service.MechanicService;

import javassist.NotFoundException;


@RestController
@RequestMapping("/api")
public class CarController {
	
	@Autowired
	private CarService carService;
	
	@Autowired
	private MechanicService mechanicService;
	
	
	@Autowired
	private CarRepository carRepository;
	
	
	@GetMapping(value="/mechanic/{mechanicId}/allCars")
	public List<Car> getCars(@PathVariable("mechanicId") Integer mechanicId) throws NotFoundException{
		
		if(mechanicService.getMechanic(mechanicId) !=  null) {
			throw new NotFoundException("Mechanic not found");
		}
			
		return carRepository.findByMechanicId(mechanicId);
	}
	
	
	
	@PutMapping(value="/mechanic/{MechanicId}/updateCar/{CarId}")
	public Car updateCar(@Valid @RequestBody Car carUpdated, @PathVariable("MechanicId") Integer MechanicId, @PathVariable("CarId") Integer CarId) throws NotFoundException {
		
//		if(mechanicService.getMechanic(MechanicId) != null) {
//			throw new NotFoundException("Mechanic not found");
//		}
//		
//		return carService.getCar(CarId).map(car -> {
//			car.setBuilder(carUpdated.getBuilder());
//			car.setModel(carUpdated.getModel());
//			return carService.createCar(car);
//		}).orElseThrow(() -> new ResourceNotFoundException("Car", "id", CarId));
		return carService.updateCar(carUpdated, MechanicId, CarId);
	}
	
	
	@GetMapping(value="/car/{CarId}")
	public Optional<Car> getCar(@PathVariable("CarId") Integer CarId) throws NotFoundException {
		return carService.getCar(CarId);
	}
	
	
	//For test.
	@GetMapping(value="/car1/{CarId}")
	public Car getCar1(@PathVariable("CarId") Integer CarId) throws NotFoundException {
		return carService.getCar1(CarId);
	}
	
	
	@GetMapping(value="/allCars")
	public List<Car> getCars(){
		return carService.getCars();
	}
	
	
	@PostMapping(value="/mechanic/{mechanic_id}/cars")
    public Car createCar(@PathVariable ("mechanic_id") Integer mechanic_id,
                                 @Valid @RequestBody Car car) {
		Mechanic mechanic = mechanicService.getMechanic(mechanic_id);
		car.setMechanic(mechanic);
		return carService.createCar(car);
       
    }
	
	
	
	

}
