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
        System.out.println("Would you like to go to the main menu? If not, type 'quit'.");
        if (!scanner.nextLine().equals("quit")) {
            mainMenu();
        }
    }

    //MODIFIES: this
    //EFFECT: Sets up the theatre information
    public void setUp() {
        System.out.println("Welcome to the Box Office!");
        System.out.println("First, set up the information for your theatre.");

        //set name
        System.out.println("Please enter the name of your theatre:");
        theatre.setName(scanner.nextLine());

        //add shows
        System.out.println("Do you have any shows you would like to add to your upcoming show list?"
                + " (1) 'yes' or (2) 'no'. Please type the corresponding number");
        String temp = scanner.nextLine(); //simp calc code
        if (temp.equals("1")) {
            theatreAddShow();
        }
        //add patrons
        System.out.println("Do you have any patrons you would like to add to the theatre records?"
                + " (1) 'yes' or (2) 'no'. Please type the corresponding number");
        if (scanner.nextLine().equals("1")) {
            theatreAddPatron();

        }

        //setup completed
        System.out.println("Setup Complete!");

    }

    //do something with the Box Office
    public void mainMenu() {
        System.out.println("(1) Sell a Ticket, (2) Modify/View Theatre Information, "
                + "(3) Modify/View Patron Information, or (4) quit? please type the corresponding number.");
        String response = scanner.nextLine();
        if (response.equals("1")) {
            preTicketSale();
        } else if (response.equals("2")) {
            theatreInformation();
        } else if (response.equals("3")) {
            patronInformation();
        } else if (response.equals("4")) {
            System.exit(1);
        } else {
            System.out.println("That wasn't one of the options! Let's see them again.");
            mainMenu();
        }
    }

    //MODIFIES: Theatre
    //EFFECTS: Changes or retrieves different information relating to the theatre
    public void theatreInformation() {
        System.out.println("Would you like to (1) view theatre information, (2) modify theatre information, "
                + "or (3) go back to the main menu?");
    }

    //MODIFIES: Theatre
    //EFFECTS: Changes or retrieves different information relating to a patron
    public void patronInformation() {
        //
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
            System.out.println("That was not one of the options. Let's go back!");
            preTicketSale();
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
        if (scanner.nextLine().equals("1")) {
            theatreAddShow();
        }

    }

    //MODIFIES: show
    //EFFECT: adds a date to a theatre show
    public void theatreAddDate(Show show) {
        System.out.println("Please enter a date of " + show.getTitle() + " in the format MMDDYY:");
        show.addDate(scanner.nextLine());

        System.out.println("Would you like to add another date? "
                + "(1) 'yes' or (2) 'no'. Please type the corresponding number");
        if (scanner.nextLine().equals("1")) {
            theatreAddDate(show);
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
            System.out.println("That wasn't one of the options!");
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
