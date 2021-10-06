package model.Shows;


import java.util.ArrayList;

// Represents a record of a patron's or theatre's upcoming shows and past shows
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


    //REQUIRES: show must not already be in the pastShow list
    //MODIFIES: this
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


    //EFFECTS: checks to see if a show is in the upcoming show list
    public boolean isContainedInUpcoming(Show show) {
        return upcomingShows.contains(show);
    }

    //EFFECTS: checks to see if a show is in the past show list
    public boolean isContainedInPast(Show show) {
        return pastShows.contains(show);
    }


    public ArrayList<Show> getUpcomingShows() {
        return upcomingShows;
    }

    //EFFECT: returns the size of the upcoming show list
    public int getUpcomingShowsSize() {
        return upcomingShows.size();
    }

    //EFFECT: returns the size of the past show list
    public int getPastShowsSize() {
        return pastShows.size();
    }

    public ArrayList<Show> getPastShows() {
        return pastShows;
    }

}
