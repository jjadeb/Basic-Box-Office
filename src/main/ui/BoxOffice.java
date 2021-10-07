package ui;

import model.People.Patron;
import model.Shows.Show;
import model.Shows.ShowList;
import model.Theatre.Theatre;

import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class BoxOffice {

    private Scanner scanner; //some structure and code taken from SimpleCalculatorSolutionLecLab
    private Theatre theatre = new Theatre();

    public BoxOffice() {
        scanner = new Scanner(System.in);
        if (theatre.getName() == "") {
            setUp();
        }
        System.out.println("Would you like to complete any further actions? If not, type 'quit'.");
        if (!scanner.nextLine().equals("quit")) {
            doAction();
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
                +  " Type 'yes' or 'no'.");
        String temp = scanner.nextLine(); //simp calc code
        if (temp.equals("yes")) {
            theatreAddShow();
        }
        //add patrons
        System.out.println("Do you have any patrons you would like to add to the theatre records?"
                +  " Type 'yes' or 'no'.");
        if (scanner.nextLine().equals("yes")) {
            theatreAddPatron();

        }

        //setup completed
        System.out.println("Setup Complete!");

    }

    //do something with the Box Office
    public void doAction() {
        System.out.println("(1) Selling a Ticket, (2) Theatre Information, or "
                + "(3) Patron Information? please type the corresponding number.");
        if (scanner.nextLine().equals("1")) {
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
            }
        } else {
            System.out.println("Incorrect");
        }
    }


    //MODIFIES: this
    //EFFECT: Asks what shows they want to add and then add them
    public void theatreAddShow() {
        Show show = new Show();
        theatre.addNewShow(show);

        System.out.println("Please enter the name of the show:");
        show.setTitle(scanner.nextLine());

        theatreAddDate(show);

        System.out.println("Please enter the price of the show tickets:");
        show.setTicketPrice(parseInt(scanner.nextLine()));


        System.out.println("Would you like to add another show? Please type 'yes' or 'no'.");
        if (scanner.nextLine().equals("yes")) {
            theatreAddShow();
        }

    }

    public void theatreAddDate(Show show) {
        System.out.println("Please enter a date of " + show.getTitle() + " in the format MMDDYY:");
        show.addDate(scanner.nextLine());

        System.out.println("Would you like to add another date? Please type 'yes' or 'no'.");
        if (scanner.nextLine().equals("yes")) {
            theatreAddDate(show);
        }
    }

    //MODIFIES: this
    //EFFECT: asks what patrons they want to add and then add them
    public void theatreAddPatron() {
        Patron patron = new Patron();
        theatre.addNewPatron(patron);

        System.out.println("What is the name of the patron?");
        patron.setName(scanner.nextLine());

        System.out.println("What is " + patron.getName() + "'s birthday?");
        patron.setBirthday(parseInt(scanner.nextLine()));

        System.out.println("Would " + patron.getName() + " like to buy a ticket now?");
        System.out.println("If yes, type 'yes', if no type 'no'.");
        String temp = scanner.nextLine();
        if (temp.equals("yes")) {
            sellTicket(patron);
        } else if (temp.equals("no")) {
            System.out.println("Alrighty matey! Maybe next time.");
        } else {
            System.out.println("That wasn't one of the options!");
        }

    }

    //EFFECT: Sell ticket to the patron
    public void sellTicket(Patron patron) {
        System.out.println("Here are the upcoming shows:" + theatre.getUpcomingShowNames());
        System.out.println("Which show would " + patron.getName() + " like to see?");
        String temp = scanner.nextLine();
        for (Show show: theatre.getUpcomingShows()) {
            if (temp.equals(show.getTitle())) {
                System.out.println("That show costs " + show.getTicketPrice()
                        + " dollars. Will the patron buy it? yes/no");
                if (scanner.nextLine().equals("yes")) {
                    patron.addShow(show);
                    System.out.println(patron.getName() + "'s purchase of a(n) " + show.getTitle()
                            + " ticket was successfully recorded!");
                }
            }
        }
        System.out.println("Would " + patron.getName() + " like to buy another ticket? Please type 'yes' or 'no'.");
        String temp2 = scanner.nextLine();
        if (temp2.equals("yes")) {
            sellTicket(patron);
        } else  {
            System.out.println("That's plenty for today!");
        }
        System.out.println("Here is a list of " + patron.getName() + "'s upcoming shows:");
        System.out.println(patron.myUpcomingShowNames());





    }



}
