package com.example.equipment.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;

import com.example.equipment.domain.Equipment;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Sql(scripts= {"classpath:equipment-schema.sql", "classpath:equipment-data.sql"}, executionPhase=ExecutionPhase.BEFORE_TEST_METHOD)
@ActiveProfiles("test")
public class EquipmentControllerIntegrationTest {
	
	
	@Autowired
	private MockMvc mvc; //this is the class that wil carry out the request
	@Autowired
	private ObjectMapper mapper; //converts from java to JSON
	
	
	@Test
	void testCreate() throws Exception {
		Equipment testEquip = new Equipment(null, "Gloves", "Black", 20);
		String testEquipAsJSON = this.mapper.writeValueAsString(testEquip);
		RequestBuilder request = post("/create").contentType(MediaType.APPLICATION_JSON).content(testEquipAsJSON);
		
		Equipment testCreatedEquip = new Equipment (3, "Gloves", "Black", 20);
		String testCreatedEquipAsJSON = this.mapper.writeValueAsString(testCreatedEquip);
		ResultMatcher checkStatus = status().isCreated();
		ResultMatcher checkBody = content().json(testCreatedEquipAsJSON);
		
		this.mvc.perform(request).andExpect(checkStatus).andExpect(checkBody);
	}

	
	@Test
	void getAllTest() throws Exception {
		RequestBuilder request = get("/getAll");
		List<Equipment> testEquip = List.of(new Equipment(1, "gloves", "blue", 35), new Equipment(2, "gloves", "black", 40));
		String json = this.mapper.writeValueAsString(testEquip);
		ResultMatcher checkStatus = status().isOk();
		ResultMatcher checkBody = content().json(json);
		
		this.mvc.perform(request).andExpect(checkStatus).andExpect(checkBody);
		
	}
	@Test
	void getTest() throws Exception {
		RequestBuilder request = get("/get/1");
		String equipmentAsJSON = this.mapper.writeValueAsString(new Equipment(1, "gloves", "blue", 35));
		ResultMatcher checkStatus = status().isOk();
		ResultMatcher checkBody = content().json(equipmentAsJSON);
		this.mvc.perform(request).andExpect(checkStatus).andExpect(checkBody);
	}
	@Test
	void testUpdate() throws Exception {
		Equipment testEquip = new Equipment(null, "gloves", "blue", 10);
		String testEquipmentAsJSON = this.mapper.writeValueAsString(testEquip);
		RequestBuilder request = put("/update/1").contentType(MediaType.APPLICATION_JSON).content(testEquipmentAsJSON);
		
		Equipment testCreatedEquipment = new Equipment(1, "gloves", "blue", 10);
		String testCreatedEquipmentAsJSON = this.mapper.writeValueAsString(testCreatedEquipment);
		ResultMatcher checkStatus = status().isAccepted();
		ResultMatcher checkBody = content().json(testCreatedEquipmentAsJSON);
		
		this.mvc.perform(request).andExpect(checkStatus).andExpect(checkBody);
	}
	@Test
	void testRemove() throws Exception {
	
		this.mvc.perform(delete("/delete/1")).andExpect(status().isNoContent());
	}

}
