package model.Shows;

import model.People.Patron;
import model.People.PatronList;

import java.util.ArrayList;

// Represents a show that a theatre hosts and that patrons buy tickets for
public class Show {

    private String title;
    private ArrayList<String> dates;
    private PatronList patrons;
    private int ticketPrice;


    public Show() {
        patrons = new PatronList();
        dates = new ArrayList<>();
        title = "";
        ticketPrice = 0;
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
    //EFFECTS: add a date to the show
    public void addDate(String date) {
        dates.add(date);
    }


    public ArrayList<String> getDates() {
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
        return patrons.getSize();
    }

    public int getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(int ticketPrice) {
        this.ticketPrice = ticketPrice;
    }
}
