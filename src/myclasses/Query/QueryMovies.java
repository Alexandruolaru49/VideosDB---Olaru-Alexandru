package myclasses.Query;

import myclasses.MyMovie;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class QueryMovies {

    public void initializeMovies(final List<String> genre,
                                    final List<String> year,
                                    final ArrayList<MyMovie> movies,
                                    final ArrayList<MyMovie> movieList) {

        int j, k, ok;
        // DACA GENRE SI YEAR SUNT DIFERITE DE NULL
        if ((genre.size() > 0) && (year.size() > 0)) {
            for (j = 0; j < movies.size(); j++) {
                ok = 0;
                if (String.valueOf(movies.get(j).year).equals(year.get(0))) {
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

        if ((genre.size() == 0) && (year.size() > 0)) {
            for (j = 0; j < movies.size(); j++) {
                if (String.valueOf(movies.get(j).year).equals(year.get(0))) {
                    movieList.add(movies.get(j));
                }
            }
        }

        if ((genre.size() > 0) && (year.size() == 0)) {
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
    }

    public String ascRating(final ArrayList<MyMovie> movieList, final int number) {

        int j, k;
        MyMovie aux;
        StringBuilder message;
        int count = number;

        movieList.sort((v1, v2) -> {
            if (v1.getRating().equals(v2.getRating())) {
                return v1.getTitle().compareTo(v2.getTitle());
            } else {
                return Double.compare(v1.getRating(), v2.getRating());
            }
        });

//        Collections.sort(movieList, new Comparator<MyMovie>() {
//            public int compare(final MyMovie v1, final MyMovie v2) {
//                return v1.getTitle().compareTo(v2.getTitle());
//            }
//        });
//
//        Collections.sort(movieList, Comparator.comparing(MyMovie::getTitle));
//
//        for (j = 0; j < movieList.size() - 1; j++) {
//            for (k = j + 1; k < movieList.size(); k++) {
//                if (Double.compare(movieList.get(j).ratingMovie,
//                        movieList.get(k).ratingMovie) > 0) {
//                    aux = new MyMovie(movieList.get(j));
//                    movieList.set(j, movieList.get(k));
//                    movieList.set(k, aux);
//                }
//            }
//        }
        message = new StringBuilder("Query result: [");
        j = 0;
        while (j < movieList.size() && count != 0) {
            if (movieList.get(j).ratingMovie != 0d) {
                message.append(movieList.get(j).title);
                if ((j != movieList.size() - 1) && (count > 1)) { //  AICI!!!!!
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

    public String ascLongest(final ArrayList<MyMovie> movieList, final int number) {

        int j, k;
        MyMovie aux;
        StringBuilder message;

        movieList.sort((v1, v2) -> {
            if (v1.getDuration() == v2.getDuration()) {
                return v1.getTitle().compareTo(v2.getTitle());
            } else {
                return Integer.compare(v1.getDuration(), v2.getDuration());
            }
        });

//        Collections.sort(movieList, new Comparator<MyMovie>() {
//            public int compare(final MyMovie v1, final MyMovie v2) {
//                return v1.getTitle().compareTo(v2.getTitle());
//            }
//        });
//
//        Collections.sort(movieList, Comparator.comparing(MyMovie::getTitle));
//
//        for (j = 0; j < movieList.size() - 1; j++) {
//            for (k = j + 1; k < movieList.size(); k++) {
//                if (movieList.get(j).duration > movieList.get(k).duration) {
//                    aux = new MyMovie(movieList.get(j));
//                    movieList.set(j, movieList.get(k));
//                    movieList.set(k, aux);
//                }
//            }
//        }

        message = new StringBuilder("Query result: [");
        for (j = 0; j < movieList.size(); j++) {
            if (j == (number - 1)) {
                break;
            }
            message.append(movieList.get(j).title);
            if ((j != movieList.size() - 1) && (j != (number - 1))) { //  AICI!!!!!
                message.append(", ");
            }
        }
        message.append("]");

        return message.toString();
    }

    public String ascFavorite(final ArrayList<MyMovie> movieList, final int number) {

        int j, k;
        MyMovie aux;
        StringBuilder message;
        int count = number;

        movieList.sort((v1, v2) -> {
            if (v1.noFavorite == v2.noFavorite) {
                return v1.getTitle().compareTo(v2.getTitle());
            } else {
                return Integer.compare(v1.noFavorite, v2.noFavorite);
            }
        });

//        Collections.sort(movieList, new Comparator<MyMovie>() {
//            public int compare(final MyMovie v1, final MyMovie v2) {
//                return v1.getTitle().compareTo(v2.getTitle());
//            }
//        });
//
//        Collections.sort(movieList, Comparator.comparing(MyMovie::getTitle));
//
//        for (j = 0; j < movieList.size() - 1; j++) {
//            for (k = j + 1; k < movieList.size(); k++) {
//                if (movieList.get(j).noFavorite > movieList.get(k).noFavorite) {
//                    aux = new MyMovie(movieList.get(j));
//                    movieList.set(j, movieList.get(k));
//                    movieList.set(k, aux);
//                }
//            }
//        }
        message = new StringBuilder("Query result: [");
        j = 0;
        while (j < movieList.size() && count != 0) {
            if (movieList.get(j).noFavorite != 0) {
                message.append(movieList.get(j).title);
                if ((j != movieList.size() - 1) && (count > 1)) { //  AICI!!!!!
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

    public String ascViews(final ArrayList<MyMovie> movieList, final int number) {

        int j, k;
        MyMovie aux;
        StringBuilder message;
        int count = number;

        movieList.sort((v1, v2) -> {
            if (v1.noViews == v2.noViews) {
                return v1.getTitle().compareTo(v2.getTitle());
            } else {
                return Integer.compare(v1.noViews, v2.noViews);
            }
        });

//        Collections.sort(movieList, new Comparator<MyMovie>() {
//            public int compare(final MyMovie v1, final MyMovie v2) {
//                return v1.getTitle().compareTo(v2.getTitle());
//            }
//        });
//
//        Collections.sort(movieList, Comparator.comparing(MyMovie::getTitle));
//
//        for (j = 0; j < movieList.size() - 1; j++) {
//            for (k = j + 1; k < movieList.size(); k++) {
//                if (movieList.get(j).noViews > movieList.get(k).noViews) {
//                    aux = new MyMovie(movieList.get(j));
//                    movieList.set(j, movieList.get(k));
//                    movieList.set(k, aux);
//                }
//            }
//        }

        message = new StringBuilder("Query result: [");
        j = 0;
        while (j < movieList.size() && count != 0) {
            if (movieList.get(j).noViews != 0) {
                message.append(movieList.get(j).title);
                if ((j != movieList.size() - 1) && (count > 1)) { //  AICI!!!!!
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

    public String descRating(final ArrayList<MyMovie> movieList, final int number) {

        int j, k;
        MyMovie aux;
        StringBuilder message;
        int count = number;

        movieList.sort((v1, v2) -> {
            if (v1.getRating().equals(v2.getRating())) {
                return v2.getTitle().compareTo(v1.getTitle());
            } else {
                return Double.compare(v2.getRating(), v1.getRating());
            }
        });

//        Collections.sort(movieList, new Comparator<MyMovie>() {
//            public int compare(final MyMovie v1, final MyMovie v2) {
//                return v1.getTitle().compareTo(v2.getTitle());
//            }
//        });
//
//        Collections.sort(movieList, Comparator.comparing(MyMovie::getTitle));
//        Collections.reverse(movieList);
//
//        for (j = 0; j < movieList.size() - 1; j++) {
//            for (k = j + 1; k < movieList.size(); k++) {
//                if (Double.compare(movieList.get(j).ratingMovie,
//                        movieList.get(k).ratingMovie) < 0) {
//                    aux = new MyMovie(movieList.get(j));
//                    movieList.set(j, movieList.get(k));
//                    movieList.set(k, aux);
//                }
//            }
//        }
        message = new StringBuilder("Query result: [");
        j = 0;
        while (j < movieList.size() && count != 0) {
            if (movieList.get(j).ratingMovie != 0d) {
                message.append(movieList.get(j).title);
                if ((j != movieList.size() - 1) && (count > 1)) { //  AICI!!!!!
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

    public String descLongest(final ArrayList<MyMovie> movieList, final int number) {

        int j, k;
        MyMovie aux;
        StringBuilder message;

        movieList.sort((v1, v2) -> {
            if (v1.getDuration() == v2.getDuration()) {
                return v2.getTitle().compareTo(v1.getTitle());
            } else {
                return Integer.compare(v2.getDuration(), v1.getDuration());
            }
        });

//        Collections.sort(movieList, new Comparator<MyMovie>() {
//            public int compare(final MyMovie v1, final MyMovie v2) {
//                return v1.getTitle().compareTo(v2.getTitle());
//            }
//        });
//
//        Collections.sort(movieList, Comparator.comparing(MyMovie::getTitle));
//        Collections.reverse(movieList);
//
//        for (j = 0; j < movieList.size() - 1; j++) {
//            for (k = j + 1; k < movieList.size(); k++) {
//                if (movieList.get(j).duration < movieList.get(k).duration) {
//                    aux = new MyMovie(movieList.get(j));
//                    movieList.set(j, movieList.get(k));
//                    movieList.set(k, aux);
//                }
//            }
//        }
        message = new StringBuilder("Query result: [");
        for (j = 0; j < movieList.size(); j++) {
            if (j == (number - 1)) {
                break;
            }
            message.append(movieList.get(j).title);
            if ((j != movieList.size() - 1) && (j != (number - 1))) { //  AICI!!!!!
                message.append(", ");
            }
        }
        message.append("]");

        return message.toString();
    }

    public String descFavorite(final ArrayList<MyMovie> movieList, final int number) {

        int j, k;
        MyMovie aux;
        StringBuilder message;
        int count = number;

//        movieList.sort((v1, v2) -> {
//            if (v1.noFavorite == v2.noFavorite) {
//                return v2.getTitle().compareTo(v1.getTitle());
//            } else {
//                return Integer.compare(v2.noFavorite, v1.noFavorite);
//            }
//        });


        Collections.sort(movieList, new Comparator<MyMovie>() {
            public int compare(final MyMovie v1, final MyMovie v2) {
                return v1.getTitle().compareTo(v2.getTitle());
            }
        });

        Collections.sort(movieList, Comparator.comparing(MyMovie::getTitle));
        Collections.reverse(movieList);

        for (j = 0; j < movieList.size() - 1; j++) {
            for (k = j + 1; k < movieList.size(); k++) {
                if (movieList.get(j).noFavorite < movieList.get(k).noFavorite) {
                    aux = new MyMovie(movieList.get(j));
                    movieList.set(j, movieList.get(k));
                    movieList.set(k, aux);
                }
            }
        }
        message = new StringBuilder("Query result: [");
        j = 0;
        while (j < movieList.size() && count != 0) {
            if (movieList.get(j).noFavorite != 0) {
                message.append(movieList.get(j).title);
                if ((j != movieList.size() - 1) && (count > 1)) { //  AICI!!!!!
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

    public String descViews(final ArrayList<MyMovie> movieList, final int number) {

        int j, k;
        MyMovie aux;
        StringBuilder message;
        int count = number;

        movieList.sort((v1, v2) -> {
            if (v1.noViews == v2.noViews) {
                return v2.getTitle().compareTo(v1.getTitle());
            } else {
                return Integer.compare(v2.noViews, v1.noViews);
            }
        });

//        Collections.sort(movieList, new Comparator<MyMovie>() {
//            public int compare(final MyMovie v1, final MyMovie v2) {
//                return v1.getTitle().compareTo(v2.getTitle());
//            }
//        });
//
//        Collections.sort(movieList, Comparator.comparing(MyMovie::getTitle));
//        Collections.reverse(movieList);
//
//        for (j = 0; j < movieList.size() - 1; j++) {
//            for (k = j + 1; k < movieList.size(); k++) {
//                if (movieList.get(j).noViews < movieList.get(k).noViews) {
//                    aux = new MyMovie(movieList.get(j));
//                    movieList.set(j, movieList.get(k));
//                    movieList.set(k, aux);
//                }
//            }
//        }

        message = new StringBuilder("Query result: [");
        j = 0;
        while (j < movieList.size() && count != 0) {
            if (movieList.get(j).noViews != 0) {
                message.append(movieList.get(j).title);
                if ((j != movieList.size() - 1) && (count > 1)) { //  AICI!!!!!
                    message.append(", ");
                }
                j++;
                count--;
            } else {
                j++;
            }
        }

        message = new StringBuilder(message.substring(0, message.length() - 1));
        message = new StringBuilder(message.substring(0, message.length() - 1));
        message.append("]");

        return message.toString();
    }


}



