package ui;

import model.Shows.Show;
import model.Theatre.Theatre;

import java.util.Scanner;

public class BoxOffice {

    private Scanner scanner; //some structure and code taken from SimpleCalculatorSolutionLecLab
    private Theatre theatre = new Theatre();

    public BoxOffice() {
        scanner = new Scanner(System.in);
        if (theatre.getName() == "") {
            setUp();
        }
        doAction();
    }

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
        if (scanner.nextLine() == "yes") {
            theatreAddPatrons();

        }

        //setup completed
        System.out.println("Setup Complete!");

    }

    //do something with the Box Office
    public void doAction() {
    }


    //EFFECT: Asks what shows they want to add and then add them
    public void theatreAddShow() {
        Show show = new Show();
        theatre.addNewShow(show);

        System.out.println("Please enter the name of the show:");
        show.setTitle(scanner.nextLine());

        theatreAddDate(show);


        System.out.println("Would you like to add another show? Please type 'yes' or 'no'.");
        if (scanner.nextLine() == "yes") {
            theatreAddShow();
        }

    }

    public void theatreAddDate(Show show) {
        System.out.println("Please enter a date of the show in the format MMDDYY:");
        show.addDate(scanner.nextLine());

        System.out.println("Would you like to add another date? Please type 'yes' or 'no'.");
        if (scanner.nextLine().equals("yes")) {
            theatreAddDate(show);
        }
    }

    //EFFECT: Asks what patrons they want to add and then add them
    public void theatreAddPatrons() {}


}
