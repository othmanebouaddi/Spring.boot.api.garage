package com.garage.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.garage.entity.Car;
import com.garage.exception.ResourceNotFoundException;
import com.garage.repository.CarRepository;

import javassist.NotFoundException;

@Service
public class CarService {
	
	@Autowired
	private CarRepository carRepository;
	
	@Autowired
	private MechanicService mechanicService;
	
	@Autowired
	private CarService carService;

	public List<Car> getCars() {
		return carRepository.findAll();
	}

	public Optional<Car> getCar(Integer CarId) {
		return carRepository.findById(CarId);
	}
	
	public Car getCar1(Integer CarId) {
		return carRepository.findById(CarId).orElseThrow(() -> new ResourceNotFoundException("Car", "id", CarId));
	}

	public Car createCar(@Valid Car car) {
		return carRepository.save(car);
	}
	
	
	public Car updateCar(Car carUpdate, Integer MechanicId, Integer CarId) throws NotFoundException {
		
		
		return getCar(CarId).map(car -> {
			car.setBuilder(carUpdate.getBuilder());
			car.setModel(carUpdate.getModel());
			return carService.createCar(car);
		}).orElseThrow(() -> new ResourceNotFoundException("Car", "id", CarId));
	}

}
