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

        massey.archiveShow(legallyBlonde);

        assertEquals(2, massey.upcomingShowSize());
        assertEquals(1, massey.pastShowSize());
        assertTrue(massey.isUpcomingShow(cabaret));
        assertFalse(massey.isUpcomingShow(legallyBlonde));
        assertTrue(massey.isPastShow(legallyBlonde));

    }

    @Test
    public void addNewPatronTest() {
        massey.addNewPatron(bob);
        massey.addNewPatron(shirley);
        massey.addNewPatron(bob);

        assertEquals(2, massey.patronSize());
        assertTrue(massey.isContainsPatron(bob));
        assertTrue(massey.isContainsPatron(shirley));
    }

    @Test
    public void removePatronTest() {
        massey.addNewPatron(bob);
        massey.addNewPatron(shirley);
        massey.addNewPatron(bob);

        massey.removePatron(shirley);

        assertEquals(1, massey.patronSize());
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
    public void getPatronTestTrue() {

        massey.addNewPatron(bob);
        massey.addNewPatron(shirley);

        bob.setBirthday(050402);
        bob.setName("Bob");

        shirley.setBirthday(050402);
        shirley.setName("Shirley");

        assertEquals(bob, massey.getPatron("Bob", 050402));
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
    public void getPatronTestTrueNeither() {

        massey.addNewPatron(bob);
        massey.addNewPatron(shirley);

        bob.setBirthday(050401);
        bob.setName("Bob");

        shirley.setBirthday(050402);
        shirley.setName("Shirley");

        assertEquals(null, massey.getPatron("Shirley", 050405));

    }

    @Test
    public void getShowTest() {
        massey.addNewShow(cabaret);
        massey.addNewShow(legallyBlonde);

        assertEquals(legallyBlonde, massey.getShow("Legally Blonde"));
        assertEquals(cabaret, massey.getShow("Cabaret"));
    }
}
