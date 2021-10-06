package model;

import model.People.Patron;
import model.Shows.Show;
import model.Theatre.Theatre;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PatronTest {

    Patron bob;
    Patron shirley;
    Show cabaret;
    Show crazyForYou;
    Theatre Massey;

    @BeforeEach

    public void setUp() {
        bob = new Patron();
        cabaret = new Show();
        crazyForYou = new Show();
    }

    @Test

    public void makePatronTest() {
        bob.setBirthday(012304);
        bob.setName("Bob");
        assertEquals("Bob", bob.getName());
        assertEquals(012304, bob.getBirthday());
    }

    @Test
    public void addShowsToPatronTest() {
        bob.addShow(cabaret);
        bob.addShow(crazyForYou);
        bob.addShow(cabaret);

        assertEquals(2, bob.myUpcomingShowSize());
        assertTrue(bob.isContainedInMyUpcomingShows(cabaret));
        assertTrue(bob.isContainedInMyUpcomingShows(crazyForYou));

    }

    @Test
    public void removeShowsFromPatronTest() {
        bob.addShow(cabaret);
        bob.addShow(crazyForYou);
        bob.addShow(cabaret);

        bob.removeShow(crazyForYou);

        assertEquals(1, bob.myUpcomingShowSize());
        assertEquals(1, bob.myPastShowSize());
        assertTrue(bob.isContainedInMyUpcomingShows(cabaret));
        assertFalse(bob.isContainedInMyUpcomingShows(crazyForYou));
        assertTrue(bob.isContainedInMyPastShows(crazyForYou));

        assertTrue(bob.getMyShows().isContainedInUpcoming(cabaret));

    }

}