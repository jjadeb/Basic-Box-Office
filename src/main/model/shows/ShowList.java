package model.shows;


import java.util.ArrayList;

// Represents a record of a patron's or theatre's upcoming shows and past shows
public class ShowList {
    private ArrayList<Show> upcomingShows;
    private ArrayList<Show> pastShows;


    //EFFECTS: constructs a list of shows including upcoming and past
    public ShowList() {
        upcomingShows = new ArrayList<>();
        pastShows = new ArrayList<>();
    }


    //MODIFIES: this
    //EFFECTS: adds a show to the list of upcoming shows; can add multiple times to represent multiple tickets
    public void addNewShow(Show show) {
        upcomingShows.add(show);
    }


    //MODIFIES: this
    //EFFECTS: removes a show from upcoming shows and adds it to past shows
    public void archive(Show show) {
        upcomingShows.remove(show);
        pastShows.add(show);
    }

    //REQUIRES: date must be in form MMDDYY
    //EFFECTS: provides a list of names of upcoming shows on the date provided, empty if none
    public ArrayList<String> onThisDate(String givenDate) {
        ArrayList<String> showsOnThisDate = new ArrayList<>();
        for (Show show : upcomingShows) {
            for (String showDate : show.getDates()) {
                if (showDate.equals(givenDate)) {
                    showsOnThisDate.add(show.getTitle());
                }
            }
        }
        for (Show show : pastShows) {
            for (String showDate : show.getDates()) {
                if (showDate.equals(givenDate)) {
                    showsOnThisDate.add(show.getTitle());
                }
            }
        }
        return showsOnThisDate;
    }


    //EFFECTS: returns true if a show is in the upcoming show list
    public boolean isContainedInUpcoming(Show show) {
        return upcomingShows.contains(show);
    }

    //EFFECTS: returns true if a show is in the past show list
    public boolean isContainedInPast(Show show) {
        return pastShows.contains(show);
    }


    //EFFECTS: returns names of upcoming shows
    public ArrayList<String> getUpcomingShowNames() {
        ArrayList<String> temp = new ArrayList<>();
        for (Show show : upcomingShows) {
            temp.add(show.getTitle());
        }
        return temp;
    }

    //EFFECTS: returns names of past shows
    public ArrayList<String> getPastShowNames() {
        ArrayList<String> temp = new ArrayList<>();
        for (Show show : pastShows) {
            temp.add(show.getTitle());
        }
        return temp;
    }

    //MODIFIES: this
    //EFFECTS: removes show from upcoming and past show lists
    public void removeShow(Show show) {
        if (upcomingShows.contains(show)) {
            upcomingShows.remove(show);
        } else if (pastShows.contains(show)) {
            pastShows.remove(show);
        }
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

    public ArrayList<Show> getUpcomingShows() {
        return upcomingShows;
    }

    //EFFECT: returns show of given show name
    public Show getShow(String showName) {
        Show theShow = null;
        for (Show show : upcomingShows) {
            if (showName.equals(show.getTitle())) {
                theShow = show;
            }
        }
        for (Show show : pastShows) {
            if (showName.equals(show.getTitle())) {
                theShow = show;
            }
        }
        return theShow;
    }

    //EFFECTS: Returns true if there is a show to get
    public boolean isGetShow(String show) {
        return (!(this.getShow(show) == null));
    }
}