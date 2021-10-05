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

    // EFFECT: Adds a new show to the patrons show list
    public void addShow(Show show) {
        myShows.addNewShow(show);
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
