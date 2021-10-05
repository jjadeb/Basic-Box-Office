package model.Shows;

import model.People.Patron;
import model.People.PatronList;

import java.util.ArrayList;

// Represents a show that the theatre has booked
public class Show {

    private String title;
    private ArrayList<Integer> dates;
    private PatronList patrons;

    public Show() {
        patrons = new PatronList();
    }

    //MODIFIES: this
    //EFFECTS: adds a patron to the list of people with tickets to the show
    public void addPatron(Patron patron) {
        patrons.addNewPatron(patron);
    }

    public void setTitle(String title) {
        this.title = title;
    }

    //REQUIRES: each date must be of the form MMDDYY
    //MODIFIES: this
    //EFFECTS: sets the dates of the show
    public void setDates(ArrayList<Integer> dates) {
        this.dates = dates;
    }


    public ArrayList<Integer> getDates() {
        return dates;
    }

    public String getTitle() {
        return title;
    }

    public PatronList getPatrons() {
        return patrons;
    }
}
