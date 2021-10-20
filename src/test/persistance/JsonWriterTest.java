package persistance;

import model.people.Patron;
import model.people.PatronList;
import model.shows.Show;
import model.theatre.Theatre;
import org.junit.jupiter.api.Test;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

//This class copies code from JsonSerializationDemo
//url: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
public class JsonWriterTest extends JsonTest{
    @Test
    void testWriterInvalidFile() {
        try {
            Theatre theatre = new Theatre();
            JsonWriter writer = new JsonWriter("./data/my/illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyWorkroom() {
        try {
            Theatre theatre = new Theatre();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyTheatre.json");
            writer.open();
            writer.write(theatre);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyTheatre.json");
            theatre = reader.read();
            assertEquals("", theatre.getName());
            assertEquals(0, theatre.upcomingShowSize());
            assertEquals(0, theatre.pastShowSize());
            assertEquals(0, theatre.getPatrons().getSize());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralWorkroom() {
        try {
            //Seting up data
            Theatre theatre = new Theatre();
            theatre.setName("Massey");

            Show hop = new Show();
            theatre.addNewShow(hop);
            hop.setTitle("Hop");
            hop.setTicketPrice(7);
            hop.addDate("120221");
            hop.addDate("090820");

            Show hay = new Show();
            theatre.addNewShow(hay);
            hay.setTitle("Hay");
            hay.setTicketPrice(4);
            hay.addDate("120221");
            hay.addDate("090825");

            Show lof = new Show();
            theatre.addNewShow(lof);
            lof.setTitle("Lof");
            lof.setTicketPrice(4);
            lof.addDate("090830");
            lof.addDate("120219");

            Patron hannah = new Patron();
            theatre.addNewPatron(hannah);
            hannah.setName("Hannah");
            hannah.setBirthday("050403");
            hannah.addShow(hop);
            hannah.addShow(lof);
            hop.addPatron(hannah);
            lof.addPatron(hannah);

            Patron shirley = new Patron();
            theatre.addNewPatron(shirley);
            shirley.setName("Shirley");
            shirley.setBirthday("050412");
            shirley.addShow(hop);
            shirley.addShow(hay);
            hop.addPatron(shirley);
            hay.addPatron(shirley);

            theatre.archiveShow(hop);
            theatre.archiveShow(lof);

            //Writing data
            JsonWriter writer = new JsonWriter("./data/testWriterTheatreGeneral.json");
            writer.open();
            writer.write(theatre);
            writer.close();

            //Reading data
            JsonReader reader = new JsonReader("./data/testWriterTheatreGeneral.json");
            theatre = reader.read();

            //Testing Name
            assertEquals("Massey", theatre.getName());

            //Testing Patrons
            PatronList patrons = theatre.getPatrons();
            assertEquals(2, patrons.getPatronList().size());

            ArrayList<Show> hannahsUpcomingShows = new ArrayList<>();
            ArrayList<Show> hannahsPastShows = new ArrayList<>();
            hannahsPastShows.add(theatre.getShow("Hop"));
            hannahsPastShows.add(theatre.getShow("Lof"));
            checkPatron("Hannah", hannahsUpcomingShows, hannahsPastShows, "050403",
                    patrons.getPatronList().get(0));

            ArrayList<Show> shirleysUpcomingShows = new ArrayList<>();
            ArrayList<Show> shirleysPastShows = new ArrayList<>();
            shirleysPastShows.add(theatre.getShow("Hop"));
            shirleysUpcomingShows.add(theatre.getShow("Hay"));
            checkPatron("Shirley", shirleysUpcomingShows, shirleysPastShows, "050412",
                    patrons.getPatronList().get(1));

            //Testing Past Shows
            ArrayList<Show> pastShows = theatre.getPastShows();
            assertEquals(2, pastShows.size());


            ArrayList hopDates = new ArrayList();
            hopDates.add("120221");
            hopDates.add("090820");
            PatronList hopPatrons = new PatronList();
            hopPatrons.addNewPatron(theatre.getPatron("Hannah", "050403"));
            hopPatrons.addNewPatron(theatre.getPatron("Shirley", "050412"));
            checkShow("Hop", hopDates, hopPatrons,
                    7, pastShows.get(0));

            ArrayList lofDates = new ArrayList();
            lofDates.add("090830");
            lofDates.add("120219");
            PatronList lofPatrons = new PatronList();
            lofPatrons.addNewPatron(theatre.getPatron("Hannah", "050403"));
            checkShow("Lof", lofDates, lofPatrons,
                    4, pastShows.get(1));

            //Testing Upcoming Shows
            ArrayList<Show> upcomingShows = theatre.getUpcomingShows();
            assertEquals(1, upcomingShows.size());
            assertTrue(theatre.isUpcomingShow(theatre.getShow("Hay")));

            ArrayList hayDates = new ArrayList();
            hayDates.add("120221");
            hayDates.add("090825");
            PatronList hayPatrons = new PatronList();
            hayPatrons.addNewPatron(theatre.getPatron("Shirley", "050412"));
            checkShow("Hay", hayDates, hayPatrons,
                    4, upcomingShows.get(0));



        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
