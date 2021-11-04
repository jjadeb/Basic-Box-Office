package ui;

import com.sun.javafx.binding.StringFormatter;
import model.shows.Show;
import model.theatre.Theatre;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.NumberFormat;

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
    private JButton addShowButton;
    private JButton removeShowButton;
    private JButton finishAdd;

    //add show labels (from FormattedTextFieldDemo.java)
    private JLabel showNameLabel;
    private JLabel showPriceLabel;
    private JLabel addShowDateLabel;
    private JLabel showList;

    //add show text fields
    private JFormattedTextField showName;
    private JFormattedTextField showPrice;
    private JFormattedTextField showDate;

    //Formats to format
    private NumberFormat amountFormat;
    private StringFormatter stringFormatter;

    //Panels
    JPanel labelPane;
    JPanel fieldPane;


    public BoxOfficeGUI() {
        super("Box Office");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(500, 300));
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
        } else if (e.getActionCommand().equals("addShow")) {
            addShow();
        } else if (e.getActionCommand().equals("removeShow")) {
            // TODO: removeShow();
        } else if (e.getActionCommand().equals("finishAdd")) {
            addShowToTheatre();
        }
    }

    //MODIFIES: theatre
    //EFFECTS: adds the show to the theatre
    public void addShowToTheatre() {
        Show show = new Show();
        String name = showName.getText();
        if (theatre.containsShowName(name)) {
            errorMessage();
        } else {
            show.setTitle(name);
            show.setTicketPrice(Double.parseDouble(showPrice.getText()));
            show.addDate(showDate.getText());
            theatre.addNewShow(show);
        }
        removeEverything();
        pack();
        setLocationRelativeTo(null);
        setVisible(false);
        setResizable(false);
        mainMenu();

        //TODO: add more dates button
        //TODO; make robust - fails rn if no entry

    }

    //MODIFIES: this
    //EFFECT: resets the window
    public void removeEverything() {
        remove(fieldPane);
        remove(labelPane);
        remove(saveButton);
        remove(finishAdd);
        remove(showList);
    }

    //EFFECTS: Lets user choose whether to sell a ticket or change/view theatre info
    public void mainMenu() {
        saveButton = new JButton("Save Information and Quit");
        saveButton.setActionCommand("save");
        saveButton.addActionListener(this);

        addShowButton = new JButton("Add a Show");
        addShowButton.setActionCommand("addShow");
        addShowButton.addActionListener(this);

        removeShowButton = new JButton("Remove a Show");
        removeShowButton.setActionCommand("removeShow");
        removeShowButton.addActionListener(this);

        add(saveButton);
        add(addShowButton);
        add(removeShowButton);
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
            errorMessage();
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
            errorMessage();
        }
    }

    //MODIFIES: theatre
    //EFFECT: lets user add a show to the theatre
    public void addShow() {

        String allShowNames = "Upcoming Shows: ";
        for (String showN : theatre.getUpcomingShowNames()) {
            allShowNames += showN;
            allShowNames += ", ";
        }



        showList = new JLabel(allShowNames);

        showNameLabel = new JLabel("Show Name");
        showPriceLabel = new JLabel("Show Price");
        addShowDateLabel = new JLabel("Add Show Date");

        finishAdd = new JButton("Done");
        finishAdd.setActionCommand("finishAdd");
        finishAdd.addActionListener(this);

        showName = new JFormattedTextField(stringFormatter);
        showName.setValue("");
        showName.setColumns(10);
        //showName.addPropertyChangeListener("name", this);

        showPrice = new JFormattedTextField(amountFormat);
        showPrice.setValue("");
        showPrice.setColumns(10);
        //showPrice.addPropertyChangeListener("price", this);

        showDate = new JFormattedTextField(stringFormatter);
        showDate.setValue("");
        showDate.setColumns(10);
        //showDate.addPropertyChangeListener("data", this);

        showNameLabel.setLabelFor(showName);
        showPriceLabel.setLabelFor(showPrice);
        addShowDateLabel.setLabelFor(showDate);

        labelPane = new JPanel(new GridLayout(3,1));
        labelPane.add(showNameLabel);
        labelPane.add(showPriceLabel);
        labelPane.add(addShowDateLabel);


        fieldPane = new JPanel(new GridLayout(0,1));
        fieldPane.add(showName);
        fieldPane.add(showPrice);
        fieldPane.add(showDate);

        add(labelPane, BorderLayout.CENTER);
        add(fieldPane, BorderLayout.LINE_END);

        add(finishAdd);
        add(showList);
        remove(addShowButton);
        remove(removeShowButton);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }

//    @Override
//    public void propertyChange(PropertyChangeEvent evt) {
//       // Object source = evt.getSource();
//        //code referece: https://docs.oracle.com/javase/tutorial/uiswing/events/propertychangelistener.html
//        String propertyName = evt.getPropertyName();
//        Show show = new Show();
//        theatre.addNewShow(show);
//        if (propertyName.equals(showName)) {
//            String name = showName.getValue().toString();
//            if (theatre.containsShowName(name)) {
//                errorMessage();
//            } else {
//                show.setTitle(name);
//            }
//        } else if (propertyName.equals(showPrice)) {
//            show.setTicketPrice((Double) showPrice.getValue());
//        } else if (propertyName.equals(showDate)) {
//            show.addDate(showDate.getValue().toString());
//        }
//    }

    //EFFECT:Displays error message
    public void errorMessage() {
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

    //TODO: set up formats
    //TODO: add more dates
    // TODO: make method shorter
    //TODO: image pop up thing
}
