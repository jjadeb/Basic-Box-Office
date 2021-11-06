package ui;

import model.people.Patron;
import model.theatre.Theatre;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

//Class references code from Phase 3 Description (Swing JLable text change)
//url: https://stackoverflow.com/questions/6578205/swing-jlabel-text-change-
// on-the-running-application

//Class references code from FormattedTextFieldDemo.java
//url: https://docs.oracle.com/javase/tutorial/displayCode.html?code=https://
// docs.oracle.com/javase/tutorial/uiswing/examples/components/FormattedText
// FieldDemoProject/src/components/FormattedTextFieldDemo.java

//Class references code from Oracle icon explanation
//url: https://docs.oracle.com/javase/tutorial/uiswing/components/icon.html

//Class Represents a Box office where a theatre can keep track of their patrons
public class BoxOfficeGUI extends JFrame implements ActionListener {

    private Theatre theatre;

    //buttons
    private JButton loadButton;
    private JButton saveButton;
    private JButton addPatronButton;
    private JButton removePatronButton;
    private JButton finishAdd;
    private JButton finishRemove;

    //add patron labels (from FormattedTextFieldDemo.java)
    private JLabel patronNameLabel;
    private JLabel patronBirthdayLabel;
    private JLabel patronList;
    private JLabel image;
    private JLabel errorLabel;

    //add patron text fields
    private JFormattedTextField patronName;
    private JFormattedTextField patronBirthday;


    //Panels
    JPanel labelPane;
    JPanel fieldPane;
    JPanel patronPane;

    //Dialogue
    JDialog imageBox;
    JDialog error;




    //EFFECTS: Constructor for box office. Creates windows, pop up image, and load button
    public BoxOfficeGUI() {
        super("Box Office");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(600, 300));
        ((JPanel) getContentPane()).setBorder(new EmptyBorder(13, 13, 13, 13));
        setLayout(new FlowLayout());
        loadButton = new JButton("Load Information");
        loadButton.setActionCommand("load");
        loadButton.addActionListener(this);

        imageBox = new JDialog();
        imageBox.setPreferredSize(new Dimension(250,300));
        image = new JLabel(new ImageIcon("data/Drawing.jpeg", "image"));
        imageBox.add(image);
        imageBox.setLocationRelativeTo(null);
        imageBox.isAlwaysOnTop();

