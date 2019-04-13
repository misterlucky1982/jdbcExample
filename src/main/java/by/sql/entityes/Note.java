package by.sql.entityes;

import java.util.ArrayList;
import java.util.List;

public class Note {
	
	private long id;
	private String name;
	private List<Note>contacts;
	
	public Note(String name){
		this.name = name;
		this.contacts = new ArrayList<>();
	}
	
	public Note(long id, String name){
		this.name=name;
		this.id=id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Note> getContacts() {
		return contacts;
	}

	public void setContacts(List<Note> contacts) {
		this.contacts = contacts;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	
}
