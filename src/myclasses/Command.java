package myclasses;

import java.util.ArrayList;

public class Command {

    public String favorite(final ArrayList<MyUser> users,
                           final int contor, final String title,
                           final ArrayList<MyMovie> movies,
                           final ArrayList<MySerialInput> serials) {

        String message;

        if (users.get(contor).isFavorite(title)) {
            message = "error -> " + title + " is already in favourite list";
            return message;
        }
        else {
            if (!users.get(contor).wasWatched(title)) {
                message = "error -> " + title + " is not seen";
                return message;
            }
            else {
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
        int nr = users.get(contor).view(title, show); // Am vizionat o data/inca o data si am salvat in "nr" numarul de vizionari
        message = "success -> " + title + " was viewed with total views of " + nr;

        return message;
    }


    public String rating(final ArrayList<MyUser> users,
                         final int contor, final String title,
                         final String name, final Double grade,
                         final int seasonNumber, final ArrayList<MyMovie> movies,
                         final ArrayList<MySerialInput> serials) {

        String message = "";
        int j;

        for (j = 0; j < movies.size(); j++) {
            if (movies.get(j).title.equals(title)) { //Asta inseamna ca titlul este al unui film.

                if (users.get(contor).isRated(title)) {
                    message = "error -> " + title + " has been already rated";
                    return message;
                }

                if (!users.get(contor).wasWatched(title)) {
                    message = "error -> " + title + " is not seen";
                    return message;
                }

                else {
                    users.get(contor).rateMovie(grade, movies.get(j));    //Am dat o nota filmului
                    message = "success -> " + title + " was rated with " + grade + " by " + name;
                    return message;
                }
            }
        }
        for (j = 0; j < serials.size(); j++) {
            if (serials.get(j).title.equals(title)) {//Asta inseamna ca titlul este al unui serial.
                if (!users.get(contor).wasWatched(title)) {
                    message = "error -> " + title + " is not seen";
                    return  message;
                }
                else {
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
