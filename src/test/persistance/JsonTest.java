package persistance;



import model.people.Patron;
import model.people.PatronList;
import model.shows.Show;
import model.shows.ShowList;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

//This class copies code from JsonSerializationDemo
//url: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
public class JsonTest {
    protected void checkShow(String name, ArrayList<String> dates, PatronList patrons,
                             double ticketPrice, Show show) {
        assertEquals(name, show.getTitle());
        assertEquals(dates, show.getDates());
        assertEquals(patrons, show.getPatrons());
        assertEquals(ticketPrice, show.getTicketPrice());
    }

    protected void checkPatron(String name, ShowList shows, double birthday, Patron patron) {
        assertEquals(name, patron.getName());
        assertEquals(birthday, patron.getBirthday());
        assertEquals(shows, patron.getMyShows());
    }
}
