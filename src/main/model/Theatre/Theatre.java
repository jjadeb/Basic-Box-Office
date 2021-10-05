package model.Theatre;

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


    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void addNewShow(Show show) {
        shows.addNewShow(show);
    }

    public void archiveShow(Show show) {
        shows.archive(show);
    }

}
