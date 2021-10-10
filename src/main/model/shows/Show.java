package model.shows;

import model.people.Patron;
import model.people.PatronList;

import java.util.ArrayList;

// Represents a show that a theatre hosts and that patrons buy tickets for
public class Show {

    private String title;
    private ArrayList<String> dates;
    private PatronList patrons;
    private double ticketPrice;


    public Show() {
        patrons = new PatronList();
        dates = new ArrayList<>();
        title = "";
        ticketPrice = 0;
    }

    //MODIFIES: this
    //EFFECTS: adds a patron to the list of people with tickets to the show
    public void addPatron(Patron patron) {
        patrons.addNewPatron(patron);
    }

    //MODIFIES: this
    //EFFECTS: removes a patron from the list of people with tickets to the show
    public void removePatron(Patron patron) {
        patrons.removePatron(patron);
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

    //REQUIRES: each date must be of the form MMDDYY
    //MODIFIES: this
    //EFFECTS: removes a date from the show
    public void removeDate(String date) {
        dates.remove(date);
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

    public double getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(double ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    //EFFECTS: Returns the names of patrons who have tickets for the show, multiple names means multiple tickets
    public ArrayList<String> getPatronNames() {
        ArrayList<String> patronNames = new ArrayList<>();
        for (Patron patron : patrons.getPatronList()) {
            String name = patron.getName();
            patronNames.add(name);
        }
        return patronNames;
    }


}
