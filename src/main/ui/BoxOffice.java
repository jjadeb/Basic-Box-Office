package ui;

import model.people.Patron;
import model.shows.Show;
import model.theatre.Theatre;

import java.util.Scanner;

import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;

//This is where all the nitty gritty of the user code is
public class BoxOffice {

    private Scanner scanner; //some structure and code for the scanner taken from SimpleCalculatorSolutionLecLab
    private Theatre theatre = new Theatre();

    //EFFECTS: constructs a box office
    public BoxOffice() {
        scanner = new Scanner(System.in);
        if (theatre.getName().equals("")) {
            setUp();
        }
        mainMenu();
    }


    //MODIFIES: theatre
    //EFFECTS: sets up the theatre information
    public void setUp() {
        System.out.println("Welcome to the Box Office!");
        System.out.println("First, set up the information for your theatre.");

        //set name
        System.out.println("Please enter the name of your theatre:");
        theatre.setName(scanner.nextLine());

        //setup completed
        System.out.println("Setup Complete!");

    }

    //EFFECTS: Lets user choose whether to sell a ticket or change/view theatre info
    public void mainMenu() {
        System.out.println("(1) Sell a Ticket, (2) Modify/View Theatre Information, "
                + "or (3) quit? please type the corresponding number.");
        String response = scanner.nextLine();
        if (response.equals("1")) {
            preTicketSale();
        } else if (response.equals("2")) {
            theatreInformation();
        }  else if (response.equals("3")) {
            System.exit(1);
        } else {
            System.out.println("That wasn't one of the options! Let's see them again.");
            mainMenu();
        }
        end();
    }


    //EFFECTS: lets user choose whether they want to view or change theatre information
    public void theatreInformation() {
        System.out.println("Would you like to (1) view theatre information, (2) modify theatre information, "
                + "or (3) go back to the main menu?");
        String temp = scanner.nextLine(); //simp calc code
        if (temp.equals("1")) {
            theatreInfoViewing();
        } else if (temp.equals("2")) {
            theatreInfoModifying();
        } else if (temp.equals("3")) {
            mainMenu();
        } else {
            System.out.println("That wasn't one of the options. Let's try again.");
            theatreInformation();
        }
    }

    //EFFECTS: lets user choose which theatre information to view
    public void theatreInfoViewing() {
        System.out.println("What info would you like to view? (1) upcoming shows, (2) past shows, (3) theatre patrons, "
                + "(4) theatre name, (5) a show, (6) a patron, (7) shows on a given date, (8) go to main menu");
        String temp = scanner.nextLine();
        if (temp.equals("1")) {
            System.out.println("Here are the upcoming theatre shows: " + theatre.getUpcomingShowNames());
        } else if (temp.equals("2")) {
            System.out.println("Here are the past shows: " + theatre.getPastShowNames());
        } else if (temp.equals("3")) {
            System.out.println("Here are the theatre patrons: " + theatre.getPatronNames());
        } else if (temp.equals("4")) {
            System.out.println("Here is the theatre name: " + (theatre.getName()));
        } else if (temp.equals("5")) {
            viewShowInfo();
        } else if (temp.equals("6")) {
            viewPatronInformation();
        } else if (temp.equals("7")) {
            showFromGivenDate();
        } else if (temp.equals("8")) {
            mainMenu();
        } else {
            System.out.println("That wasn't one of the options! Let's try again.");
            theatreInfoViewing();
        }
    }

    //EFFECTS: lets user view which shows are on a given date
    public void showFromGivenDate() {
        System.out.println("For which date would you like to view the shows?");
        String date = scanner.nextLine();
        System.out.println("Here are the shows on " + date + ": " + theatre.showsOnThisDate(date));
    }


    //EFFECTS: retrieves different information relating to a patron
    public void viewPatronInformation() {
        Patron patron = getPatron();
        System.out.println("What information would you like to view? (1) upcoming shows, (2) past shows");
        String temp = scanner.nextLine();
        if (temp.equals("1")) {
            System.out.println("Here is " + patron.getName() + "'s upcoming shows: " + patron.myUpcomingShowNames());
        } else if (temp.equals("2")) {
            System.out.println("Here is " + patron.getName() + "'s past shows: " + patron.myPastShowNames());
        } else {
            System.out.println("That wasn't one of the options! (1) try again, or (2) go to main menu");
            String temp2 = scanner.nextLine();
            if (temp2.equals("1")) {
                viewPatronInformation();
            } else {
                mainMenu();
            }
        }
    }

