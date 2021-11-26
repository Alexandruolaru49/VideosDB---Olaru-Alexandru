package myclasses.Query;

import myclasses.CopyClasses.MyMovie;
import java.util.ArrayList;
import java.util.List;

public class QueryMovies {

    /**
     * Metoda care initializeaza lista de filme care urmeaza a fi sortata ulterior
     * Se face filtrarea, fiind adaugate in lista noua doar filmele care au anul si genul cerut,
     * in cazul in care este specificat unul anume.
     * @param movies
     * lista de filme din database
     * @param genre
     * genul filmului din database cu care se va face filtrarea
     * @param year
     * anul fimului din database cu care se va face filtrarea
     */
    public ArrayList<MyMovie> initializeMovies(final List<String> genre,
                                               final List<String> year,
                                               final ArrayList<MyMovie> movies) {


        ArrayList<MyMovie> movieList = new ArrayList<MyMovie>();
        int j, k, ok;

        if ((genre.get(0) == null) && (year.get(0) == null)) {
            for (j = 0; j < movies.size(); j++) {
                movieList.add(movies.get(j));
            }
        }

        if ((genre.get(0) != null) && (year.get(0) != null)) {
            for (j = 0; j < movies.size(); j++) {
                ok = 0;
                if (String.valueOf(movies.get(j).getYear()).equals(year.get(0))) {
                    for (k = 0; k < movies.get(j).getGenres().size(); k++) {
                        if (movies.get(j).getGenres().get(k).equals(genre.get(0))) {
                            ok = 1;
                        }
                    }
                    if (ok == 1) {
                        movieList.add(movies.get(j));
                    }
                }
            }
        }

        if ((genre.get(0) == null) && (year.get(0) != null)) {
            for (j = 0; j < movies.size(); j++) {
                if (String.valueOf(movies.get(j).getYear()).equals(year.get(0))) {
                    movieList.add(movies.get(j));
                }
            }
        }

        if ((genre.get(0) != null) && (year.get(0) == null)) {
            for (j = 0; j < movies.size(); j++) {
                ok = 0;
                for (k = 0; k < movies.get(j).getGenres().size(); k++) {
                    if (movies.get(j).getGenres().get(k).equals(genre.get(0))) {
                        ok = 1;
                    }
                }
                if (ok == 1) {
                    movieList.add(movies.get(j));
                }
            }
        }

        return movieList;
    }

    /**
     * Metoda care compara lista de filme in mod crescator dupa ratingul acestora,
     * iar apoi crescator dupa nume.
     * @param movieList
     * lista ce va fi sortata
     * @param number
     * primele "number" filme ce se doresc afisate
     */
    public String ascRating(final ArrayList<MyMovie> movieList, final int number) {

        int j;
        StringBuilder message;
        int count = number;

        movieList.sort((v1, v2) -> {
            if (v1.getRating().equals(v2.getRating())) {
                return v1.getTitle().compareTo(v2.getTitle());
            } else {
                return Double.compare(v1.getRating(), v2.getRating());
            }
        });

        message = new StringBuilder("Query result: [");

        j = 0;
        while (j < movieList.size() && count != 0) {
            if (movieList.get(j).getRatingMovie() != 0d) {
                message.append(movieList.get(j).getTitle());
                if ((j != movieList.size() - 1) && (count > 1)) {
                    message.append(", ");
                }
                j++;
                count--;
            } else {
                j++;
            }
        }
        message.append("]");

        return message.toString();
    }

    /**
     * Metoda care compara lista de filme in mod crescator dupa durata acestora,
     * iar apoi crescator dupa nume
     * @param movieList
     * lista ce va fi sortata
     * @param number
     * primele "number" filme ce se doresc afisate
     */
    public String ascLongest(final ArrayList<MyMovie> movieList, final int number) {

        int j;
        StringBuilder message;

        movieList.sort((v1, v2) -> {
            if (v1.getDuration() == v2.getDuration()) {
                return v1.getTitle().compareTo(v2.getTitle());
            } else {
                return Integer.compare(v1.getDuration(), v2.getDuration());
            }
        });

        message = new StringBuilder("Query result: [");

        for (j = 0; j < movieList.size(); j++) {
            if (j == (number)) {
                break;
            }
            message.append(movieList.get(j).getTitle());
            if ((j != movieList.size() - 1) && (j != (number - 1))) {
                message.append(", ");
            }
        }
        message.append("]");

        return message.toString();
    }

    /**
     * Metoda care compara lista de filme in mod crescator dupa numarul de favorite
     * ale acestora, iar apoi crescator dupa nume.
     * @param movieList
     * lista ce va fi sortata
     * @param number
     * primele "number" filme ce se doresc afisate
     */
    public String ascFavorite(final ArrayList<MyMovie> movieList, final int number) {

        int j;
        StringBuilder message;
        int count = number;

        movieList.sort((v1, v2) -> {
            if (v1.getNoFavorite() == v2.getNoFavorite()) {
                return v1.getTitle().compareTo(v2.getTitle());
            } else {
                return Integer.compare(v1.getNoFavorite(), v2.getNoFavorite());
            }
        });

        message = new StringBuilder("Query result: [");

        j = 0;
        while (j < movieList.size() && count != 0) {
            if (movieList.get(j).getNoFavorite() != 0) {
                message.append(movieList.get(j).getTitle());
                if ((j != movieList.size() - 1) && (count > 1)) {
                    message.append(", ");
                }
                j++;
                count--;
            } else {
                j++;
            }
        }
        message.append("]");

        return message.toString();
    }

