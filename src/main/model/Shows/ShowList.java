package model.Shows;

import model.Shows.Show;

import java.util.ArrayList;

// Represents a record of upcoming shows and of past shows
public class ShowList {
    private ArrayList<Show> upcomingShows;
    private ArrayList<Show> pastShows;


    public ShowList() {
        upcomingShows = new ArrayList<>();
        pastShows = new ArrayList<>();
    }

    //MODIFIES: this
    //EFFECTS: adds a show to the list of upcoming shows if not already there
    public void addNewShow(Show show) {
        if (!upcomingShows.contains(show)) {
            upcomingShows.add(show);
        }
    }


    //REQUIRES: only to be used on upcomingShow lists
    //MODIFIES: this and pastShows
    //EFFECTS: removes a show from upcoming shows and adds it to past shows
    public void archive(Show show) {
        upcomingShows.remove(show);
        pastShows.add(show);
    }

    //REQUIRES: date must be in form MMDDYY
    //EFFECTS: provides a list of names of upcoming shows on the date provided, empty if none
    public ArrayList<String> onThisDate(int givenDate) {
        ArrayList<String> showsOnThisDate = new ArrayList<>();
        for (Show show: upcomingShows) {
            for (int showDate: show.getDates()) {
                if (showDate == givenDate) {
                    showsOnThisDate.add(show.getTitle());
                }
            }
        }
        for (Show show: pastShows) {
            for (int showDate: show.getDates()) {
                if (showDate == givenDate) {
                    showsOnThisDate.add(show.getTitle());
                }
            }
        }
        return showsOnThisDate;
    }


    public boolean isContainedInUpcoming(Show show) {
        return upcomingShows.contains(show);
    }

    public boolean isContainedInPast(Show show) {
        return pastShows.contains(show);
    }


    public ArrayList getUpcomingShows() {
        return upcomingShows;
    }

    public int getUpcomingShowsSize() {
        return upcomingShows.size();
    }

    public int getPastShowsSize() {
        return pastShows.size();
    }

    public ArrayList getPastShows() {
        return pastShows;
    }
}
