package model;

import model.People.Patron;
import model.Shows.Show;
import model.Shows.ShowList;
import model.Theatre.Theatre;
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
        legallyBlonde = new Show();
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

        assertEquals(2, massey.upcomingShowSize());
        assertTrue(massey.isUpcomingShow(cabaret));
        assertTrue(massey.isUpcomingShow(legallyBlonde));

    }

    @Test
    public void archiveShowTest() {
        massey.addNewShow(cabaret);
        massey.addNewShow(legallyBlonde);
        massey.addNewShow(cabaret);

        massey.archiveShow(legallyBlonde);

        assertEquals(1, massey.upcomingShowSize());
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
}
