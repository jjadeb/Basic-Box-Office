package model.People;

import model.People.Patron;

import java.util.ArrayList;

//Represents a list of the patrons that have accounts with the theatre
public class PatronList {

    private ArrayList<Patron> patronList;

    public PatronList() {
        patronList = new ArrayList<>();
    }

    //MODIFIES this
    //EFFECTS: Adds a new patron to the list of patrons
    public void addNewPatron(Patron patron) {
        if (!patronList.contains(patron)) {
            patronList.add(patron);
        }
    }

    //REQUIRES: Patron be in the list of patrons
    //MODIFIES: this
    //EFFECTS: Removes a new patron from the list of patrons
    public void removePatron(Patron patron) {
        patronList.remove(patron);
    }

    public ArrayList<Patron> getPatronList() {
        return patronList;
    }

    public int size() {
        return patronList.size();
    }

    public boolean contains(Patron patron) {
        return patronList.contains(patron);
    }
}