    //EFFECTS: lets user choose which patron information to change
    public void changePatronInformation() {
        Patron patron = getPatron();
        System.out.println("Which patron information would you like to change? (1) name, (2) birthday, (3) shows, "
                + "(4) nothing, go to main menu");
        String temp = scanner.nextLine();
        if (temp.equals("1")) {
            changePatronName(patron);
        } else if (temp.equals("2")) {
            changePatronBirthday(patron);
        } else if (temp.equals("3")) {
            changePatronShow(patron);
        } else if (temp.equals("4")) {
            mainMenu();
        } else {
            System.out.println("That wasn't one of the options! Let's go to the main menu.");
            mainMenu();
        }
    }

    //MODIFIES: patron
    //EFFECTS: allows user to change patron name
    public void changePatronName(Patron patron) {
        System.out.println("What would you like to change the patron's name to?");
        patron.setName(scanner.nextLine());
        System.out.println("The patron's name is now " + patron.getName());
    }

    //MODIFIES: patron
    //EFFECTS: allows user to change patron birthday
    public void changePatronBirthday(Patron patron) {
        System.out.println("What would you like to change the patron's birthday to? Please enter in form MMDDYY");
        patron.setBirthday(parseInt(scanner.nextLine()));
        System.out.println("The patron's birthday is now " + patron.getBirthday());
    }


    //EFFECTS: allows user to choose what show info to change
    public void changePatronShow(Patron patron) {
        System.out.println("What show information would you like to change? (1) add new show, (2) remove show"
                + " (3) nothing go to main menu");
        String temp = scanner.nextLine();
        if (temp.equals("1")) {
            patronAddNewShow(patron);
        } else if (temp.equals("2")) {
            patronRemoveShow(patron);
        } else if (temp.equals("3")) {
            mainMenu();
        } else {
            System.out.println("That wasn't one of the options! Lets try again.");
            changePatronShow(patron);
        }

    }

    //MODIFIES: show, patron
    //EFFECTS: lets a user add a show to the patron's show list
    public void patronAddNewShow(Patron patron) {
        System.out.println("Here are the upcoming shows: " + theatre.getUpcomingShowNames());
        System.out.println("Which show would you like to add the patron to?");
        String temp1 = scanner.nextLine();
        if (theatre.isGetTheatreShow(temp1)) {
            Show show = theatre.getShow(temp1);
            show.addPatron(patron);
            patron.addShow(show);
            System.out.println(show.getTitle() + " has been added to " + patron.getName() + "'s show list!");
            System.out.println("Would you like to add another show to the patron? "
                    + "(1) 'yes' or (2) 'no'. Please type the corresponding number");
            String temp = scanner.nextLine();
            if (temp.equals("1")) {
                patronAddNewShow(patron);
            } else if (temp.equals("2")) {
                //
            } else {
                System.out.println("That wasn't one of the options! Let's go to the main menu.");
                mainMenu();
            }
        } else {
            System.out.println("That show is not in our records. Let's go to the man menu.");
            mainMenu();
        }
    }



    //MODIFIES: show, patron
    //EFFECTS: lets a user remove the show from the patron's show list
    public void patronRemoveShow(Patron patron) {
        System.out.println("Here are the patron's shows: " + patron.myUpcomingShowNames());
        System.out.println("Which show would you like to remove from the patron?");
        String input = scanner.nextLine();
        if (theatre.isGetTheatreShow(input)) {
            Show show = theatre.getShow(input);

            show.removePatron(patron);
            patron.removeShow(show);

            System.out.println(show.getTitle() + " has been removed from " + patron.getName() + "'s show list!");

            System.out.println("Would you like to remove another show from the patron's list? "
                    + "(1) 'yes' or (2) 'no'. Please type the corresponding number");
            String temp = scanner.nextLine();
            if (temp.equals("1")) {
                patronRemoveShow(patron);
            } else if (temp.equals("2")) {
                //
            } else {
                System.out.println("That wasn't one of the options! Let's go to the main menu.");
                mainMenu();
            }
        } else {
            System.out.println("This show is not in our system yet. Let's go back to the main menu");
            mainMenu();
        }
    }


