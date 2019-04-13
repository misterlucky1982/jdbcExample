package by.sql.entityes;

public class Contact {
	
	private long id;
	private String name;
	private Note note;
	
	public Contact(String line, Note note){
		this.name = line;
		this.note = note;
	}
	

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Note getNote() {
		return note;
	}

	public void setNote(Note note) {
		this.note = note;
	}
	
	
}
