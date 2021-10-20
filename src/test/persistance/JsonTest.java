package persistance;



import model.people.Patron;
import model.people.PatronList;
import model.shows.Show;
import model.shows.ShowList;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

//This class copies code from JsonSerializationDemo
//url: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
public class JsonTest {
    protected void checkShow(String name, ArrayList<String> dates, PatronList patrons,
                             double ticketPrice, Show show) {
        assertEquals(name, show.getTitle());
        assertEquals(dates, show.getDates());
        assertTrue(patrons.getPatronList().containsAll(show.getPatrons().getPatronList()));
        assertEquals(ticketPrice, show.getTicketPrice());
    }

    protected void checkPatron(String name, ArrayList<Show> upcomingShows, ArrayList<Show> pastShows, String birthday,
                               Patron patron) {
        assertEquals(name, patron.getName());
        assertEquals(birthday, patron.getBirthday());
        assertTrue(upcomingShows.containsAll(patron.getMyShows().getUpcomingShows()));
        assertTrue(pastShows.containsAll(patron.getMyShows().getPastShows()));
    }
}