    //EFFECTS: lets user choose which show information to change
    public void changeShowInfo() {
        System.out.println("What would you like to do? (1) add a new show, (2) archive a show, "
                + "(3) change a specific show's info, (4) go to the main menu");
        String temp = scanner.nextLine();
        if (temp.equals("1")) {
            theatreAddShow();
        } else if (temp.equals("2")) {
            theatreArchiveShow();
        } else if (temp.equals("3")) {
            changeSpecificShow();
        } else if (temp.equals("4")) {
            mainMenu();
        } else {
            System.out.println("That wasn't one of the options! Let's try again.");
            changeShowInfo();
        }
    }

     //MODIFIES: theatre
     //EFFECTS: lets user archive a theatre show
    public void theatreArchiveShow() {
        System.out.println("Which show would you like to archive?");
        System.out.println("Here is the list of upcoming shows: " + theatre.getUpcomingShowNames());
        String input = scanner.nextLine();
        if (theatre.isGetTheatreShow(input)) {
            Show show = theatre.getShow(input);
            theatre.archiveShow(show);
            System.out.println(show.getTitle() + " is now archived!");

            System.out.println("Would you like to archive another show? "
                    + "(1) 'yes' or (2) 'no'. Please type the corresponding number.");
            String temp = scanner.nextLine();
            if (temp.equals("1")) {
                theatreArchiveShow();
            } else if (temp.equals("2")) {
                //
            } else {
                System.out.println("That wasn't one of the options! Going to main menu.");
                mainMenu();
            }
        } else {
            System.out.println("That show is not in our records. Let's go back to the main menu.");
            mainMenu();
        }

    }


    //EFFECTS: Lets user choose which show's information they would like to change
    @SuppressWarnings("methodlength")
    public void changeSpecificShow() {
        System.out.println("Here is the list of upcoming and past shows: " + theatre.getUpcomingShowNames()
                + theatre.getPastShowNames());
        System.out.println("Which show's info would you like to modify?");
        String input = scanner.nextLine();
        if (theatre.isGetTheatreShow(input)) {
            Show show = theatre.getShow(input);
            System.out.println("Which info would you like to modify? (1) title, (2) dates, (3) patrons, "
                    + "(4) ticket price, (5) nothing (go back to main menu)");
            String temp = scanner.nextLine();
            if (temp.equals("1")) {
                modifyShowTitle(show);
            } else if (temp.equals("2")) {
                modifyShowDates(show);
            } else if (temp.equals("3")) {
                modifyShowPatrons(show);
            } else if (temp.equals("4")) {
                modifyShowTicketPrice(show);
            } else if (temp.equals("5")) {
                mainMenu();
            } else {
                System.out.println("That wasn't one of the options! Let's go back to the main menu.");
                mainMenu();
            }
        } else {
            System.out.println("That show is not in our system yet. Let's go to the main menu.");
            mainMenu();
        }
    }

    //MODIFIES: show
    //EFFECTS: lets user modify show title
    public void modifyShowTitle(Show show) {
        System.out.println("Here is the current title of the show: " + show.getTitle());
        System.out.println("What would you like to change the title of the show to?");
        String newTitle = scanner.nextLine();
        show.setTitle(newTitle);
        System.out.println("The new title of the show is: " + show.getTitle());
    }

    //MODIFIES: show
    //EFFECTS: lets user modify show dates
    public void modifyShowDates(Show show) {
        System.out.println("Here are the current dates of the show: " + show.getDates());
        System.out.println("Would you like to (1) add a date, (2) remove a date, (3) go back to the main menu");
        String temp = scanner.nextLine();
        if (temp.equals("1")) {
            theatreAddDate(show);
        } else if (temp.equals("2")) {
            theatreRemoveDate(show);
        } else if (temp.equals("3")) {
            mainMenu();
        } else {
            System.out.println("That wasn't one of the options! Let's go back to the main menu.");
            mainMenu();
        }

    }


