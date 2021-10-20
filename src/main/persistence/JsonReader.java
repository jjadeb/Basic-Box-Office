package persistence;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Stream;

import model.people.Patron;
import model.shows.Show;
import model.theatre.Theatre;
import org.json.*;

//This class copies code from JsonSerializationDemo
//url: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
// Represents a reader that reads workroom from JSON data stored in file
public class JsonReader {

    private final String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads workroom from file and returns it;
    // throws IOException if an error occurs reading data from file
    public Theatre read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseTheatre(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses theatre from JSON object and returns it
    private Theatre parseTheatre(JSONObject jsonObject) {
        Theatre theatre = new Theatre();
        addName(theatre, jsonObject);
        addUpcomingShows(theatre, jsonObject);
        addPastShows(theatre, jsonObject);
        addPatrons(theatre, jsonObject);
        return theatre;
    }

    // MODIFIES: theatre
    // EFFECTS: parses patrons from JSON object and adds them to theatre
    private void addPatrons(Theatre theatre, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("patrons");
        for (Object json : jsonArray) {
            JSONObject nextPatron = (JSONObject) json;
            addPatron(theatre, nextPatron);
        }
    }

    // MODIFIES: theatre
    // EFFECTS: parses patron from JSON object and adds it to theatre
    private void addPatron(Theatre theatre, JSONObject jsonObject) {
        Patron patron = new Patron();

        String name = jsonObject.getString("name");
        patron.setName(name);

        String birthday = jsonObject.getString("birthday");
        patron.setBirthday(birthday);

        JSONArray jsonArray = jsonObject.getJSONArray("patronUpcomingShows");
        for (Object json : jsonArray) {
            JSONObject nextShow = (JSONObject) json;
            addUpcomingShowPatron(patron, nextShow, theatre);
        }
        JSONArray jsonArray2 = jsonObject.getJSONArray("patronPastShows");
        for (Object json : jsonArray2) {
            JSONObject nextShow = (JSONObject) json;
            addPastShowPatron(patron, nextShow, theatre);
        }
        theatre.addNewPatron(patron);
    }




    //MODIFIES: patron, show
    //EFFECTS: parses upcoming show from JSON object and adds it to patron, adds patron to show
    private void addUpcomingShowPatron(Patron patron, JSONObject jsonObject, Theatre theatre) {
        String name = jsonObject.getString("show");
        Show show = theatre.getShow(name);
        patron.addShow(show);
        show.addPatron(patron);
    }

    //MODIFIES: patron, show
    //EFFECTS: parses past show from JSON object and adds it to patron,
    private void addPastShowPatron(Patron patron, JSONObject jsonObject, Theatre theatre) {
        String name = jsonObject.getString("show");
        Show show = theatre.getShow(name);
        patron.addShow(show);
        show.addPatron(patron);
        patron.getMyShows().archive(show);
    }

    // MODIFIES: show
    // EFFECTS: parses date from JSON object and adds it to show
    private void addShowDate(Show show, JSONObject jsonObject) {
        String date = jsonObject.getString("date");
        show.addDate(date);
    }




    // MODIFIES: theatre
    // EFFECTS: parses shows from JSON object and adds them to theatre
    private void addUpcomingShows(Theatre theatre, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("upcoming");
        for (Object json : jsonArray) {
            JSONObject nextShow = (JSONObject) json;
            addUpcomingShow(theatre, nextShow);
        }
    }

    // MODIFIES: theatre
    // EFFECTS: parses shows from JSON object and adds them to theatre
    private void addPastShows(Theatre theatre, JSONObject jsonObject) {
        JSONArray jsonArray2 = jsonObject.getJSONArray("past");
        for (Object json : jsonArray2) {
            JSONObject nextShow = (JSONObject) json;
            addPastShow(theatre, nextShow);
        }
    }

    // MODIFIES: theatre
    // EFFECTS: parses show from JSON object and adds it to theatre
    private void addUpcomingShow(Theatre theatre, JSONObject jsonObject) {
        Show show = new Show();
        String name = jsonObject.getString("title");
        show.setTitle(name);
        Double price = jsonObject.getDouble("ticketPrice");
        show.setTicketPrice(price);

        JSONArray jsonArray = jsonObject.getJSONArray("dates");
        for (Object json : jsonArray) {
            JSONObject nextDate = (JSONObject) json;
            addShowDate(show, nextDate);
        }
       //addPatronShow adds the patron to the show, so we don't need to do it here

        theatre.addNewShow(show);
    }

    // MODIFIES: theatre
    // EFFECTS: parses show from JSON object and adds it to theatre
    private void addPastShow(Theatre theatre, JSONObject jsonObject) {
        Show show = new Show();
        String name = jsonObject.getString("title");
        show.setTitle(name);
        Double price = jsonObject.getDouble("ticketPrice");
        show.setTicketPrice(price);

        JSONArray jsonArray = jsonObject.getJSONArray("dates");
        for (Object json : jsonArray) {
            JSONObject nextDate = (JSONObject) json;
            addShowDate(show, nextDate);
        }
        //addPatronShow adds the patron to the show, so we don't need to do it here

        theatre.addNewShow(show);
        theatre.archiveShow(show);
    }

    // MODIFIES: theatre
    // EFFECTS: parses name from JSON object and adds it to theatre
    private void addName(Theatre theatre, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        theatre.setName(name);
    }
}


