package com.garage.repository;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.garage.entity.Car;


@Repository
@Transactional
public interface CarRepository extends JpaRepository<Car, Integer>{
	
	public Optional<Car> findById(Integer CarId);
	public List<Car> findByMechanicId(Integer MechanicId);
	

}
