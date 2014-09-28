package com.fursel.persistence.json;

import org.codehaus.jackson.annotate.JsonProperty;


public class CategoryJson {
	
	private Long id;

	private Long parent;

	@JsonProperty("text")
	private String name;

	public String getId() {
		return id.toString();
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getParent() {
		if(parent==null)
			return "#";
		return parent.toString();
	}

	public void setParent(Long parent) {
		this.parent = parent;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
