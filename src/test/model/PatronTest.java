package model;

import model.People.Patron;
import model.Theatre.Theatre;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PatronTest {

    Patron bob;
    Patron shirley;
    Theatre Massey;

    @BeforeEach

    public void setUp() {
        bob = new Patron();
    }

    @Test

    public void makePatronTest() {
        bob.setBirthday(012304);
        bob.setName("Bob");
        assertEquals("Bob", bob.getName());
        assertEquals(012304, bob.getBirthday());
    }

    public void addShowsToPatronTest() {

    }

}