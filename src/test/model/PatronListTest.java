package model;

import model.people.Patron;
import model.people.PatronList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PatronListTest {

    PatronList listOfPatrons;
    Patron bob;
    Patron shirley;

    @BeforeEach

    public void setUp() {
        listOfPatrons = new PatronList();
        bob = new Patron();
        shirley = new Patron();
    }

    @Test

    public void addPatron() {
        listOfPatrons.addNewPatron(bob);
        listOfPatrons.addNewPatron(shirley);

        assertTrue(listOfPatrons.contains(bob));
        assertTrue(listOfPatrons.contains(shirley));
        assertEquals(2, listOfPatrons.getSize());

        assertTrue(listOfPatrons.getPatronList().contains(bob));
        assertTrue(listOfPatrons.getPatronList().contains(shirley));
    }

    @Test

    public void addPatronTwice() {
        listOfPatrons.addNewPatron(bob);
        listOfPatrons.addNewPatron(bob);

        assertTrue(listOfPatrons.contains(bob));
        assertEquals(2, listOfPatrons.getSize());

        assertTrue(listOfPatrons.getPatronList().contains(bob));


    }
}
