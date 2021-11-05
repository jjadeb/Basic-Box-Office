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

public class BoxOfficeGUI extends JFrame implements ActionListener {

    private Theatre theatre;
    private JButton loadButton;
    private JButton saveButton;
    private JButton addPatronButton;
    private JButton removePatronButton;
    private JButton finishAdd;

    //add patron labels (from FormattedTextFieldDemo.java)
    private JLabel patronNameLabel;
    private JLabel patronBirthdayLabel;
    private JLabel patronList;

    //add patron text fields
    private JFormattedTextField patronName;
    private JFormattedTextField patronBirthday;


    //Panels
    JPanel labelPane;
    JPanel fieldPane;
    JPanel patronPane;


    public BoxOfficeGUI() {
        super("Box Office");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(700, 300));
        ((JPanel) getContentPane()).setBorder(new EmptyBorder(13, 13, 13, 13));
        setLayout(new FlowLayout());
        loadButton = new JButton("Load Information");
        loadButton.setActionCommand("load");
        loadButton.addActionListener(this);

        theatre = new Theatre();
        add(loadButton);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("load")) {
            readIn();
            mainMenu();
        } else if (e.getActionCommand().equals("save")) {
            writeOut();
        } else if (e.getActionCommand().equals("addPatron")) {
            addPatron();
        } else if (e.getActionCommand().equals("removePatron")) {
            // TODO: removePatron();
        } else if (e.getActionCommand().equals("finishAdd")) {
            addPatronToTheatre();
        }
    }



    //MODIFIES: theatre
    //EFFECTS: adds the patron to the theatre
    public void addPatronToTheatre() {
        String name = patronName.getText();
        String birthday = patronBirthday.getText();
        if (name == null || birthday == null) {
            // do nothing //TODO: figure out this
        }
        if (theatre.containsPatronName(name)) {
            errorQuitMessage();
        } else {
            Patron patron = new Patron();
            patron.setName(name);
            patron.setBirthday(birthday);
            theatre.addNewPatron(patron);
        }
        removeEverything();
        pack();
        setLocationRelativeTo(null);
        setVisible(false);
        setResizable(false);
        mainMenu();

        //TODO; make robust - fails rn if no entry

    }

    //MODIFIES: this
    //EFFECT: resets the window
    public void removeEverything() {
        remove(fieldPane);
        remove(labelPane);
        remove(saveButton);
        remove(finishAdd);
        remove(patronList);
    }

    //EFFECTS: Lets user choose whether to sell a ticket or change/view theatre info
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
    }


    //EFFECTS: saves theatre data and exits
    public void writeOut() {
        JsonWriter writer = new JsonWriter("./data/theatre");
        try {
            writer.open();
        } catch (FileNotFoundException e) {
            errorQuitMessage();
        }
        writer.write(theatre);
        writer.close();
        System.exit(1);
    }

    //MODIFIES: theatre
    //EFFECTS: reads in theatre data
    public void readIn() {
        JsonReader reader = new JsonReader("./data/theatre");
        try {
            theatre = reader.read();
        } catch (IOException e) {
            errorQuitMessage();
        }
    }

    //MODIFIES: theatre
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

        add(labelPane, BorderLayout.CENTER);
        add(fieldPane, BorderLayout.LINE_END);
        add(patronPane, BorderLayout.AFTER_LINE_ENDS);

        add(finishAdd);
        add(patronList);
        remove(addPatronButton);
        remove(removePatronButton);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }

    //EFFECT: initializes labels and fields
    public void initializeLablesAndFields() {
        patronNameLabel = new JLabel("Patron Name");
        patronBirthdayLabel = new JLabel("Patron Birthday (MMDDYY)");

        finishAdd = new JButton("Done");
        finishAdd.setActionCommand("finishAdd");
        finishAdd.addActionListener(this);

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



    //EFFECT:Displays error message
    public void errorQuitMessage() {
        JLabel error = new JLabel("Error. Let's quit");
        removeAll();
        JPanel labelPane = new JPanel(new GridLayout(0,1));
        labelPane.add(error);
        add(labelPane, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
        System.exit(1);
    }

    //TODO: make remove patron button
    //TODO: image pop up thing
}