    //EFFECTS: lets user choose how to modify a show's patrons
    public void modifyShowPatrons(Show show) {
        System.out.println("Here are the current patrons of the show: " + show.getPatronNames());
        System.out.println("Would you like to (1) add a patron, (2) remove a patron, (3) go back to the main menu");
        String temp = scanner.nextLine();
        if (temp.equals("1")) {
            theatreAddPatronToShow(show);
        } else if (temp.equals("2")) {
            theatreRemovePatronFromShow(show);
        } else if (temp.equals("3")) {
            mainMenu();
        } else {
            System.out.println("That wasn't one of the options! Let's go back to the main menu.");
            mainMenu();
        }
    }


    //MODIFIES: patron, show
    //EFFECTS: lets user add a patron to a show
    public void theatreAddPatronToShow(Show show) {

        Patron patron = getPatron();

        show.addPatron(patron);
        patron.addShow(show);

        System.out.println(patron.getName() + " has been added to " + show.getTitle() + "!");

        System.out.println("Would you like to add another patron to a show? "
                + "(1) 'yes' or (2) 'no'. Please type the corresponding number");
        String temp = scanner.nextLine();
        if (temp.equals("1")) {
            theatreAddPatronToShow(show);
        } else if (temp.equals("2")) {
            //
        } else {
            System.out.println("That wasn't one of the options! (1) try again, or (2) go to main menu");
            String temp3 = scanner.nextLine();
            if (temp3.equals("1")) {
                theatreAddPatronToShow(show);
            } else {
                mainMenu();
            }
        }
    }

    //MODIFIES: patron, show
    //EFFECTS: lets user remove a patron from a show
    public void theatreRemovePatronFromShow(Show show) {
        Patron patron = getPatron();

        show.removePatron(patron);
        patron.removeShow(show);

        System.out.println(patron.getName() + " has been removed from " + show.getTitle() + "!");

        System.out.println("Would you like to remove another patron from a show? "
                + "(1) 'yes' or (2) 'no'. Please type the corresponding number");
        String temp = scanner.nextLine();
        if (temp.equals("1")) {
            theatreRemovePatronFromShow(show);
        } else if (temp.equals("2")) {
            //
        } else {
            System.out.println("That wasn't one of the options! (1) try again, or (2) go to main menu");
            String temp3 = scanner.nextLine();
            if (temp3.equals("1")) {
                theatreRemovePatronFromShow(show);
            } else {
                mainMenu();
            }
        }
    }

    //MODIFIES: show
    //EFFECTS: lets user change a show ticket price
    public void modifyShowTicketPrice(Show show) {
        System.out.println("Here is the current ticket price of " + show.getTitle() + ":" + show.getTicketPrice());
        System.out.println("What would you like the new ticket price to be?");
        String newPrice = scanner.nextLine();
        show.setTicketPrice(parseDouble(newPrice));
        System.out.println("The new ticket price of " + show.getTitle() + " is " + show.getTicketPrice());
    }


    //EFFECTS: lets user see information pertaining to a specific show
    public void viewShowInfo()  {
        System.out.println("Here is the list of upcoming and past shows: " + theatre.getUpcomingShowNames()
                + theatre.getPastShowNames());
        System.out.println("Which show would you like to view?");
        String temp = scanner.nextLine();
        if (theatre.isGetTheatreShow(temp)) {
            Show show = theatre.getShow(temp);
            System.out.println("Which information would you like to view? (1) dates, (2) patron list, or "
                    + "(3) ticket price");
            String temp2 = scanner.nextLine();
            if (temp2.equals("1")) {
                System.out.println("Here are the dates for " + show.getTitle() + ": " + show.getDates());
            } else if (temp2.equals("2")) {
                System.out.println("Here are the patrons for " + show.getTitle() + ": " + show.getPatronNames());
            } else if (temp2.equals("3")) {
                System.out.println("Here is the ticket price for " + show.getTitle() + ": " + show.getTicketPrice());
            } else {
                System.out.println("That wasn't one of the options! Let's go to the main menu.");
                mainMenu();
            }
        } else {
            System.out.println("That show isn't in our records. Let's go to the main menu.");
            mainMenu();
        }

    }

