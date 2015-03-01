package org.djjs.model;

import java.util.HashSet;
import java.util.Set;

public class Country {
	
	int id;
	String name;
	Set<State> states;
	
	public void addState(State s) {
		if(null == states) {
			states = new HashSet<State>();
		}
		states.add(s);
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
	 * @return the states
	 */
	public Set<State> getStates() {
		return states;
	}
	/**
	 * @param states the states to set
	 */
	/*public void setStates(Set<State> states) {
		this.states = states;
	}*/
	
	
	
}
