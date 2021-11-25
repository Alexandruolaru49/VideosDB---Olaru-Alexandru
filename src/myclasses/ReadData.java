package myclasses;

//import fileio.ActorInputData;
//import fileio.MovieInputData;
//import fileio.SerialInputData;
//import fileio.UserInputData;
import fileio.*;

import java.util.ArrayList;

public class ReadData {

    //CITESC ACTORII SI II STOCHEZ IN ARRAYLIST-UL "actors"
    public ArrayList<MyActor> readActors(Input input) {
        ArrayList<MyActor> actors = new ArrayList<>();
        for (ActorInputData i : input.getActors()) {         // Parcurg lista de actori citita din JSON
            MyActor actor = new MyActor(i);                 // Instantiez un obiect de tip MyActor si apelez copy-constructorul
            actors.add(actor);                              // Stochez obiectul, adaugandu-l la lista de actori
        }
        return actors;
    }

    //CITESC FILMELE SI LE STOCHEZ IN ARRAYLIST-UL "movies"
    public ArrayList<MyMovie> readMovies(Input input) {
        ArrayList<MyMovie> movies = new ArrayList<>();
        for (MovieInputData i : input.getMovies()) {         // Parcurg lista de filme citita din JSON
            MyMovie movie = new MyMovie(i);                 // Instantiez un obiect de tip MyMovie si apelez copy-constructorul
            movies.add(movie);                              // Stochez obiectul, adaugandu-l la lista de filme
        }
        return movies;
    }

    //CITESC SERIALELE SI LE STOCHEZ IN ARRAYLIST-UL "serials"
    public ArrayList<MySerialInput> readSerials(Input input) {
        ArrayList<MySerialInput> serials = new ArrayList<>();
        for (SerialInputData i : input.getSerials()) {       // Parcurg lista de seriale citita din JSON
            MySerialInput serial = new MySerialInput(i);    // Instantiez un obiect de tip MySerialInput si apelez copy-constructorul
            serials.add(serial);                            // Stochez obiectul, adaugandu-l la lista de seriale
        }
        return serials;
    }

    //CITESC USERII SI II STOCHEZ IN ARRAYLIST-UL "users"
    public ArrayList<MyUser> readUsers(Input input){
        ArrayList<MyUser> users = new ArrayList<>();
        for (UserInputData i : input.getUsers()) {           // Parcurg lista de useri citita din JSON
            MyUser user = new MyUser(i);                    // Instantiez un obiect de tip MyUser si apelez copy-constructorul
            users.add(user);                                // Stochez obiectul, adaugandu-l la lista de useri
        }
        return  users;
    }

    public void set_Views_And_Favorites_Movies(ArrayList<MyMovie> movies, ArrayList<MyUser> users) {
        for(int k = 0; k < movies.size(); k++) {
            movies.get(k).initializeViews(users);
            movies.get(k).initializeFavorites(users);
        }
    }

    public void set_Views_And_Favorites_Serials(ArrayList<MySerialInput> serials, ArrayList<MyUser> users) {
        for(int k = 0; k < serials.size(); k++) {
            serials.get(k).initializeViews(users);
            serials.get(k).initializeFavorites(users);
        }
    }

}
