package com.pengenkerjadirumah.persistence.json;


public class CategoryJson {
	
	private Long id;

	private Long parent;

	private String text;

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

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
}