        theatre = new Theatre();
        add(loadButton);
        pack();
        imageBox.pack();
        setLocationRelativeTo(null);
        imageBox.setVisible(true);
        setVisible(true);
        setResizable(false);
    }



    @Override
    //EFFECTS: calls the proper method for each button
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("load")) {
            readIn();
            mainMenu();
        } else if (e.getActionCommand().equals("save")) {
            writeOut();
        } else if (e.getActionCommand().equals("addPatron")) {
            addPatron();
        } else if (e.getActionCommand().equals("removePatron")) {
            removePatron();
        } else if (e.getActionCommand().equals("finishAdd")) {
            addPatronToTheatre();
        } else if (e.getActionCommand().equals("finishRemove")) {
            removePatronFromTheatre();
        }
    }


    //MODIFIES: theatre
    //EFFECTS: adds the patron to the theatre
    public void addPatronToTheatre() {
        String name = patronName.getText();
        String birthday = patronBirthday.getText();
        if (name.equals("") || birthday.equals("")) {
            errorGeneral("Error: no input.");
        } else if (theatre.containsPatronName(name)) {
            errorGeneral("Error: name already in system.");
        } else {
            Patron patron = new Patron();
            patron.setName(name);
            patron.setBirthday(birthday);
            theatre.addNewPatron(patron);
        }
        remove(finishAdd);
        removeEverything();
        pack();
        setLocationRelativeTo(null);
        setVisible(false);
        setResizable(false);
        mainMenu();

    }

    //MODIFIES: theatre
    //EFFECTS: remove patron from the theatre
    public void removePatronFromTheatre() {
        String name = patronName.getText();
        String birthday = patronBirthday.getText();
        if (name.equals("") || birthday.equals("")) {
            errorGeneral("Error: no input.");
        } else if (!theatre.containsPatronName(name)
                || theatre.getPatron(name,birthday) ==  null) {
            errorGeneral("Error: patron doesn't exist.");
        } else {
            Patron patron = theatre.getPatron(name, birthday);
            theatre.removePatron(patron);
        }
        remove(finishRemove);
        removeEverything();
        pack();
        setLocationRelativeTo(null);
        setVisible(false);
        setResizable(false);
        mainMenu();
    }

    //MODIFIES: this
    //EFFECT: resets the window
    public void removeEverything() {
        remove(fieldPane);
        remove(labelPane);
        remove(saveButton);
        remove(patronList);
    }


    //MODIFIES: this
    //EFFECTS: Lets user choose whether save info, add a patron, or remove one
    public void mainMenu() {
        saveButton = new JButton("Save Information and Quit");
        saveButton.setActionCommand("save");
        saveButton.addActionListener(this);

        addPatronButton = new JButton("Add a Patron");
        addPatronButton.setActionCommand("addPatron");
        addPatronButton.addActionListener(this);

        removePatronButton = new JButton("Remove a Patron");
        removePatronButton.setActionCommand("removePatron");
        removePatronButton.addActionListener(this);

        add(saveButton);
        add(addPatronButton);
        add(removePatronButton);
        remove(loadButton);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
        toFront();
    }



    //MODIFIES: this
    //EFFECTS: saves theatre data and exits
    public void writeOut() {
        JsonWriter writer = new JsonWriter("./data/theatre");
        try {
            writer.open();
            writer.write(theatre);
            writer.close();
            System.exit(1);
        } catch (FileNotFoundException e) {
            errorGeneral("File not found. Info not able to be saved.");
        }
    }


    //EFFECTS: reads in theatre data
    public void readIn() {
        JsonReader reader = new JsonReader("./data/theatre");
        try {
            theatre = reader.read();
        } catch (IOException e) {
            errorGeneral("File not found. Info not able to be loaded.");
        }
    }

    //MODIFIES: this
    //EFFECT: lets user add a patron to the theatre
    public void addPatron() {

        String patronNames = "Patrons: ";
        for (String patronN : theatre.getPatronNames()) {
            patronNames += patronN;
            patronNames += ", ";
        }
        patronList = new JLabel(patronNames);

        initializeLablesAndFields();
        initializePanes();
        patronPane.add(patronList);

        finishAdd = new JButton("Done");
        finishAdd.setActionCommand("finishAdd");
        finishAdd.addActionListener(this);

        addItems();

        add(finishAdd);

        remove(addPatronButton);
        remove(removePatronButton);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }

    //MODIFIES: this
    //EFFECT: lets user remove a patron to the theatre
    public void removePatron() {
        String patronNames = "Patrons: ";
        for (String patronN : theatre.getPatronNames()) {
            patronNames += patronN;
            patronNames += ", ";
        }
        patronList = new JLabel(patronNames);

        initializeLablesAndFields();
        initializePanes();

        patronPane.add(patronList);
        finishRemove = new JButton("Done");
        finishRemove.setActionCommand("finishRemove");
        finishRemove.addActionListener(this);

        addItems();

        add(finishRemove);

        remove(addPatronButton);
        remove(removePatronButton);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }

    //EFFECTS: adds items to the window
    private void addItems() {
        add(labelPane, BorderLayout.CENTER);
        add(fieldPane, BorderLayout.LINE_END);
        add(patronPane, BorderLayout.PAGE_END);
        add(patronList);
    }

    //EFFECT: initializes labels and fields
    public void initializeLablesAndFields() {
        patronNameLabel = new JLabel("Patron Name");
        patronBirthdayLabel = new JLabel("Patron Birthday (MMDDYY)");

        patronName = new JFormattedTextField();
        patronName.setValue("");
        patronName.setColumns(10);

        patronBirthday = new JFormattedTextField();
        patronBirthday.setValue("");
        patronBirthday.setColumns(10);

        patronNameLabel.setLabelFor(patronName);
        patronBirthdayLabel.setLabelFor(patronBirthday);
    }


    //EFFECTS: initializes panes
    public void initializePanes() {
        labelPane = new JPanel(new GridLayout(0, 1, 2, 10));
        labelPane.add(patronNameLabel);
        labelPane.add(patronBirthdayLabel);

        fieldPane = new JPanel(new GridLayout(0,1));
        fieldPane.add(patronName);
        fieldPane.add(patronBirthday);

        patronPane = new JPanel(new GridLayout(0,1));
    }



    //MODIFIES: this
    //EFFECTS: produces an error message saying there is no input
    public void errorGeneral(String string) {
        error = new JDialog();
        error.setPreferredSize(new Dimension(300,100));
        errorLabel = new JLabel(string);
        error.add(errorLabel);
        error.pack();
        error.setVisible(true);
        error.setLocationRelativeTo(null);
        error.setAlwaysOnTop(true);
    }




}
