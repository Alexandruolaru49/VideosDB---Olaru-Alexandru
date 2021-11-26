package myclasses.MyRead;

import fileio.Input;
import fileio.ActorInputData;
import fileio.MovieInputData;
import fileio.SerialInputData;
import fileio.UserInputData;
import myclasses.CopyClasses.MyMovie;
import myclasses.CopyClasses.MySerialInput;
import myclasses.CopyClasses.MyActor;
import myclasses.CopyClasses.MyUser;
import java.util.ArrayList;

public class ReadData {

    /**
     * Metoda care citeste actorii din database si ii stocheaza intr-o lista
     * de tipul clasei proprii create.Parcurg lista de actori citita din JSON,
     * instantiez un obiect de tip MyActor si apelez copy-constructorul si
     * stochez obiectul, adaugandu-l la lista de actori.
     * @param input
     * variabila de tip Input, de unde fac citirea
     */
    public ArrayList<MyActor> readActors(final Input input) {
        ArrayList<MyActor> actors = new ArrayList<>();
        for (ActorInputData i : input.getActors()) {
            MyActor actor = new MyActor(i);
            actors.add(actor);
        }
        return actors;
    }

    /**
     * Metoda care citeste filmele din database si le stocheaza intr-o lista
     * de tipul clasei proprii create.Parcurg lista de filme citita din JSON,
     * instantiez un obiect de tip MyMovie si apelez copy-constructorul si
     * stochez obiectul, adaugandu-l la lista de filme.
     * @param input
     * variabila de tip Input, de unde fac citirea
     */
    public ArrayList<MyMovie> readMovies(final Input input) {
        ArrayList<MyMovie> movies = new ArrayList<>();
        for (MovieInputData i : input.getMovies()) {
            MyMovie movie = new MyMovie(i);
            movies.add(movie);
        }
        return movies;
    }

    /**
     * Metoda care citeste serialele din database si le stocheaza intr-o lista
     * de tipul clasei proprii create.Parcurg lista de seriale citita din JSON,
     * instantiez un obiect de tip MySerialInput si apelez copy-constructorul si
     * stochez obiectul, adaugandu-l la lista de seriale.
     * @param input
     * variabila de tip Input, de unde fac citirea
     */
    public ArrayList<MySerialInput> readSerials(final Input input) {
        ArrayList<MySerialInput> serials = new ArrayList<>();
        for (SerialInputData i : input.getSerials()) {
            MySerialInput serial = new MySerialInput(i);
            serials.add(serial);
        }
        return serials;
    }

    /**
     * Metoda care citeste utilizatorii din database si ii stocheaza intr-o lista
     * de tipul clasei proprii create.Parcurg lista de utilizatori citita din JSON,
     * instantiez un obiect de tip MyUser si apelez copy-constructorul si
     * stochez obiectul, adaugandu-l la lista de utilizatori.
     * @param input
     * variabila de tip Input, de unde fac citirea
     */
    public ArrayList<MyUser> readUsers(final Input input) {
        ArrayList<MyUser> users = new ArrayList<>();
        for (UserInputData i : input.getUsers()) {
            MyUser user = new MyUser(i);
            users.add(user);
        }
        return  users;
    }

    /**
     * Metoda care initializeaza numarul de vizualizari si de cate ori au fost
     * adaugate la favorite toate filmele, apelandu-se metodele unui film de
     * a face acest lucru.
     * @param movies
     * lista de filme din database
     * @param users
     * lista de utilizatori din database
     */
    public void setViewsAndFavoritesMovies(final ArrayList<MyMovie> movies,
                                               final ArrayList<MyUser> users) {

        for (int k = 0; k < movies.size(); k++) {
            movies.get(k).initializeViews(users);
            movies.get(k).initializeFavorites(users);
        }
    }

    /**
     * Metoda care initializeaza numarul de vizualizari si de cate ori au fost
     * adaugate la favorite toate serialele, apelandu-se metodele unui serial
     * de a face acest lucru.
     * @param serials
     * lista de seriale din database
     * @param users
     * lista de utilizatori din database
     */
    public void setViewsAndFavoritesSerials(final ArrayList<MySerialInput> serials,
                                                final ArrayList<MyUser> users) {

        for (int k = 0; k < serials.size(); k++) {
            serials.get(k).initializeViews(users);
            serials.get(k).initializeFavorites(users);
        }
    }

}