    //EFFECTS: lets user choose between quitting the program or going to the main menu
    private void end() {
        System.out.println("Would you like to (1) quit or (2) go to the main menu?");
        String temp = scanner.nextLine();
        if (temp.equals("1")) {
            System.exit(1);
        } else {
            mainMenu();
        }
    }


    //EFFECTS: lets user choose which theatre information to modify
    public void theatreInfoModifying() {
        System.out.println("Which information would you like to modify? (1) shows, (2) patrons, (3) theatre info, "
                + "(4) go to main menu.");
        String temp = scanner.nextLine();
        if (temp.equals("1")) {
            changeShowInfo();
        } else if (temp.equals("2")) {
            changePatronInformation();
        } else if (temp.equals("3")) {
            changeTheatreInfo();
        } else if (temp.equals("4")) {
            mainMenu();
        } else {
            System.out.println("That wasn't one of the options! (1) try again, or (2) go to main menu");
            String temp3 = scanner.nextLine();
            if (temp3.equals("1")) {
                theatreInfoModifying();
            } else {
                mainMenu();
            }
        }

    }


    //EFFECTS: allows user to choose what theatre info they want to change
    public void changeTheatreInfo() {
        System.out.println("What theatre information would you like to change? (1) name (2) go to main menu");
        String temp = scanner.nextLine();
        if (temp.equals("1")) {
            changeTheatreName();
        } else if (temp.equals("2")) {
            mainMenu();
        } else {
            System.out.println("That wasn't one of the options! Let's try again.");
            changeTheatreInfo();
        }
    }

    //MODIFIES: theatre
    //EFFECTS: allows user to change theatre name
    public void changeTheatreName() {
        System.out.println("What would you like to change the theatre name to?");
        theatre.setName(scanner.nextLine());
        System.out.println("Theatre name is now " + theatre.getName() + "!");
    }

    //EFFECT: makes or gets a patron before selling them a ticket
    public void preTicketSale() {
        Patron patron = getPatron();
        sellTicket(patron);
    }

    //EFFECTS: finds the patron or sets up the patron account and returns it
    public Patron getPatron() {
        System.out.println("Is this patron already in the system? Type the number (1) yes or not sure, (2) no");
        String temp = scanner.nextLine();
        Patron patron = null;
        if (temp.equals("1")) {
            System.out.println("What is the patrons name?");
            String name = scanner.nextLine();
            System.out.println("What is the patrons birthday? Enter in format MMDDYY.");
            Integer birthday = parseInt(scanner.nextLine());
            if (theatre.getPatron(name, birthday) == null) {
                System.out.println("That patron is not in our system yet. Let's set up an account for them!");
                patron = theatreAddPatron();
            } else {
                System.out.println("That patron is in our system!");
                patron = theatre.getPatron(name, birthday);
            }
        } else if (temp.equals("2")) {
            patron = theatreAddPatron();
        } else {
            goBackPre();
        }
        return patron;
    }

    //EFFECTS: gives option to go to preTicketSale or mainMenu
    public void goBackPre() {
        System.out.println("That wasn't one of the options! (1) try again, or (2) go to main menu");
        String temp3 = scanner.nextLine();
        if (temp3.equals("1")) {
            preTicketSale();
        } else {
            mainMenu();
        }
    }

    //MODIFIES: theatre
    //EFFECT: lets user add a show to the theatre
    public void theatreAddShow() {
        Show show = new Show();
        theatre.addNewShow(show);

        System.out.println("Please enter the name of the show:");
        show.setTitle(scanner.nextLine());

        theatreAddDate(show);

        System.out.println("Please enter the price of the show tickets:");
        show.setTicketPrice(parseDouble(scanner.nextLine()));


        System.out.println("Would you like to add another show? "
                + "(1) 'yes' or (2) 'no'. Please type the corresponding number.");
        String temp = scanner.nextLine();
        if (temp.equals("1")) {
            theatreAddShow();
        } else if (temp.equals("2")) {
            //
        } else {
            System.out.println("That wasn't one of the options! Going to main menu.");
            mainMenu();
        }

    }

