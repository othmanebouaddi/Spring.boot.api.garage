package com.garage.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.garage.entity.Mechanic;
import com.garage.exception.ResourceNotFoundException;
import com.garage.repository.MechanicRepository;

@Service
public class MechanicService {
	
	@Autowired
	private MechanicRepository mechanicRepository;

	public List<Mechanic> getMechanics() {
		return mechanicRepository.findAll();
	}

	public Mechanic createMechanic(Mechanic mechanic) {
		return mechanicRepository.save(mechanic);
	}

	public Mechanic getMechanic(Integer mechanicId) {
		return mechanicRepository.findById(mechanicId).orElseThrow(() -> new ResourceNotFoundException("Mechanic", "id", mechanicId));
	}

	public Mechanic updateMechanic(Integer mechanicId, String name) {
		return mechanicRepository.findById(mechanicId).map(mechanic -> {
			mechanic.setName(name);
			return mechanicRepository.save(mechanic);
		}).orElseThrow(() -> new ResourceNotFoundException("Mechanic", "id", mechanicId));
	}

	public Mechanic deleteMechanic(Integer mechanicId) {
		 return mechanicRepository.findById(mechanicId).map(mechanic -> {			
				return mechanicRepository.save(mechanic);
			}).orElseThrow(() -> new ResourceNotFoundException("Mechanic", "id", mechanicId));
			
		}
	
	

	
	
	


}
