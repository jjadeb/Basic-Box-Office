package model.Shows;

import model.People.Patron;
import model.People.PatronList;

import java.util.ArrayList;

// Represents a theatre show
public class Show {

    private String title;
    private ArrayList<Integer> dates;
    private PatronList patrons;

    public Show() {
        patrons = new PatronList();
        dates = new ArrayList<>();
        title = "";
    }

    //MODIFIES: this
    //EFFECTS: adds a patron to the list of people with tickets to the show (if not already there)
    public void addPatron(Patron patron) {
        if (!patrons.contains(patron)) {
            patrons.addNewPatron(patron);
        }
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

    //EFFECTS: Checks to see if a patron is in the patron list
    public boolean isContainsPatron(Patron patron) {
        return patrons.contains(patron);
    }

    //EFFECTS: Returns the size of the patron list
    public int patronSize() {
        return patrons.size();
    }
}
