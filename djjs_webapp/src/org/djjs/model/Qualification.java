package org.djjs.model;

import java.io.Serializable;

public class Qualification implements Serializable {

	private static final long serialVersionUID = 1L;
	private int value;
	private String name;
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
