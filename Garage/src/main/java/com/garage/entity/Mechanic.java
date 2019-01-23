package com.garage.entity;

import java.util.Set; 

import javax.persistence.*;

import io.swagger.annotations.ApiModelProperty;


@Entity
@Table(name="mechanic")
public class Mechanic {
	
	
	@Id
	@Column(name="id")
	@ApiModelProperty(notes = "The mechanic id")
	private Integer id;
	
	@Column(name="name")
	@ApiModelProperty(notes = "The mechanic name")
	private String name;
	
	
	
	@OneToMany( fetch = FetchType.LAZY, mappedBy = "mechanic", cascade = CascadeType.ALL)
    private Set<Car> cars;

	public Mechanic() {
	}

	public Mechanic(Integer id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Car> getCar() {
		return cars;
	}

	public void setCar(Set<Car> cars) {
		this.cars = cars;
	}

	
	
	
	
	
	
	
	
	

}
