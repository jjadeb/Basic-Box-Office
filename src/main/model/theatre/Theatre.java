package model.theatre;

import model.Event;
import model.EventLog;
import model.people.Patron;
import model.people.PatronList;
import model.shows.Show;
import model.shows.ShowList;
import org.json.JSONArray;
import org.json.JSONObject;

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
        EventLog.getInstance().logEvent(new Event("Created a theatre."));
    }

    public void setName(String name) {
        this.name = name;
        EventLog.getInstance().logEvent(new Event("Set the name of the theatre to: " + name));
    }

    public String getName() {
        return name;
    }

    //MODIFIES: this
    //EFFECTS: adds a show to the upcoming show list
    public void addNewShow(Show show) {
        shows.addNewShow(show);
        EventLog.getInstance().logEvent(new Event("Added a show "
                + "to the theatre's upcoming show list."));
    }

    //MODIFIES: this
    //EFFECTS: moves a show from the upcoming show list to the past show list
    public void archiveShow(Show show) {
        for (Patron patron: show.getPatrons().getPatronList()) {
            patron.removeShow(show);
        }
        shows.archive(show);
        EventLog.getInstance().logEvent(new Event("Removed " + show.getTitle()
                + " from the theatre's upcoming show list and added it to it's past show list."));
    }

    //MODIFIES: this
    //EFFECTS: adds a patron to the list of theatre patrons
    public void addNewPatron(Patron patron) {
        patrons.addNewPatron(patron);
        EventLog.getInstance().logEvent(new Event("Added a patron"
                + " to the theatre's patron list."));
    }

    //MODIFIES: this
    //EFFECTS: removes a patron from the list of theatre patrons
    public void removePatron(Patron patron) {
        patrons.removePatron(patron);
        EventLog.getInstance().logEvent(new Event("Removed " + patron.getName()
                + " from the theatre's patron list."));
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

    //EFFECTS: returns the list of patrons
    public PatronList getPatrons() {
        return patrons;
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

    //EFFECTS: returns past shows
    public ArrayList<Show> getPastShows() {
        return shows.getPastShows();
    }

    //EFFECTS: retrieves patron from a theatre's list of patrons
    public Patron getPatron(String name, String birthday) {
        Patron find = null;
        for (Patron patron : patrons.getPatronList()) {
            if (patron.getName().equals(name) && (patron.getBirthday().equals(birthday))) {
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
    public boolean isGetTheatrePatron(String patronName, String patronBirthday) {
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

    //EFFECTS: returns a list of show names on a given date
    public ArrayList<String> showsOnThisDate(String date) {
        return shows.onThisDate(date);
    }

    //MODIFIES: this
    //EFFECTS: removes a show from upcoming and past show lists
    public void removeShow(Show show) {
        shows.removeShow(show);
        EventLog.getInstance().logEvent(new Event("Removed " + show.getTitle()
                + " from the theatre's show list."));
    }

    //EFFECTS: returns true if a name of a patron is already in the system, false otherwise
    public boolean containsPatronName(String patronName) {
        Boolean x = false;
        for (String name: this.getPatronNames()) {
            if (patronName.equals(name)) {
                x = true;
            }
        }
        return x;
    }

    //EFFECTS: returns true if a name of a show is already in the system, false otherwise
    public boolean containsShowName(String showName) {
        Boolean x = false;
        for (String name: this.getPastShowNames()) {
            if (showName.equals(name)) {
                x = true;
            }
        }
        for (String name: this.getUpcomingShowNames()) {
            if (showName.equals(name)) {
                x = true;
            }
        }
        return x;
    }

    //EFFECTS: puts theatre info into a json object
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("patrons", patronsToJson());
        json.put("upcoming", upcomingShowsToJson());
        json.put("past", pastShowsToJson());
        return json;
    }

    // EFFECTS: returns patrons in this theatre as a JSON array
    private JSONArray patronsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Patron p : patrons.getPatronList()) {
            jsonArray.put(p.toJson());
        }

        return jsonArray;
    }

//    // EFFECTS: returns shows in this theatre as a JSON array
//    private JSONObject showsToJson() {
//        JSONObject json = new JSONObject();
//        json.put("upcoming", upcomingShowsToJson());
//        json.put("past", pastShowsToJson());
//
//        return json;
//    }

    // EFFECTS: returns upcoming shows as a JSON array
    private JSONArray upcomingShowsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Show s : shows.getUpcomingShows()) {
            jsonArray.put(s.toJson());
        }

        return jsonArray;
    }

    // EFFECTS: returns past shows as a JSON array
    private JSONArray pastShowsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Show s : shows.getPastShows()) {
            jsonArray.put(s.toJson());
        }

        return jsonArray;
    }
}
