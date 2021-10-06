package model.Theatre;

import model.People.Patron;
import model.People.PatronList;
import model.Shows.Show;
import model.Shows.ShowList;

import java.util.ArrayList;

//Represents a theatre
public class Theatre {


    String name;
    PatronList patrons;
    ShowList shows;


    public Theatre() {
        patrons = new PatronList();
        shows = new ShowList();
        name = "";


    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    //MODIFIES: this
    //EFFECTS: Adds a show to the upcoming show list
    public void addNewShow(Show show) {
        shows.addNewShow(show);
    }

    //REQUIRES: Show must be in upcoming show list
    //MODIFIES: this
    //EFFECTS: Moves a show from the upcoming show list to the past show list
    public void archiveShow(Show show) {
        shows.archive(show);
    }

    //MODIFIES: this
    //EFFECTS: Adds a patron to the list of theatre patrons
    public void addNewPatron(Patron patron) {
        patrons.addNewPatron(patron);
    }

    //MODIFIES: this
    //EFFECTS: Removes a patron from the list of theatre patrons
    public void removePatron(Patron patron) {
        patrons.removePatron(patron);
    }


    //EFFECTS: Checks to see if a patron is in the patron list
    public boolean isContainsPatron(Patron patron) {
        return patrons.contains(patron);
    }

    //EFFECTS: Returns the size of the patron list
    public int patronSize() {
        return patrons.size();
    }

    //EFFECTS: Checks to see if a show is in the upcoming show list
    public boolean isUpcomingShow(Show show) {
        return shows.isContainedInUpcoming(show);
    }

    //EFFECTS: Checks to see if a show is in the past show list
    public boolean isPastShow(Show show) {
        return shows.isContainedInPast(show);
    }

    //EFFECTS: Returns the size of the upcoming show list
    public int upcomingShowSize() {
        return shows.getUpcomingShowsSize();
    }

    //EFFECTS: Returns the size of the past show list
    public int pastShowSize() {
        return shows.getPastShowsSize();
    }

}
