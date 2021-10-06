package model.People;

import java.util.ArrayList;

import model.Shows.Show;
import model.Theatre.Theatre;

import model.Shows.ShowList;

//Represent a theatre patron that has signed up for an account with the theatre
public class Patron {

    String name;
    int birthday;
    ShowList myShows;

    public Patron() {
        myShows = new ShowList();
        name = "";
        birthday = 000000;


    }

    //MODIFIES: this
    // EFFECT: adds a new show to the patrons upcoming show list
    public void addShow(Show show) {
        myShows.addNewShow(show);
    }

    //REQUIRES: the show must not already be in the past show list
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

    public void setName(String name) {
        this.name = name;
    }

    public void setBirthday(int birthday) {
        this.birthday = birthday;
    }

    public String getName() {
        return name;
    }

    public int getBirthday() {
        return birthday;
    }


    public ShowList getMyShows() {
        return myShows;
    }


}