    //MODIFIES: show
    //EFFECT: adds a date to a theatre show
    public void theatreAddDate(Show show) {
        System.out.println("Please enter a date of " + show.getTitle() + " in the format MMDDYY:");
        show.addDate(scanner.nextLine());

        System.out.println("Would you like to add another date? "
                + "(1) 'yes' or (2) 'no'. Please type the corresponding number");
        String temp = scanner.nextLine();
        if (temp.equals("1")) {
            theatreAddDate(show);
        } else if (temp.equals("2")) {
            //
        } else {
            System.out.println("That wasn't one of the options! Let's go to the main menu.");
            mainMenu();
        }
    }

    //MODIFIES: show
    //EFFECT: removes a date from a theatre show
    public void theatreRemoveDate(Show show) {
        System.out.println("Please enter the date of " + show.getTitle() + " that you would like to remove "
                + "in the format MMDDYY:");
        show.removeDate(scanner.nextLine());

        System.out.println("Would you like to remove another date? "
                + "(1) 'yes' or (2) 'no'. Please type the corresponding number");
        String temp = scanner.nextLine();
        if (temp.equals("1")) {
            theatreRemoveDate(show);
        } else if (temp.equals("2")) {
            //
        } else {
            System.out.println("That wasn't one of the options! (1) try again, or (2) go to main menu");
            String temp3 = scanner.nextLine();
            if (temp3.equals("1")) {
                theatreRemoveDate(show);
            } else {
                mainMenu();
            }
        }
    }

    //MODIFIES: theatre
    //EFFECT: lets user add a patron to the theatre
    public Patron theatreAddPatron() {
        Patron patron = new Patron();
        theatre.addNewPatron(patron);

        System.out.println("What is the name of the patron?");
        patron.setName(scanner.nextLine());

        System.out.println("What is " + patron.getName() + "'s birthday? Please enter in form MMDDYY.");
        patron.setBirthday(parseInt(scanner.nextLine()));

        return patron;
    }



    //EFFECTS: sell ticket to the patron
    public void sellTicket(Patron patron) {
        System.out.println("Here are the upcoming shows:" + theatre.getUpcomingShowNames());
        System.out.println("Which show would " + patron.getName() + " like to see?");
        String temp = scanner.nextLine();
        if (theatre.isGetTheatreShow(temp)) {
            Show show = theatre.getShow(temp);

            ticketExchange(show, patron);

            System.out.println("Would " + patron.getName() + " like to buy another ticket? "
                    + "(1) yes, (2) no go to main menu");
            String temp2 = scanner.nextLine();
            if (temp2.equals("1")) {
                sellTicket(patron);
            } else if (temp2.equals("2")) {
                mainMenu();
            }  else {
                System.out.println("That wasn't one of the options. Let's go to the main menu.");
                mainMenu();
            }
        } else {
            System.out.println("That show isn't in our records. Let's go to the main menu.");
            mainMenu();
        }
    }

    //MODIFIES: show, patron
    //EFFECTS: This is where the money and ticket exchange of the sale happens and is recorded
    public void ticketExchange(Show show, Patron patron) {
        System.out.println("That show costs " + show.getTicketPrice()
                + " dollars. Will the patron buy it? (1) yes, (2) no");
        String temp = scanner.nextLine();
        if (temp.equals("1")) {
            System.out.println("Please get money and give ticket to patron. Press '1' when complete");
            scanner.nextLine();
            patron.addShow(show);
            show.addPatron(patron);
            System.out.println(patron.getName() + "'s purchase of a(n) " + show.getTitle()
                    + " ticket was successfully recorded!");
            System.out.println("Here is a list of " + patron.getName() + "'s upcoming shows:");
            System.out.println(patron.myUpcomingShowNames());

        } else {
            System.out.println("Let's go back to the main menu.");
            mainMenu();
        }

    }
}
