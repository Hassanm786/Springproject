package com.example.equipment.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.equipment.domain.Equipment;
import com.example.equipment.service.EquipmentService;

@CrossOrigin
@RestController // tells spring that this is a controller.
public class EquipmentController {

	private EquipmentService service;

	@Autowired // tells spring to fetch the Equipment service from the context
	public EquipmentController(EquipmentService service) {

		super();
		this.service = service;
	}

	@PostMapping("/create")
	public ResponseEntity<Equipment> createEquipment(@RequestBody Equipment e) {
		Equipment created = this.service.createEquipment(e);
		ResponseEntity<Equipment> response = new ResponseEntity<Equipment>(created, HttpStatus.CREATED);
		return response;
	}
	

	@GetMapping("/getAll")

	public ResponseEntity<List<Equipment>> getAllequip() {
		return ResponseEntity.ok(this.service.getAllEquip());
	}

	@GetMapping("/get/{id}")

	public Equipment getEquipment(@PathVariable Integer id) {
		return this.service.getEquipment(id);
	}
	
//	@GetMapping
	
//	//public ResponseEntity<List<Equipment>> getEquipmentByName(@PathVariable String name) {
//		List<Equipment> findname = this.service.getAllEquipByName(name);
//		return ResponseEntity.ok(findname);
//		
//	}
//	
//	@GetMapping
//	
//	public ResponseEntity<List<Equipment>> getEquipmentByPrice(@PathVariable Integer price) {
//		List<Equipment> findprice = this.service.getAllEquipByPrice(price);
//		return ResponseEntity.ok(findprice);
//	

	@PutMapping("/update/{id}")

	public ResponseEntity<Equipment> replaceEquipment(@PathVariable Integer id, @RequestBody Equipment newEquipment) {
		Equipment body = this.service.replaceEquipment(id, newEquipment);
		ResponseEntity<Equipment> response = new ResponseEntity<Equipment>(body, HttpStatus.ACCEPTED);
		return response;
	}

	@DeleteMapping("/delete/{id}")

	public ResponseEntity<?> deleteEquipment(@PathVariable Integer id) {
		this.service.deleteEquipment(id);

		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	

}
