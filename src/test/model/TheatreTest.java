package model;

import model.people.Patron;
import model.shows.Show;
import model.shows.ShowList;
import model.theatre.Theatre;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TheatreTest {

    Theatre massey;
    ShowList showList;
    Show cabaret;
    Show legallyBlonde;
    Patron bob;
    Patron shirley;

    @BeforeEach
    public void setUp() {
        massey = new Theatre();
        showList = new ShowList();
        cabaret = new Show();
        cabaret.setTitle("Cabaret");
        legallyBlonde = new Show();
        legallyBlonde.setTitle("Legally Blonde");
        bob = new Patron();
        shirley = new Patron();
        bob.setName("Bob");
        shirley.setName("Shirley");

    }

    @Test
    public void namingTest() {
        massey.setName("Massey Theatre");
        assertEquals("Massey Theatre", massey.getName());
    }

    @Test
    public void addNewShowTest() {
        massey.addNewShow(cabaret);
        massey.addNewShow(legallyBlonde);
        massey.addNewShow(cabaret);

        assertEquals(3, massey.upcomingShowSize());
        assertTrue(massey.isUpcomingShow(cabaret));
        assertTrue(massey.isUpcomingShow(legallyBlonde));
        assertTrue(massey.getUpcomingShows().contains(legallyBlonde));

    }

    @Test
    public void archiveShowTest() {
        massey.addNewShow(cabaret);
        massey.addNewShow(legallyBlonde);
        massey.addNewShow(cabaret);

        bob.addShow(legallyBlonde);
        legallyBlonde.addPatron(bob);

        shirley.addShow(cabaret);
        cabaret.addPatron(shirley);

        assertTrue(bob.isContainedInMyUpcomingShows(legallyBlonde));

        massey.archiveShow(legallyBlonde);

        assertEquals(2, massey.upcomingShowSize());
        assertEquals(1, massey.pastShowSize());
        assertTrue(massey.isUpcomingShow(cabaret));
        assertFalse(massey.isUpcomingShow(legallyBlonde));
        assertTrue(massey.isPastShow(legallyBlonde));
        assertFalse(bob.isContainedInMyUpcomingShows(legallyBlonde));
        assertTrue(bob.isContainedInMyPastShows(legallyBlonde));
        assertTrue(shirley.isContainedInMyUpcomingShows(cabaret));

    }

    @Test
    public void addNewPatronTest() {
        massey.addNewPatron(bob);
        massey.addNewPatron(shirley);
        massey.addNewPatron(bob);

        assertEquals(3, massey.patronSize());
        assertTrue(massey.isContainsPatron(bob));
        assertTrue(massey.isContainsPatron(shirley));
    }

    @Test
    public void removePatronTest() {
        massey.addNewPatron(bob);
        massey.addNewPatron(shirley);
        massey.addNewPatron(bob);

        massey.removePatron(shirley);

        assertEquals(2, massey.patronSize());
        assertTrue(massey.isContainsPatron(bob));
        assertFalse(massey.isContainsPatron(shirley));
    }

    @Test
    public void myUpcomingShowNamesTest() {

        assertTrue(massey.getUpcomingShowNames().isEmpty());

        massey.addNewShow(cabaret);
        massey.addNewShow(legallyBlonde);

        assertTrue(massey.getUpcomingShowNames().contains("Cabaret"));
        assertTrue(massey.getUpcomingShowNames().contains("Legally Blonde"));


    }

    @Test
    public void myPastShowNamesTest() {

        assertTrue(massey.getPastShowNames().isEmpty());

        massey.addNewShow(cabaret);
        massey.addNewShow(legallyBlonde);

        assertTrue(massey.getPastShowNames().isEmpty());

        massey.archiveShow(cabaret);
        massey.archiveShow(legallyBlonde);

        assertTrue(massey.getPastShowNames().contains("Cabaret"));
        assertTrue(massey.getPastShowNames().contains("Legally Blonde"));


    }

    @Test
    public void getPatronTestTrue() {

        massey.addNewPatron(bob);
        massey.addNewPatron(shirley);

        bob.setBirthday(050402);
        bob.setName("Bob");

        shirley.setBirthday(050402);
        shirley.setName("Shirley");

        assertEquals(bob, massey.getPatron("Bob", 050402));
        assertTrue(massey.isGetTheatrePatron("Bob", 050402));
        assertFalse(massey.isGetTheatrePatron("Shirley", 050403));
    }

    @Test
    public void getPatronTestTrue2() {

        massey.addNewPatron(bob);
        massey.addNewPatron(shirley);

        bob.setBirthday(050401);
        bob.setName("Shirley");

        shirley.setBirthday(050402);
        shirley.setName("Shirley");

        assertEquals(bob, massey.getPatron("Shirley", 050401));
    }

    @Test
    public void getPatronTestTrueBoth() {

        massey.addNewPatron(bob);
        massey.addNewPatron(shirley);

        bob.setBirthday(050401);
        bob.setName("Shirley");

        shirley.setBirthday(050401);
        shirley.setName("Shirley");

        assertEquals(shirley, massey.getPatron("Shirley", 050401));

    }

    @Test
    public void getPatronTestTrueNeither1() {

        massey.addNewPatron(bob);
        massey.addNewPatron(shirley);

        bob.setBirthday(050401);
        bob.setName("Bob");

        shirley.setBirthday(050402);
        shirley.setName("Shirley");

        assertEquals(null, massey.getPatron("Shirley", 050405));

    }

    @Test
    public void getPatronTestTrueNeither2() {

        massey.addNewPatron(bob);
        massey.addNewPatron(shirley);

        bob.setBirthday(050401);
        bob.setName("Bob");

        shirley.setBirthday(050402);
        shirley.setName("Shirley");

        assertNull(massey.getPatron("Cat", 050401));

    }

    @Test
    public void getPatronTestTrueNeither3() {

        massey.addNewPatron(bob);
        massey.addNewPatron(shirley);

        bob.setBirthday(050401);
        bob.setName("Bob");

        shirley.setBirthday(050402);
        shirley.setName("Shirley");

        assertNull(massey.getPatron("Cat", 050454));

    }

    @Test
    public void getShowTest() {

        assertNull(massey.getShow("Cabaret"));

        massey.addNewShow(cabaret);
        massey.addNewShow(legallyBlonde);

        assertEquals(legallyBlonde, massey.getShow("Legally Blonde"));
        assertEquals(cabaret, massey.getShow("Cabaret"));

        assertTrue(massey.isGetTheatreShow("Legally Blonde"));
        assertFalse(massey.isGetTheatreShow("Bob the Builder"));
    }

    @Test
    public void getPatronNamesTest() {

        assertTrue(massey.getPatronNames().isEmpty());

        massey.addNewPatron(bob);
        massey.addNewPatron(shirley);

        assertEquals(2, massey.getPatronNames().size());
        assertTrue(massey.getPatronNames().contains("Bob"));
        assertTrue(massey.getPatronNames().contains("Shirley"));
    }

    @Test

    public void showsOnThisDateTest() {

        massey.addNewShow(legallyBlonde);
        massey.addNewShow(cabaret);


        legallyBlonde.addDate("022120");
        legallyBlonde.addDate("022220");
        legallyBlonde.addDate("022320");

        cabaret.addDate("021020");
        cabaret.addDate("022020");
        cabaret.addDate("022120");

        cabaret.addDate("022320");
        cabaret.removeDate("022320");

        showList.archive(cabaret);

        assertEquals(1, massey.showsOnThisDate("022320").size());
        assertEquals(2, massey.showsOnThisDate("022120").size());
        assertEquals(0, massey.showsOnThisDate("022420").size());
        assertEquals(1, massey.showsOnThisDate("021020").size());
        assertEquals(0, massey.showsOnThisDate("020520").size());

        assertTrue(massey.showsOnThisDate("022320").contains("Legally Blonde"));
        assertTrue(massey.showsOnThisDate("022120").contains("Legally Blonde"));
        assertTrue(massey.showsOnThisDate("022120").contains("Cabaret"));
        assertTrue(massey.showsOnThisDate("021020").contains("Cabaret"));
        assertTrue(massey.showsOnThisDate("020520").isEmpty());




    }
}
