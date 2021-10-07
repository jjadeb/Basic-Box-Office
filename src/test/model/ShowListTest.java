package model;


import model.Shows.Show;
import model.Shows.ShowList;
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
        cabaret.setTitle("Cabaret");
        beautyAndTheBeast.setTitle("Beauty and the Beast");

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
}
