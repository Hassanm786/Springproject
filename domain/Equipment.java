package com.example.equipment.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Equipment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	//Auto Increments
	private Integer id;
	
	@Column(nullable = false)
	private String name;
	@Column(unique = true)
	private String colour;
	private Integer price;

	public Equipment() {
		super();
	}

	public Equipment(Integer id, String name, String colour, Integer price) {
		super();
		this.id = id;
		this.name = name;
		this.colour = colour;
		this.price = price;

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

	public String getColour() {
		return colour;
	}

	public void setColour(String colour) {
		this.colour = colour;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Equipment [id=" + id + ", name=" + name + ", colour=" + colour + ", price=" + price + "]";
	}

}