    /**
     * Metoda care compara lista de filme in mod crescator dupa numarul de vizionari
     * ale acestora, iar apoi crescator dupa nume.
     * @param movieList
     * lista ce va fi sortata
     * @param number
     * primele "number" filme ce se doresc afisate
     */
    public String ascViews(final ArrayList<MyMovie> movieList, final int number) {

        int j;
        StringBuilder message;
        int count = number;

        movieList.sort((v1, v2) -> {
            if (v1.getNoViews() == v2.getNoViews()) {
                return v1.getTitle().compareTo(v2.getTitle());
            } else {
                return Integer.compare(v1.getNoViews(), v2.getNoViews());
            }
        });

        message = new StringBuilder("Query result: [");

        j = 0;
        while (j < movieList.size() && count != 0) {
            if (movieList.get(j).getNoViews() != 0) {
                message.append(movieList.get(j).getTitle());
                if ((j != movieList.size() - 1) && (count > 1)) {
                    message.append(", ");
                }
                j++;
                count--;
            } else {
                j++;
            }
        }
        message.append("]");

        return message.toString();
    }

    /**
     * Metoda care compara lista de filme in mod descrescator dupa ratingul acestora,
     * iar apoi descrescator dupa nume.
     * @param movieList
     * lista ce va fi sortata
     * @param number
     * primele "number" filme ce se doresc afisate
     */
    public String descRating(final ArrayList<MyMovie> movieList, final int number) {

        int j;
        StringBuilder message;
        int count = number;

        movieList.sort((v1, v2) -> {
            if (v1.getRating().equals(v2.getRating())) {
                return v2.getTitle().compareTo(v1.getTitle());
            } else {
                return Double.compare(v2.getRating(), v1.getRating());
            }
        });

        int end;
        for (end = 0; end < movieList.size(); end++) {
            if (movieList.get(end).getRatingMovie() == 0d) {
                break;
            }
        }

        message = new StringBuilder("Query result: [");
        j = 0;
        while (j < end && count != 0) {
            if (movieList.get(j).getRatingMovie() != 0d) {
                message.append(movieList.get(j).getTitle());
                if ((j != end - 1) && (count > 1)) {
                    message.append(", ");
                }
                j++;
                count--;
            } else {
                j++;
            }
        }
        message.append("]");

        return message.toString();
    }

    /**
     * Metoda care compara lista de filme in mod descrescator dupa durata acestora,
     * iar apoi descrescator dupa nume
     * @param movieList
     * lista ce va fi sortata
     * @param number
     * primele "number" filme ce se doresc afisate
     */
    public String descLongest(final ArrayList<MyMovie> movieList, final int number) {

        int j;
        StringBuilder message;

        movieList.sort((v1, v2) -> {
            if (v1.getDuration() == v2.getDuration()) {
                return v2.getTitle().compareTo(v1.getTitle());
            } else {
                return Integer.compare(v2.getDuration(), v1.getDuration());
            }
        });

        message = new StringBuilder("Query result: [");

        for (j = 0; j < movieList.size(); j++) {
            if (j == (number)) {
                break;
            }
            message.append(movieList.get(j).getTitle());
            if ((j != movieList.size() - 1) && (j != (number - 1))) {
                message.append(", ");
            }
        }
        message.append("]");

        return message.toString();
    }

    /**
     * Metoda care compara lista de filme in mod descrescator dupa numarul de favorite
     * ale acestora, iar apoi descrescator dupa nume.
     * @param movieList
     * lista ce va fi sortata
     * @param number
     * primele "number" filme ce se doresc afisate
     */
    public String descFavorite(final ArrayList<MyMovie> movieList, final int number) {

        int j;
        StringBuilder message;
        int count = number;

        movieList.sort((v1, v2) -> {
            if (v1.getNoFavorite() == v2.getNoFavorite()) {
                return v2.getTitle().compareTo(v1.getTitle());
            } else {
                return Integer.compare(v2.getNoFavorite(), v1.getNoFavorite());
            }
        });

        int end;
        for (end = 0; end < movieList.size(); end++) {
            if (movieList.get(end).getNoFavorite() == 0) {
                break;
            }
        }

        message = new StringBuilder("Query result: [");

        j = 0;
        while (j < end && count != 0) {
            if (movieList.get(j).getNoFavorite() != 0) {
                message.append(movieList.get(j).getTitle());
                if ((j != end - 1) && (count > 1)) {
                    message.append(", ");
                }
                j++;
                count--;
            } else {
                j++;
            }
        }
        message.append("]");

        return message.toString();
    }

    /**
     * Metoda care compara lista de filme in mod descrescator dupa numarul de vizionari
     * ale acestora, iar apoi descrescator dupa nume.
     * @param movieList
     * lista ce va fi sortata
     * @param number
     * primele "number" filme ce se doresc afisate
     */
    public String descViews(final ArrayList<MyMovie> movieList, final int number) {

        int j;
        StringBuilder message;
        int count = number;

        movieList.sort((v1, v2) -> {
            if (v1.getNoViews() == v2.getNoViews()) {
                return v2.getTitle().compareTo(v1.getTitle());
            } else {
                return Integer.compare(v2.getNoViews(), v1.getNoViews());
            }
        });

        int end;
        for (end = 0; end < movieList.size(); end++) {
            if (movieList.get(end).getNoViews() == 0) {
                break;
            }
        }

        message = new StringBuilder("Query result: [");
        j = 0;
        while (j < end && count != 0) {
            if (movieList.get(j).getNoViews() != 0) {
                message.append(movieList.get(j).getTitle());
                if ((j != end - 1) && (count > 1)) {
                    message.append(", ");
                }
                j++;
                count--;
            } else {
                j++;
            }
        }

        message.append("]");

        return message.toString();
    }

}



