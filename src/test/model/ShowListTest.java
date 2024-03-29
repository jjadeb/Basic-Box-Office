package model;


import model.shows.Show;
import model.shows.ShowList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
        cabaret.setTitle("Cabaret");
        beautyAndTheBeast.setTitle("Beauty and the Beast");

        showList.addNewShow(beautyAndTheBeast);
        showList.addNewShow(singingInTheRain);
        showList.addNewShow(cabaret);

    }

    @Test

    public void removeShowTest() {
        assertEquals(3, showList.getUpcomingShowsSize());
        assertEquals(0, showList.getPastShowsSize());
        assertTrue(showList.isContainedInUpcoming(beautyAndTheBeast));
        assertTrue(showList.isContainedInUpcoming(singingInTheRain));

        showList.removeShow(beautyAndTheBeast);
        showList.archive(cabaret);
        showList.archive(singingInTheRain);
        showList.removeShow(singingInTheRain);

        Show blab = new Show();
        showList.removeShow(blab);

        assertEquals(0, showList.getUpcomingShowsSize());
        assertEquals(1, showList.getPastShowsSize());
        assertFalse(showList.isContainedInUpcoming(beautyAndTheBeast));
        assertFalse(showList.isContainedInUpcoming(singingInTheRain));

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
        assertEquals(4, showList.getUpcomingShowsSize());
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


        beautyAndTheBeast.addDate("022120");
        beautyAndTheBeast.addDate("022220");
        beautyAndTheBeast.addDate("022320");


        singingInTheRain.addDate("022320");
        singingInTheRain.addDate("022420");
        singingInTheRain.addDate("022520");


        cabaret.addDate("021020");
        cabaret.addDate("022020");
        cabaret.addDate("022120");
        cabaret.addDate("022320");
        cabaret.removeDate("022320");

        showList.archive(cabaret);

        assertEquals(2, showList.onThisDate("022320").size());
        assertEquals(2, showList.onThisDate("022120").size());
        assertEquals(1, showList.onThisDate("022420").size());
        assertEquals(1, showList.onThisDate("021020").size());
        assertEquals(0, showList.onThisDate("020520").size());

        assertTrue(showList.onThisDate("022320").contains("Singing in the Rain"));
        assertTrue(showList.onThisDate("022320").contains("Beauty and the Beast"));
        assertTrue(showList.onThisDate("022120").contains("Beauty and the Beast"));
        assertTrue(showList.onThisDate("022120").contains("Cabaret"));
        assertTrue(showList.onThisDate("022420").contains("Singing in the Rain"));
        assertTrue(showList.onThisDate("021020").contains("Cabaret"));
        assertTrue(showList.onThisDate("020520").isEmpty());




    }


    @Test
    public void myUpcomingShowNamesTest() {

        assertTrue(showList.getUpcomingShowNames().contains("Cabaret"));
        assertTrue(showList.getUpcomingShowNames().contains("Beauty and the Beast"));

        showList.archive(beautyAndTheBeast);
        showList.archive(cabaret);
        showList.archive(singingInTheRain);

        assertTrue(showList.getUpcomingShowNames().isEmpty());
    }

    @Test
    public void myPastShowNamesTest() {

        assertTrue(showList.getPastShowNames().isEmpty());

        showList.archive(beautyAndTheBeast);
        showList.archive(cabaret);
        showList.archive(singingInTheRain);

        assertTrue(showList.getPastShowNames().contains("Cabaret"));
        assertTrue(showList.getPastShowNames().contains("Beauty and the Beast"));

    }


    @Test
    public void getShowTest() {

        assertEquals(beautyAndTheBeast, showList.getShow("Beauty and the Beast"));
        assertEquals(cabaret, showList.getShow("Cabaret"));

        showList.archive(beautyAndTheBeast);

        assertEquals(beautyAndTheBeast, showList.getShow("Beauty and the Beast"));

        assertTrue(showList.isGetShow("Beauty and the Beast"));
        assertFalse(showList.isGetShow("Almost, Maine"));

    }
}
