package model;

import model.people.Patron;
import model.shows.Show;
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
        bob.setName("Bob");
        shirley.setName("Shirley");


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

    @Test
    public void getAndSetPrice() {
        beautyAndTheBeast.setTicketPrice(15);
        assertEquals(15, beautyAndTheBeast.getTicketPrice());
    }

    @Test
    public void getPatronNamesTest() {
        beautyAndTheBeast.addPatron(bob);
        beautyAndTheBeast.addPatron(shirley);

        assertEquals(2, beautyAndTheBeast.getPatronNames().size());
        assertTrue(beautyAndTheBeast.getPatronNames().contains("Bob"));
        assertTrue(beautyAndTheBeast.getPatronNames().contains("Shirley"));
    }
}
