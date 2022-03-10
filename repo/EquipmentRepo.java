package com.example.equipment.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.equipment.domain.Equipment;

@Repository
public interface EquipmentRepo extends JpaRepository<Equipment, Integer> {
	
	//spring auto-generated the basic CRUD functionalities
	
	List<Equipment> findByNameIgnoreCase(String name);
	
	List<Equipment> findByPrice(Integer price);
	
	}


