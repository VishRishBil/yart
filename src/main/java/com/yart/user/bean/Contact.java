package com.yart.user.bean;

public class Contact {

	public enum ContactType {
		EMAIL("email"), PHONE("phone"), MOBILE("mobile"), ADDRESS("address"),SKYPE("im:skype"),UNDEFINED("");

		private String name;
		
		ContactType(String name){
			this.name =name;
		}
		
		public String getName(){
			return this.name;
		}
		
		public static ContactType getContactTypeByName(String name){
			for(ContactType c : ContactType.values()){
				if(c.getName().equals(name)){
					return c;
				}
			}
			return UNDEFINED;
		}
		
	}

	private int id;

	private ContactType type;

	private String value;

	public Contact(int id, String type,
			String value) {
		super();
		this.id = id;
		this.type = ContactType.getContactTypeByName(type);
		this.value = value;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public ContactType getType() {
		return type;
	}

	public void setType(ContactType type) {
		this.type = type;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	

	
}
