package com.yart.bean;

public class Project {
	
	private int id;
	
	private String name;
	
	private LocalString description;
	
	private User owner;

	public Project(int id, String name, LocalString description, User owner) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.owner = owner;
	}
	
	public Project(int id, String name, String description, User owner) {
		super();
		this.id = id;
		this.name = name;
		this.description = new LocalString(description);
		this.owner = owner;
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

	public LocalString getDescription() {
		return description;
	}

	public void setDescription(LocalString description) {
		this.description = description;
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}
	
	
	
	

}
