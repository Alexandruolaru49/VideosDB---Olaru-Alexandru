package myclasses.Commands;
import myclasses.MyMovie;
import myclasses.MySerialInput;
import myclasses.MyUser;
import myclasses.MyShowInput;

import java.util.ArrayList;



public class Command {

    /**
     * Metoda ce implementeaza comanda "favorite"
     * * Se verifica daca titlul pentru care se face adaugarea in lista de favorite a utilizatorului
     * este al unui film sau al unui serial pentru a fi cautat in lista potrivita, apoi se
     * apeleaza metoda utilizatorului de a adauga la favorite.
     * @param users - lista utilizatorilor din database
     * @param movies - lista filmelor din database
     * @param serials - lista serialelor din database
     * @param title - titlul videoclipului ce se doreste adaugat la favorite
     * @param contor - pozitia in lista de utilizatori a utilizatorului pentru care se face comanda
     */
    public String favorite(final ArrayList<MyUser> users,
                           final int contor, final String title,
                           final ArrayList<MyMovie> movies,
                           final ArrayList<MySerialInput> serials) {

        String message;

        if (users.get(contor).isFavorite(title)) {
            message = "error -> " + title + " is already in favourite list";
            return message;
        } else {
            if (!users.get(contor).wasWatched(title)) {
                message = "error -> " + title + " is not seen";
                return message;
            } else {
                message = "success -> " + title + " was added as favourite";
                int isMovie = 0, isSerial = 0;
                for (int i = 0; i < movies.size(); i++) {
                    if (movies.get(i).getTitle().equals(title)) {
                        isMovie = 1;
                    }
                }
                for (int i = 0; i < serials.size(); i++) {
                    if (serials.get(i).getTitle().equals(title)) {
                        isSerial = 1;
                    }
                }

                if (isMovie == 1) {
                    MyMovie film = movies.get(0);
                    for (int j = 0; j < movies.size(); j++) {
                        if (movies.get(j).getTitle().equals(title)) {
                            film = movies.get(j);
                        }
                    }
                    users.get(contor).addToFavorites(title, film);
                }
                if (isSerial == 1) {
                    MySerialInput serial = serials.get(0);
                    for (int j = 0; j < serials.size(); j++) {
                        if (serials.get(j).getTitle().equals(title)) {
                            serial = serials.get(j);
                        }
                    }
                    users.get(contor).addToFavorites(title, serial);
                }

                return message;
            }
        }
    }


    /**
     * Metoda ce implementeaza comanda "view"
     * Se verifica daca videoclipul ce doreste a fi vizionat este serial sau film, apelandu-se,
     * mai apoi, metoda utilizatorului de a viziona un clip.
     * @param users
     * lista utilizatorilor din database
     * @param movies
     * lista filmelor din database
     * @param serials
     * lista serialelor din database
     * @param title
     * titlul videoclipului ce se doreste a fi vizionat
     * @param contor
     * pozitia in lista de utilizatori a utilizatorului pentru care se face comanda
     */
    public String view(final ArrayList<MyUser> users,
                       final int contor, final String title,
                       final ArrayList<MyMovie> movies,
                       final ArrayList<MySerialInput> serials) {

        String message = "";

        MyShowInput show = movies.get(0);
        for (int j = 0; j < movies.size(); j++) {
            if (movies.get(j).getTitle().equals(title)) {
                show = movies.get(j);
            }
        }
        for (int j = 0; j < serials.size(); j++) {
            if (serials.get(j).getTitle().equals(title)) {
                show = serials.get(j);
            }
        }

        int nr = users.get(contor).view(title, show);
        message = "success -> " + title + " was viewed with total views of " + nr;

        return message;
    }

    /**
     * Metoda ce implementeaza comanda "rating"
     * Se verifica daca videoclipul este film sau serial, iar daca nu a fost nici vizionat
     * si nici nu i-a fost data o nota pana acum, se realizeaza comanda, apelandu-se
     * metoda utilizatorului de a da o nota unui film/serial.
     * @param users
     * lista utilizatorilor din database
     * @param movies
     * lista filmelor din database
     * @param serials
     * lista serialelor din database
     * @param title
     * titlul videoclipului ce se doreste a fi vizionat
     * @param contor
     * pozitia in lista de utilizatori a utilizatorului pentru care se face comanda
     * @param seasonNumber
     * numarul sezonului pentru care se ofera rating
     * @param name
     * string ce reprezinta numele de utilizator
     * @param grade
     * nota ce ii este data unui videoclip
     */
    public String rating(final ArrayList<MyUser> users,
                         final int contor, final String title,
                         final String name, final Double grade,
                         final int seasonNumber, final ArrayList<MyMovie> movies,
                         final ArrayList<MySerialInput> serials) {

        String message = "";
        int j;

        for (j = 0; j < movies.size(); j++) {
            if (movies.get(j).getTitle().equals(title)) {

                if (users.get(contor).isRated(title)) {
                    message = "error -> " + title + " has been already rated";
                    return message;
                }

                if (!users.get(contor).wasWatched(title)) {
                    message = "error -> " + title + " is not seen";
                    return message;
                } else {
                    users.get(contor).rateMovie(grade, movies.get(j));
                    message = "success -> " + title + " was rated with " + grade + " by " + name;
                    return message;
                }
            }
        }
        for (j = 0; j < serials.size(); j++) {
            if (serials.get(j).getTitle().equals(title)) {

                if (users.get(contor).isRated(title + seasonNumber)) {
                    message = "error -> " + title + " has been already rated";
                    return message;
                }

                if (!users.get(contor).wasWatched(title)) {
                    message = "error -> " + title + " is not seen";
                    return  message;
                } else {
                    int seasonNo = seasonNumber;
                    users.get(contor).rateSerial(grade, seasonNo, serials.get(j));
                    message = "success -> " + title + " was rated with " + grade + " by " + name;
                    return message;
                }
            }
        }
        return message;
    }

}
