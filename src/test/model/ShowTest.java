package model;

import model.People.Patron;
import model.Shows.Show;
import model.Shows.ShowList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ShowTest {

    Show beautyAndTheBeast;
    Show singingInTheRain;
    Show cabaret;

    Patron bob;
    Patron shirley;

    @BeforeEach


    public void setUp() {
        beautyAndTheBeast = new Show();
        singingInTheRain = new Show();
        cabaret = new Show();

        bob = new Patron();
        shirley = new Patron();


    }

    @Test
    public void addPatronTest() {
        beautyAndTheBeast.addPatron(bob);
        beautyAndTheBeast.addPatron(shirley);

        assertEquals(2,beautyAndTheBeast.patronSize());
        assertTrue(beautyAndTheBeast.isContainsPatron(bob));
        assertTrue(beautyAndTheBeast.isContainsPatron(shirley));
    }

    @Test
    public void addPatronTwiceTest() {
        beautyAndTheBeast.addPatron(bob);
        beautyAndTheBeast.addPatron(shirley);
        beautyAndTheBeast.addPatron(bob);

        assertEquals(2,beautyAndTheBeast.patronSize());
        assertTrue(beautyAndTheBeast.isContainsPatron(bob));
        assertTrue(beautyAndTheBeast.isContainsPatron(shirley));
    }
}
