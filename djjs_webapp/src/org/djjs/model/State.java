package org.djjs.model;

import java.util.HashSet;
import java.util.Set;

public class State {

	int id;
	String name;
	Set<District> districts;
	
	public void addDistrict(District d) {
		if(districts == null) {
			districts = new HashSet<District>();
		}
		districts.add(d);		
	}
	
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the districts
	 */
	public Set<District> getDistricts() {
		return districts;
	}
	/**
	 * @param districts the districts to set
	 */
	/*public void setDistricts(Set<District> districts) {
		this.districts = districts;
	}*/
	
	
}
