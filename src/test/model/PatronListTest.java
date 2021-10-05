package model;

import model.People.Patron;
import model.People.PatronList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

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
        assertEquals(2, listOfPatrons.size());
    }

    @Test

    public void addPatronTwice() {
        listOfPatrons.addNewPatron(bob);
        listOfPatrons.addNewPatron(bob);

        assertTrue(listOfPatrons.contains(bob));
        assertEquals(1, listOfPatrons.size());


    }
}
