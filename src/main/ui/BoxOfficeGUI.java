package ui;

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

//Class references code from Phase 3 Description
public class BoxOfficeGUI extends JFrame implements ActionListener {

    private Theatre theatre;
    private JButton loadButton;

    public BoxOfficeGUI() {
        super("Box Office");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(400, 200));
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
            //editShowPatrons();
        }
    }

    //EFFECTS: Lets user choose whether to sell a ticket or change/view theatre info
    public void mainMenu() {
        JButton saveButton = new JButton("Save Information and Quit");
        saveButton.setActionCommand("save");
        saveButton.addActionListener(this);

        JButton editShowPatronsButton = new JButton("Add a Show");
        editShowPatronsButton.setActionCommand("addShow");
        editShowPatronsButton.addActionListener(this);

        add(saveButton);
        add(editShowPatronsButton);
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
            System.out.println("Information not able to be saved");;
        }
        writer.write(theatre);
        writer.close();
        System.out.println("Information has been saved!");
        System.exit(1);
    }

    //MODIFIES: theatre
    //EFFECTS: reads in theatre data
    public void readIn() {
        JsonReader reader = new JsonReader("./data/theatre");
        try {
            theatre = reader.read();
        } catch (IOException e) {
            System.out.println("Information not able to be loaded.");
        }
    }
}
