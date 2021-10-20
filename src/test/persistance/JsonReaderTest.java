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

            ArrayList almostDates = new ArrayList();
            almostDates.add("090825");
            almostDates.add("120221");
            checkShow("Almost, Maine", almostDates, upcomingShows.get(0).getPatrons(),
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
            checkShow("Cabaret", cabDates, pastShows.get(0).getPatrons(),
                    7, pastShows.get(0));

            ArrayList blahDates = new ArrayList();
            blahDates.add("090830");
            blahDates.add("120219");
            checkShow("Blah", blahDates, pastShows.get(1).getPatrons(),
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

            checkPatron("Bob", patrons.getPatronList().get(0).getMyShows(), 050402,
                    patrons.getPatronList().get(0));

            checkPatron("Shirley", patrons.getPatronList().get(1).getMyShows(), 050412,
                    patrons.getPatronList().get(1));

        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}

