package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
* @author Kevin Roney - karoney
* CIS 175 - Fall 2023
* Jan 30, 2023
*/
@Entity
@Table(name="pets")
public class Pet {
	@Id
	@GeneratedValue
	@Column(name="ID")
	private int id;
	@Column(name="NAME")
	private String name;
	@Column(name="SPECIES")
	private String species;
	@Column(name="AGE")
	private int age;

	public Pet() {
		
	}
	public Pet(String name, String species, int age) {
		this.name = name;
		this.species = species;
		this.age = age;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSpecies() {
		return species;
	}
	public void setSpecies(String species) {
		this.species = species;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
	public String returnPetInfo() {
		return "Name: " + this.name + "  |  Species: " + this.species + "  |  Age: " + this.age;
	}
}
