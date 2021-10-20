package persistance;

import model.people.Patron;
import model.people.PatronList;
import model.shows.Show;
import model.shows.ShowList;
import model.theatre.Theatre;
import org.junit.jupiter.api.Test;
import persistence.JsonReader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

//This class copies code from JsonSerializationDemo
//url: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
public class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile");
        try {
            Theatre theatre = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderTheatreEmpty() {
        JsonReader reader = new JsonReader("./data/testReaderTheatreEmpty");
        try {
            Theatre theatre = reader.read();
            assertTrue(theatre.getUpcomingShowNames().isEmpty());
            assertTrue(theatre.getPastShowNames().isEmpty());
            assertTrue(theatre.getPatronNames().isEmpty());
            assertEquals("Massey Theatre", theatre.getName());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralTheatre() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralTheatre");
        try {
            Theatre theatre = reader.read();
            assertEquals("Massey Theatre", theatre.getName());

        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralTheatreUpcomingShows() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralTheatre");
        try {
            Theatre theatre = reader.read();
            ArrayList<Show> upcomingShows = theatre.getUpcomingShows();
            assertEquals(1, upcomingShows.size());
            assertTrue(theatre.isUpcomingShow(theatre.getShow("Almost, Maine")));

            ArrayList almostDates = new ArrayList();
            almostDates.add("090825");
            almostDates.add("120221");
            PatronList almostPatrons = new PatronList();
            almostPatrons.addNewPatron(theatre.getPatron("Shirley", "050412"));
            checkShow("Almost, Maine", almostDates, almostPatrons,
                    15, upcomingShows.get(0));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralTheatrePastShows() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralTheatre");
        try {
            Theatre theatre = reader.read();
            ArrayList<Show> pastShows = theatre.getPastShows();
            assertEquals(2, pastShows.size());


            ArrayList cabDates = new ArrayList();
            cabDates.add("090821");
            cabDates.add("120221");
            PatronList cabPatrons = new PatronList();
            cabPatrons.addNewPatron(theatre.getPatron("Bob", "050402"));
            cabPatrons.addNewPatron(theatre.getPatron("Shirley", "050412"));
            checkShow("Cabaret", cabDates, cabPatrons,
                    7, pastShows.get(0));

            ArrayList blahDates = new ArrayList();
            blahDates.add("090830");
            blahDates.add("120219");
            PatronList blahPatrons = new PatronList();
            blahPatrons.addNewPatron(theatre.getPatron("Bob", "050402"));
            checkShow("Blah", blahDates, blahPatrons,
                    4, pastShows.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralTheatrePatrons() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralTheatre");
        try {
            Theatre theatre = reader.read();
            PatronList patrons = theatre.getPatrons();
            assertEquals(2, patrons.getPatronList().size());

            ArrayList<Show> bobsUpcomingShows = new ArrayList<>();
            ArrayList<Show> bobsPastShows = new ArrayList<>();
            bobsPastShows.add(theatre.getShow("Cabaret"));
            bobsPastShows.add(theatre.getShow("Blah"));
            checkPatron("Bob", bobsUpcomingShows, bobsPastShows, "050402",
                    patrons.getPatronList().get(0));

            ArrayList<Show> shirleysUpcomingShows = new ArrayList<>();
            ArrayList<Show> shirleysPastShows = new ArrayList<>();
            shirleysPastShows.add(theatre.getShow("Cabaret"));
            shirleysUpcomingShows.add(theatre.getShow("Almost, Maine"));
            checkPatron("Shirley", shirleysUpcomingShows, shirleysPastShows, "050412",
                    patrons.getPatronList().get(1));

        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}

