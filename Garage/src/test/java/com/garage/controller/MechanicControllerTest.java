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
import com.garage.entity.Mechanic;
import com.garage.service.MechanicService;

import utils.Utils;



@SpringBootTest
@WebMvcTest(GarageApplication.class)
public class MechanicControllerTest {
	
	private MockMvc mockMvc;

	@Mock
	private MechanicService mechanicService;

	@InjectMocks
	private MechanicController mechanicController;
	
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(mechanicController).build();
	}
	
	@Test
	public void updateMechanicTest() throws Exception {
		Mechanic mechanic = new Mechanic(1, "Jack");
		when(mechanicService.updateMechanic(mechanic.getId(), mechanic.getName())).thenReturn(mechanic);
		mockMvc.perform(put("/api/updateMechanic/{mechanicId}/{name}", mechanic.getId(), mechanic.getName()).contentType(MediaType.APPLICATION_JSON)
				.content(Utils.asJsonString(mechanic))).andExpect(status().isOk());
	}
	

	


	@Test
	public void getMechanicsTest() throws Exception {
		// given
		List<Mechanic> mechanics = Arrays.asList(new Mechanic(1, "Jack"),
				new Mechanic(2, "Sonia"));

		// when
		when(mechanicService.getMechanics()).thenReturn(mechanics);
		mockMvc.perform(get("/api/allMechanics")).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andExpect(jsonPath("$", hasSize(2)))
				.andExpect(jsonPath("$[0].id", is(1)))
				.andExpect(jsonPath("$[0].name", is("Jack")))
				.andExpect(jsonPath("$[1].id", is(2)))
				.andExpect(jsonPath("$[1].name", is("Sonia")));
		verify(mechanicService, times(1)).getMechanics();
		verifyNoMoreInteractions(mechanicService);
	}
	
	
	
	@Test
	public void createMechanicTest() throws Exception {
		Mechanic mechanic = new Mechanic(1, "Jack");
		when(mechanicService.createMechanic(mechanic)).thenReturn(mechanic);
		mockMvc.perform(
				post("/api/createMechanic").contentType(MediaType.APPLICATION_JSON).content(Utils.asJsonString(mechanic)))
				.andExpect(status().isOk());
	}
	
	@Test
	public void DeleteMechanicTest() throws Exception {
		Mechanic mechanic = new Mechanic(1, "Sonia");
		
		//when
		when(mechanicService.deleteMechanic(mechanic.getId())).thenReturn(mechanic);
		mockMvc.perform(delete("/api/delete/Mechanic/{MechanicId}", mechanic.getId()))
		.andExpect(status().isOk())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
		.andExpect(jsonPath("$.name", is("Sonia")))
		.andExpect(jsonPath("$.id", is(1)));
		verify(mechanicService, times(1)).deleteMechanic(mechanic.getId());
		verifyNoMoreInteractions(mechanicService);
	}
	
	@Test
	public void getMechanicTest() throws Exception {
		Mechanic mechanic = new Mechanic(1, "Sonia");
		when(mechanicService.getMechanic(1)).thenReturn(mechanic);
		mockMvc.perform(get("/api/getMechanic/{MechanicId}", 1)).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andExpect(jsonPath("$.name", is("Sonia")))
				.andExpect(jsonPath("$.id", is(1)));
		verify(mechanicService, times(1)).getMechanic(1);
		verifyNoMoreInteractions(mechanicService);
	}
	
	
	
	
	
}