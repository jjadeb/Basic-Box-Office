package model.people;


import model.shows.Show;

import model.shows.ShowList;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

//Represent a theatre patron that has signed up for an account with the theatre
public class Patron {

    String name;
    String birthday;
    ShowList myShows;

    //EFFECTS: constructs a patron
    public Patron() {
        myShows = new ShowList();
        name = "";
        birthday = "";
    }

    //MODIFIES: this
    // EFFECT: adds a new show to the patrons upcoming show list, can add multiple times to represent multiple tickets
    public void addShow(Show show) {
        myShows.addNewShow(show);
    }


    //MODIFIES: this
    //EFFECT: removes a show from the patrons upcoming show list and adds it to their past shows
    public void removeShow(Show show) {
        myShows.archive(show);
    }

    //EFFECTS: checks to see if the show is in the patron's upcoming show list
    public boolean isContainedInMyUpcomingShows(Show show) {
        return myShows.isContainedInUpcoming(show);
    }

    //EFFECTS: checks to see if the show is in the patron's past show list
    public boolean isContainedInMyPastShows(Show show) {
        return myShows.isContainedInPast(show);
    }

    //EFFECTS: checks to see the size of the patron's upcoming show list
    public int myUpcomingShowSize() {
        return myShows.getUpcomingShowsSize();
    }

    //EFFECTS: checks to see the size of the patron's past show list
    public int myPastShowSize() {
        return myShows.getPastShowsSize();
    }

    //REQUIRES: no patron should have the same name
    public void setName(String name) {
        this.name = name;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getName() {
        return name;
    }

    public String getBirthday() {
        return birthday;
    }

    public ShowList getMyShows() {
        return myShows;
    }

    //EFFECTS: Returns the names of upcoming shows
    public ArrayList<String> myUpcomingShowNames() {
        return myShows.getUpcomingShowNames();
    }

    //EFFECTS: Returns the names of past shows
    public ArrayList<String> myPastShowNames() {
        return myShows.getPastShowNames();
    }

    //EFFECTS: Gets patron information to put in Json

    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("birthday", birthday);
        json.put("patronUpcomingShows", patronUpcomingShowsToJson());
        json.put("patronPastShows", patronPastShowsToJson());
        return json;
    }

    // EFFECTS: returns things in the patron's upcoming shows as a JSON array
    private JSONArray patronUpcomingShowsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Show s : myShows.getUpcomingShows()) {
            jsonArray.put(s.toJsonPatron());
        }

        return jsonArray;
    }

    // EFFECTS: returns things in the patron's past shows as a JSON array
    private JSONArray patronPastShowsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Show s : myShows.getPastShows()) {
            jsonArray.put(s.toJsonPatron());
        }

        return jsonArray;
    }



}

