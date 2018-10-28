package de.l3s.elasticquery;

public class Entity {
	private String id;
	private String attributes;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getAttributes() {
		return attributes;
	}
	public void setAttributes(String attributes) {
		this.attributes = attributes;
	}
	public Entity(String id, String attributes) {
		super();
		this.id = id;
		this.attributes = attributes;
	}
}
