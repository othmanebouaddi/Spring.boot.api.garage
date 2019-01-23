package com.garage.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.garage.entity.Mechanic;
import com.garage.service.MechanicService;


@RestController
@RequestMapping("/api")
public class MechanicController {

	
	
	@Autowired
	private MechanicService mechanicService;
	
	
	
	@GetMapping(value="/allMechanics")
	public List<Mechanic> getMechanics(){
		return mechanicService.getMechanics();
	}
	
	@PutMapping(value="/updateMechanic/{mechanicId}/{name}")
	public Mechanic updateMechanic(@PathVariable("mechanicId") Integer mechanicId, @PathVariable("name") String name){
		return mechanicService.updateMechanic(mechanicId, name);
	}
	
	
	@PostMapping(value="/createMechanic")
	public Mechanic createMechanic(@RequestBody Mechanic mechanic) {
		return mechanicService.createMechanic(mechanic);
	}
	
	
	@GetMapping(value="/getMechanic/{MechanicId}")
	public Mechanic getMechanic(@PathVariable("MechanicId") Integer MechanicId) {
		return mechanicService.getMechanic(MechanicId);
	}
	
	@DeleteMapping(value="/delete/Mechanic/{MechanicId}")
	public Mechanic deleteMechanic(@PathVariable("MechanicId") Integer MechanicId) {
		return mechanicService.deleteMechanic(MechanicId);
	}
	
	
}
