package model.theatre;

import model.people.Patron;
import model.people.PatronList;
import model.shows.Show;
import model.shows.ShowList;

import java.util.ArrayList;

//Represents a theatre that hosts shows
public class Theatre {


    String name;
    PatronList patrons;
    ShowList shows;


    //EFFECTS: constructs a theatre
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
    //EFFECTS: adds a show to the upcoming show list
    public void addNewShow(Show show) {
        shows.addNewShow(show);
    }

    //MODIFIES: this
    //EFFECTS: moves a show from the upcoming show list to the past show list
    public void archiveShow(Show show) {
        for (Patron patron: show.getPatrons().getPatronList()) {
            if (show.isContainsPatron(patron)) {
                patron.removeShow(show);
            }
        }
        shows.archive(show);
    }

    //MODIFIES: this
    //EFFECTS: adds a patron to the list of theatre patrons
    public void addNewPatron(Patron patron) {
        patrons.addNewPatron(patron);
    }

    //MODIFIES: this
    //EFFECTS: removes a patron from the list of theatre patrons
    public void removePatron(Patron patron) {
        patrons.removePatron(patron);
    }


    //EFFECTS: checks to see if a patron is in the patron list
    public boolean isContainsPatron(Patron patron) {
        return patrons.contains(patron);
    }

    //EFFECTS: returns the size of the patron list
    public int patronSize() {
        return patrons.getSize();
    }

    //EFFECTS: checks to see if a show is in the upcoming show list
    public boolean isUpcomingShow(Show show) {
        return shows.isContainedInUpcoming(show);
    }

    //EFFECTS: checks to see if a show is in the past show list
    public boolean isPastShow(Show show) {
        return shows.isContainedInPast(show);
    }

    //EFFECTS: returns the size of the upcoming show list
    public int upcomingShowSize() {
        return shows.getUpcomingShowsSize();
    }

    //EFFECTS: returns the size of the past show list
    public int pastShowSize() {
        return shows.getPastShowsSize();
    }

    //EFFECTS: returns the names of upcoming shows
    public ArrayList<String> getUpcomingShowNames() {
        return shows.getUpcomingShowNames();
    }

    //EFFECTS: returns the names of upcoming shows
    public ArrayList<String> getPastShowNames() {
        return shows.getPastShowNames();
    }

    //EFFECTS: returns upcoming shows
    public ArrayList<Show> getUpcomingShows() {
        return shows.getUpcomingShows();
    }

    //EFFECTS: retrieves patron from a theatre's list of patrons
    public Patron getPatron(String name, Integer birthday) {
        Patron find = null;
        for (Patron patron : patrons.getPatronList()) {
            if (patron.getName().equals(name) && (patron.getBirthday() == birthday)) {
                find = patron;
            }
        }
        return find;
    }

    //EFFECTS: returns the names of patrons in a patron list
    public ArrayList<String> getPatronNames() {
        ArrayList<String> patronNames = new ArrayList<>();
        for (Patron patron : patrons.getPatronList()) {
            String name = patron.getName();
            patronNames.add(name);
        }
        return patronNames;
    }

    //EFFECTS: returns true if a patron is in the records
    public boolean isGetTheatrePatron(String patronName, Integer patronBirthday) {
        return (!(getPatron(patronName, patronBirthday) == null));
    }


    //EFFECTS: returns the show of a give show name
    public Show getShow(String showName) {
        return shows.getShow(showName);
    }

    //EFFECTS: returns true if a show is in the records
    public boolean isGetTheatreShow(String showName) {
        return shows.isGetShow(showName);
    }

    //EFFECTS: Returns a list of show names on a given date
    public ArrayList<String> showsOnThisDate(String date) {
        return shows.onThisDate(date);
    }

}
