package model;

import model.People.Patron;
import model.Shows.Show;
import model.Shows.ShowList;
import model.Theatre.Theatre;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class ShowListTest {
    ShowList showList;
    Show beautyAndTheBeast;
    Show singingInTheRain;
    Show cabaret;

    @BeforeEach



    public void setUp() {
        showList = new ShowList();
        beautyAndTheBeast = new Show();
        singingInTheRain = new Show();
        cabaret = new Show();

        showList.addNewShow(beautyAndTheBeast);
        showList.addNewShow(singingInTheRain);
        showList.addNewShow(cabaret);

    }

    @Test

    public void addShowTest() {
        assertEquals(3, showList.getUpcomingShowsSize());
        assertEquals(0, showList.getPastShowsSize());
        assertTrue(showList.isContainedInUpcoming(beautyAndTheBeast));
        assertTrue(showList.isContainedInUpcoming(singingInTheRain));
    }

    @Test

    public void addShowTwiceTest() {
        showList.addNewShow(beautyAndTheBeast);
        assertEquals(3, showList.getUpcomingShowsSize());
        assertEquals(0, showList.getPastShowsSize());
        assertTrue(showList.isContainedInUpcoming(beautyAndTheBeast));
        assertTrue(showList.isContainedInUpcoming(cabaret));
        assertTrue(showList.isContainedInUpcoming(singingInTheRain));

        assertTrue(showList.getUpcomingShows().contains(beautyAndTheBeast));

    }

    @Test

    public void archiveShowTest() {
        showList.archive(beautyAndTheBeast);

        assertEquals(2, showList.getUpcomingShowsSize());
        assertEquals(1, showList.getPastShowsSize());

        assertFalse(showList.isContainedInUpcoming(beautyAndTheBeast));
        assertTrue(showList.isContainedInUpcoming(singingInTheRain));
        assertTrue(showList.isContainedInPast(beautyAndTheBeast));
        assertFalse(showList.isContainedInPast(singingInTheRain));

        assertTrue(showList.getPastShows().contains(beautyAndTheBeast));

    }

    @Test

    public void onThisDateTest() {
        beautyAndTheBeast.setTitle("Beauty and the Beast");
        singingInTheRain.setTitle("Singing in the Rain");
        cabaret.setTitle("Cabaret");

        ArrayList<Integer> batbdates = new ArrayList();
        batbdates.add(022120);
        batbdates.add(022220);
        batbdates.add(022320);
        beautyAndTheBeast.setDates(batbdates);

        ArrayList<Integer> sitrdates = new ArrayList();
        sitrdates.add(022320);
        sitrdates.add(022420);
        sitrdates.add(022520);
        singingInTheRain.setDates(sitrdates);

        ArrayList<Integer> cabdates = new ArrayList();
        cabdates.add(021020);
        cabdates.add(022020);
        cabdates.add(022120);
        cabaret.setDates(cabdates);

        showList.archive(cabaret);

        assertEquals(2, showList.onThisDate(022320).size());
        assertEquals(2, showList.onThisDate(022120).size());
        assertEquals(1, showList.onThisDate(022420).size());
        assertEquals(1, showList.onThisDate(021020).size());
        assertEquals(0, showList.onThisDate(020520).size());

        assertTrue(showList.onThisDate(022320).contains("Singing in the Rain"));
        assertTrue(showList.onThisDate(022320).contains("Beauty and the Beast"));
        assertTrue(showList.onThisDate(022120).contains("Beauty and the Beast"));
        assertTrue(showList.onThisDate(022120).contains("Cabaret"));
        assertTrue(showList.onThisDate(022420).contains("Singing in the Rain"));
        assertTrue(showList.onThisDate(021020).contains("Cabaret"));
        assertTrue(showList.onThisDate(020520).isEmpty());




    }
}
