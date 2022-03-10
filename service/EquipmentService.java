package com.example.equipment.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.equipment.domain.Equipment;
import com.example.equipment.repo.EquipmentRepo;

@Service
public class EquipmentService implements ServiceIF<Equipment> {

	private EquipmentRepo repo;

	@Autowired
	public EquipmentService(EquipmentRepo repo) {
		this.repo = repo;
	}

//create
	public Equipment createEquipment(Equipment e) {

		Equipment created = this.repo.save(e);
		return created;
	}

//read
	public List<Equipment> getAllEquip() {

		return this.repo.findAll();
	}

	public Equipment getEquipment(Integer id) {
		Optional<Equipment> find = this.repo.findById(id);
		return find.get();
	}

	public List<Equipment> getAllEquipByName(String name) {
		List<Equipment> findname = this.repo.findByNameIgnoreCase(name);
		return findname;
	}

	public List<Equipment> getAllEquipByPrice(Integer price) {
		List<Equipment> findprice = this.repo.findByPrice(price);
		return findprice;
	}

//update
	public Equipment replaceEquipment(Integer id, Equipment newEquipment) {
		Equipment exists = this.repo.findById(id).get();
		exists.setColour(newEquipment.getColour());
		exists.setPrice(newEquipment.getPrice());
		Equipment updated = this.repo.save(exists);
		return updated;
	}

//delete
	public void deleteEquipment(@PathVariable Integer id) {

		this.repo.deleteById(id);
	}
}
