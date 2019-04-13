package by.sql.db_lesson;

import java.util.Iterator;
import java.util.List;

import by.sql.entityes.Contact;
import by.sql.entityes.Note;
import by.sql.sqlconnector.JDBCConnector;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        JDBCConnector jdbc = new JDBCConnector();
        List<Note>list = jdbc.getNotesList();
        Iterator<Note> it = list.iterator();
        
        while(it.hasNext())System.out.println(it.next().getName());
        jdbc.close();
    }
}
