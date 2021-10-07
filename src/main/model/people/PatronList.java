package model.people;


import java.util.ArrayList;

//Represents a list of patrons
public class PatronList {

    private ArrayList<Patron> patronList;

    public PatronList() {
        patronList = new ArrayList<>();
    }

    //MODIFIES this
    //EFFECTS: adds a new patron to the list of patrons
    public void addNewPatron(Patron patron) {
        if (!patronList.contains(patron)) {
            patronList.add(patron);
        }
    }

    //MODIFIES: this
    //EFFECTS: removes a new patron from the list of patrons
    public void removePatron(Patron patron) {
        patronList.remove(patron);
    }

    public ArrayList<Patron> getPatronList() {
        return patronList;
    }

    //EFFECTS: returns the size of the patron list
    public int getSize() {
        return patronList.size();
    }

    //EFFECTS: checks to see if the patron list contains a patron
    public boolean contains(Patron patron) {
        return patronList.contains(patron);
    }
}
