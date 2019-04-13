package by.sql.sqlconnector;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import by.sql.entityes.Contact;
import by.sql.entityes.Note;

public class JDBCConnector {

	private static final String DRIVER = "org.postgresql.Driver";
	private static final String URL = "jdbc:postgresql://localhost/test";
	private static final String LOGIN = "postgres";
	private static final String PASSWORD = "gfhjkm";
	private Connection connection;
	{
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		try {
			connection = (Connection) DriverManager.getConnection(URL, LOGIN, PASSWORD);
		} catch (SQLException e) {
			System.out.println("Ooops... I`m sorry... I can`t find out this database...");
			e.printStackTrace();
		}
	}

	public boolean addNote(Note note) {
		PreparedStatement statement = null;
		try {
			String insert = "insert into notes (name) values ('" + note.getName() + "');";
			statement = this.connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
			int rows = statement.executeUpdate();
			if (rows == 0) {
				throw new SQLException("Creating note failed, no rows affected.");
			}
			try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
				if (generatedKeys.next()) {
					note.setId(generatedKeys.getLong(1));
				} else {
					throw new SQLException("Creating note failed, no ID obtained.");
				}
			}
			statement.close();
		} catch (SQLException e) {
			return false;
		}
		return true;
	}

	public boolean addContact(Contact contact) {
		if (contact.getNote() == null || contact.getNote().getId() == 0)
			return false;
		PreparedStatement statement = null;
		try {
			String insert = "insert into contacts (contact, note_id) values ('" + contact.getName() + "',"
					+ contact.getNote().getId() + ");";
			statement = this.connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
			int rows = statement.executeUpdate();
			if (rows == 0) {
				throw new SQLException("Creating contact failed, no rows affected.");
			}
			try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
				if (generatedKeys.next()) {
					contact.setId(generatedKeys.getLong(1));
				} else {
					throw new SQLException("Creating contact failed, no ID obtained.");
				}
			}
			statement.close();
		} catch (SQLException e) {
			return false;
		}
		return true;
	}

	public List<Note> getNotesList() {
		List<Note> result = new ArrayList<>();
		Statement statement = null;
		try {
			statement = connection.createStatement();
		} catch (SQLException e) {
			return null;
		}
		ResultSet rs;
		try {
			rs = statement.executeQuery("select * from notes;");
			while (rs.next()) {
				String name = rs.getString("name");
				long id = rs.getLong("id");
				result.add(new Note(id,name));
			}
		} catch (SQLException e) {
		};
		return result;
	}

	public void close() {
		try {
			if (this.connection != null)
				this.connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
