package com.garage.repository;


import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.garage.entity.Car;
import com.garage.entity.Mechanic;

@Repository
@Transactional
public interface MechanicRepository extends JpaRepository<Mechanic, Integer>{
	
	
	public Optional<Mechanic> findById(Integer MechanicId);

}
