package model.shows;

import model.Event;
import model.EventLog;
import model.people.Patron;
import model.people.PatronList;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

// Represents a show that a theatre hosts and that patrons buy tickets for
public class Show {

    private String title;
    private ArrayList<String> dates;
    private PatronList patrons;
    private double ticketPrice;


    //EFFECTS: constructs a show
    public Show() {
        patrons = new PatronList();
        dates = new ArrayList<>();
        title = "";
        ticketPrice = 0;
        EventLog.getInstance().logEvent(new Event("Created a new show."));
    }

    //MODIFIES: this
    //EFFECTS: adds a patron to the list of people with tickets to the show
    public void addPatron(Patron patron) {
        patrons.addNewPatron(patron);
        EventLog.getInstance().logEvent(new Event("Added " + patron.getName()
                + " to " + title + "'s patron list."));
    }

    //MODIFIES: this
    //EFFECTS: removes a patron from the list of people with tickets to the show
    public void removePatron(Patron patron) {
        patrons.removePatron(patron);
        EventLog.getInstance().logEvent(new Event("Removed " + patron.getName()
                + " from " + title + "'s patron list."));
    }


    //REQUIRES: show shouldn't have the same title as another show
    public void setTitle(String title) {
        this.title = title;
        EventLog.getInstance().logEvent(new Event("Set the title of the show to: " + title));
    }


    //REQUIRES: each date must be of the form MMDDYY
    //MODIFIES: this
    //EFFECTS: add a date to the show
    public void addDate(String date) {
        dates.add(date);
        EventLog.getInstance().logEvent(new Event("Added " + date
                + " to " + title + "'s dates."));
    }

    //REQUIRES: each date must be of the form MMDDYY
    //MODIFIES: this
    //EFFECTS: removes a date from the show
    public void removeDate(String date) {
        dates.remove(date);
        EventLog.getInstance().logEvent(new Event("Removed " + date
                + " from " + title + "."));
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
        EventLog.getInstance().logEvent(new Event("Set " + title + "'s ticket price to "
                + ticketPrice + "."));
    }

    public PatronList getPatrons() {
        return patrons;
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

    //EFFECTS: Returns patron show as a json object
    public JSONObject toJsonPatron() {
        JSONObject json = new JSONObject();
        json.put("show", title);
        return json;
    }

    //EFFECTS: returns show as a json object
    public JSONObject toJson() {
        PatronList emptyPatrons = new PatronList();
        JSONObject json = new JSONObject();
        json.put("title", title);
        json.put("ticketPrice", ticketPrice);
        json.put("dates", datesToJson());
        json.put("showPatrons", emptyPatrons);
        return json;
    }

    // EFFECTS: returns dates in a show as a JSON array
    private JSONArray datesToJson() {
        JSONArray jsonArray = new JSONArray();

        for (String d : dates) {
            JSONObject json = new JSONObject();
            json.put("date", d);
            jsonArray.put(json);
        }
        return jsonArray;
    }

}
