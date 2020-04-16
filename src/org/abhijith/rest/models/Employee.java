package org.abhijith.rest.models;


import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Employee {

	public Employee() {
		// TODO Auto-generated constructor stub
	}

	@JsonProperty
	private String name;

	@JsonProperty
	private String type;
	
	@JsonProperty
	private List<String> departments;

	public Employee(String name, String type, List<String> departments) {
		super();
		this.name = name;
		this.type = type;
		this.departments = departments;
	}

	static public enum EmployeeType {
		PERMANENT("permanent"), CONTRACT("contract");
		
		private String name;
		private EmployeeType(String name) {
			this.name = name;
		}
	}
}
