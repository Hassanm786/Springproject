package com.example.equipment.service;

import java.util.List;

public interface ServiceIF<T> {
	
	T createEquipment(T t);
	
	List<T> getAllEquip();
	
	T getEquipment(Integer id);
	
	T replaceEquipment(Integer id, T t);
	
	void deleteEquipment(Integer id);

}
