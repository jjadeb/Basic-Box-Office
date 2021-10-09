package ui;

import model.people.Patron;
import model.shows.Show;
import model.theatre.Theatre;

import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class BoxOffice {

    private Scanner scanner; //some structure and code taken from SimpleCalculatorSolutionLecLab
    private Theatre theatre = new Theatre();

    public BoxOffice() {
        scanner = new Scanner(System.in);
        if (theatre.getName().equals("")) {
            setUp();
        }
        mainMenu();
    }


    //MODIFIES: this
    //EFFECT: Sets up the theatre information
    public void setUp() {
        System.out.println("Welcome to the Box Office!");
        System.out.println("First, set up the information for your theatre.");

        //set name
        System.out.println("Please enter the name of your theatre:");
        theatre.setName(scanner.nextLine());

        //setup completed
        System.out.println("Setup Complete!");

    }

    //do something with the Box Office
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

    //MODIFIES: Theatre
    //EFFECTS: lets user choose whether they want to view or change information
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

    //EFFECTS: lets user view theatre information
    public void theatreInfoViewing() {
        System.out.println("What theatre information would you like to view?");
        System.out.println("(1) upcoming theatre shows, (2) past theatre shows, (3) theatre patrons, "
                + "(4) theatre name, (5) a show, (6) a patron, (7) go to main menu");
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
            mainMenu();
        } else {
            System.out.println("That wasn't one of the options! Let's try again.");
            theatreInfoViewing();
        }
    }

    //MODIFIES: Theatre
    //EFFECTS: Retrieves different information relating to a patron
    public void viewPatronInformation() {
        System.out.println("What is the patrons name?");
        String name = scanner.nextLine();
        System.out.println("What is the patrons birthday?");
        Integer birthday = parseInt(scanner.nextLine());
        Patron patron = theatre.getPatron(name, birthday);

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

    //MODIFIES: patronList, patron
    //EFFECTS: Changes different information relating to a patron
    public void changePatronInformation() {
        //
    }

    //MODIFIES: showList, show
    //EFFECTS: Changes different information relating to a patron
    public void changeShowInfo() {
        //
    }

    //EFFECTS: lets user see information pertaining to a specific show
    public void viewShowInfo()  {
        System.out.println("Which show would you like to view?");
        String temp = scanner.nextLine();
        Show show = theatre.getShow(temp);
        System.out.println("Which information would you like to view? (1) dates, (2) patron list, or (3) price");
        String temp2 = scanner.nextLine();
        if (temp2.equals("1")) {
            System.out.println("Here are the dates for " + show.getTitle() + ": " + show.getDates());
        } else if (temp2.equals("2")) {
            System.out.println("Here are the patrons for " + show.getTitle() + ": " + show.getPatronNames());
        } else if (temp2.equals("3")) {
            System.out.println("Here is the ticket price for " + show.getTitle() + ": " + show.getTicketPrice());
        } else {
            System.out.println("That wasn't one of the options! (1) try again, or (2) go to main menu");
            String temp3 = scanner.nextLine();
            if (temp3.equals("1")) {
                viewShowInfo();
            } else {
                mainMenu();
            }
        }
    }

    private void end() {
        System.out.println("Would you like to (1) quit or (2) go to the main menu?");
        String temp = scanner.nextLine();
        if (temp.equals("1")) {
            System.exit(1);
        } else {
            mainMenu();
        }
    }


    //EFFECTS: lets user modify theatre information
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

    //MODIFIES: theatre
    //EFFECTS: allows user to change info pertaining to the theatre
    public void changeTheatreInfo() {
        System.out.println("What theatre information would you like to change? (1) name");
        String temp = scanner.nextLine();
    }

    //EFFECT: Does pre-ticket sale setup
    public void preTicketSale() {
        System.out.println("Is this patron already in the system? Type the number (1) yes or not sure, (2) no");
        String temp = scanner.nextLine();
        if (temp.equals("1")) {
            System.out.println("What is the patrons name?");
            String name = scanner.nextLine();
            System.out.println("What is the patrons birthday?");
            Integer birthday = parseInt(scanner.nextLine());
            if (theatre.getPatron(name, birthday) == null) {
                System.out.println("That patron is not in our system yet. Let's set up an account for them!");
                theatreAddPatron();
            } else {
                System.out.println("That patron is in our system! Let's get them a ticket.");
                sellTicket(theatre.getPatron(name, birthday));
            }
        } else if (temp.equals("2")) {
            theatreAddPatron();
        } else {
            goBackPre();
        }
    }

    //EFFECTS: Gives option to go to preTicketSale or mainMenu
    public void goBackPre() {
        System.out.println("That wasn't one of the options! (1) try again, or (2) go to main menu");
        String temp3 = scanner.nextLine();
        if (temp3.equals("1")) {
            preTicketSale();
        } else {
            mainMenu();
        }
    }

    //MODIFIES: Theatre
    //EFFECT: Asks what shows they want to add and then add them
    public void theatreAddShow() {
        Show show = new Show();
        theatre.addNewShow(show);

        System.out.println("Please enter the name of the show:");
        show.setTitle(scanner.nextLine());

        theatreAddDate(show);

        System.out.println("Please enter the price of the show tickets:");
        show.setTicketPrice(parseInt(scanner.nextLine()));


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
            System.out.println("That wasn't one of the options! (1) try again, or (2) go to main menu");
            String temp3 = scanner.nextLine();
            if (temp3.equals("1")) {
                theatreAddDate(show);
            } else {
                mainMenu();
            }
        }
    }

    //MODIFIES: theatre
    //EFFECT: asks what patrons they want to add and then add them
    public void theatreAddPatron() {
        Patron patron = new Patron();
        theatre.addNewPatron(patron);

        System.out.println("What is the name of the patron?");
        patron.setName(scanner.nextLine());

        System.out.println("What is " + patron.getName() + "'s birthday?");
        patron.setBirthday(parseInt(scanner.nextLine()));

        System.out.println("Would " + patron.getName() + " like to buy a ticket now?");
        System.out.println("(1) 'yes' or (2) 'no'. Please type the corresponding number");
        String temp = scanner.nextLine();
        if (temp.equals("1")) {
            sellTicket(patron);
        } else if (temp.equals("2")) {
            System.out.println("Alrighty matey! Maybe next time.");
        } else {
            System.out.println("That wasn't one of the options! (1) try again, or (2) go to main menu");
            String temp3 = scanner.nextLine();
            if (temp3.equals("1")) {
                theatreAddPatron();
            } else {
                mainMenu();
            }
        }

    }



    //MODIFIES: show, patron
    //EFFECT: Sell ticket to the patron
    public void sellTicket(Patron patron) {
        System.out.println("Here are the upcoming shows:" + theatre.getUpcomingShowNames());
        System.out.println("Which show would " + patron.getName() + " like to see?");
        String temp = scanner.nextLine();
        Show show = theatre.getShow(temp);

        ticketExchange(show, patron);

        System.out.println("Would " + patron.getName() + " like to buy another ticket? "
                + "(1) 'yes', (2) 'no go to main menu', or (3) quit.");
        String temp2 = scanner.nextLine();
        if (temp2.equals("1")) {
            sellTicket(patron);
        } else if (temp2.equals("2")) {
            mainMenu();
        } else if (temp2.equals("3")) {
            System.exit(1);
        } else {
            System.out.println("That wasn't one of the options. Let's go to the main menu.");
            mainMenu();
        }
    }

    //MODIFIES: show, patron
    //EFFECTS: This is where the money and ticket exchange happens and is recorded
    public void ticketExchange(Show show, Patron patron) {
        System.out.println("That show costs " + show.getTicketPrice()
                + " dollars. Will the patron buy it? (1) yes, (2) no");
        String temp = scanner.nextLine();
        if (temp.equals("yes")) {
            System.out.println("Please get money and give ticket to patron. Press '1' when complete");
            scanner.nextLine();
            patron.addShow(show);
            show.addPatron(patron);
            System.out.println(patron.getName() + "'s purchase of a(n) " + show.getTitle()
                    + " ticket was successfully recorded!");
            System.out.println("Here is a list of " + patron.getName() + "'s upcoming shows:");
            System.out.println(patron.myUpcomingShowNames());

        }

    }
}
