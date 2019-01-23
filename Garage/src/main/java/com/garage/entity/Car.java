package com.garage.entity;

import java.util.Optional;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name="cars")
public class Car {
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="car_id")
	@ApiModelProperty(notes = "The database generated car ID")
	private Integer id;
	
	@ApiModelProperty(notes = "The car builder")
	@Column(name="builder")
	private String builder;
	
	@ApiModelProperty(notes = "The car model")
	@Column(name="model")
	private String model;
	
	
	
	@ManyToOne
	@JoinColumn(name = "id", referencedColumnName = "id")
	
	@JsonIgnore
	private Mechanic mechanic;


	public Car() {
	}
	
	public Car(Integer id, String builder, String model) {
		super();
		this.id = id;
		this.builder = builder;
		this.model = model;
	}
	
	public Car( String builder, String model) {
		super();
		this.builder = builder;
		this.model = model;
	}
	



	public Integer getId() {
		return id;
	}



	public void setId(Integer id) {
		this.id = id;
	}



	public String getBuilder() {
		return builder;
	}



	public void setBuilder(String builder) {
		this.builder = builder;
	}



	public String getModel() {
		return model;
	}



	public void setModel(String model) {
		this.model = model;
	}



	public Mechanic getMechanic() {
		return mechanic;
	}

	public void setMechanic(Mechanic mechanic) {
		this.mechanic = mechanic;
	}

	
	
	
	
	

}
