package myclasses.Query;

import myclasses.MyActor;
import myclasses.MyMovie;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class QueryMovies {

    public void InitializeMovieList(List<String> genre, List<String> year, ArrayList<MyMovie> movies, ArrayList<MyMovie> movieList) {

        int j, k, ok;
        if((genre.size() > 0) && (year.size() > 0)) {     // DACA GENRE SI YEAR SUNT DIFERITE DE NULL
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

        if((genre.size() == 0) && (year.size() > 0)) {
            for(j = 0; j < movies.size(); j++) {
                if(String.valueOf(movies.get(j).year).equals(year.get(0))) {
                    movieList.add(movies.get(j));
                }
            }
        }

        if((genre.size() > 0) && (year.size() == 0)) {
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

    public String AscSortByRating(ArrayList<MyMovie> movieList, int number) {

        int j, k;
        MyMovie aux;
        StringBuilder message;

        Collections.sort(movieList, new Comparator<MyMovie>() {
            public int compare(MyMovie v1, MyMovie v2) {
                return v1.getTitle().compareTo(v2.getTitle());
            }
        });

        Collections.sort(movieList, Comparator.comparing(MyMovie::getTitle));

        for (j = 0; j < movieList.size() - 1; j++) {
            for (k = j + 1; k < movieList.size(); k++) {
                //if (movieList.get(j).ratingMovie > movieList.get(k).ratingMovie) {
                if (Double.compare(movieList.get(j).ratingMovie, movieList.get(k).ratingMovie) > 0) {
                    aux = new MyMovie(movieList.get(j));
                    movieList.set(j, movieList.get(k));
                    movieList.set(k, aux);
                }
            }
        }
        message = new StringBuilder("Query result: [");
        j = 0;
        while(j < movieList.size() && number != 0) {
            if(movieList.get(j).ratingMovie != 0d) {
                message.append(movieList.get(j).title);
                if ((j != movieList.size() - 1) && (number > 1)) { //  AICI!!!!!
                    message.append(", ");
                }
                j++;
                number--;
            }
            else {
                j++;
            }
        }
        message.append("]");

        return message.toString();
    }

    public String AscSortByLongest(ArrayList<MyMovie> movieList, int number) {

        int j,k;
        MyMovie aux;
        StringBuilder message;

        Collections.sort(movieList, new Comparator<MyMovie>() {
            public int compare(MyMovie v1, MyMovie v2) {
                return v1.getTitle().compareTo(v2.getTitle());
            }
        });

        Collections.sort(movieList, Comparator.comparing(MyMovie::getTitle));

        for (j = 0; j < movieList.size() - 1; j++) {
            for (k = j + 1; k < movieList.size(); k++) {
                if (movieList.get(j).duration > movieList.get(k).duration) {
                    aux = new MyMovie(movieList.get(j));
                    movieList.set(j, movieList.get(k));
                    movieList.set(k, aux);
                }
            }
        }

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

    public String AscSortByFavorite (ArrayList<MyMovie> movieList, int number) {

        int j, k;
        MyMovie aux;
        StringBuilder message;

        Collections.sort(movieList, new Comparator<MyMovie>() {
            public int compare(MyMovie v1, MyMovie v2) {
                return v1.getTitle().compareTo(v2.getTitle());
            }
        });

        Collections.sort(movieList, Comparator.comparing(MyMovie::getTitle));

        for (j = 0; j < movieList.size() - 1; j++) {
            for (k = j + 1; k < movieList.size(); k++) {
                if (movieList.get(j).noFavorite > movieList.get(k).noFavorite) {
                    aux = new MyMovie(movieList.get(j));
                    movieList.set(j, movieList.get(k));
                    movieList.set(k, aux);
                }
            }
        }
        message = new StringBuilder("Query result: [");
        j = 0;
        while(j < movieList.size() && number != 0) {
            if(movieList.get(j).noFavorite != 0) {
                message.append(movieList.get(j).title);
                if ((j != movieList.size() - 1) && (number > 1)) { //  AICI!!!!!
                    message.append(", ");
                }
                j++;
                number--;
            }
            else {
                j++;
            }
        }
        message.append("]");

        return message.toString();
    }

    public String AscSortByViews(ArrayList<MyMovie> movieList, int number) {

        int j, k;
        MyMovie aux;
        StringBuilder message;

        Collections.sort(movieList, new Comparator<MyMovie>() {
            public int compare(MyMovie v1, MyMovie v2) {
                return v1.getTitle().compareTo(v2.getTitle());
            }
        });

        Collections.sort(movieList, Comparator.comparing(MyMovie::getTitle));

        for (j = 0; j < movieList.size() - 1; j++) {
            for (k = j + 1; k < movieList.size(); k++) {
                if (movieList.get(j).noViews > movieList.get(k).noViews) {
                    aux = new MyMovie(movieList.get(j));
                    movieList.set(j, movieList.get(k));
                    movieList.set(k, aux);
                }
            }
        }

        message = new StringBuilder("Query result: [");
        j = 0;
        while(j < movieList.size() && number != 0) {
            if(movieList.get(j).noViews != 0) {
                message.append(movieList.get(j).title);
                if ((j != movieList.size() - 1) && (number > 1)) { //  AICI!!!!!
                    message.append(", ");
                }
                j++;
                number--;
            }
            else {
                j++;
            }
        }
        message.append("]");

        return message.toString();
    }

    public String DescSortByRating(ArrayList<MyMovie> movieList, int number) {

        int j, k;
        MyMovie aux;
        StringBuilder message;

        Collections.sort(movieList, new Comparator<MyMovie>() {
            public int compare(MyMovie v1, MyMovie v2) {
                return v1.getTitle().compareTo(v2.getTitle());
            }
        });

        Collections.sort(movieList, Comparator.comparing(MyMovie::getTitle));
        Collections.reverse(movieList);

        for (j = 0; j < movieList.size() - 1; j++) {
            for (k = j + 1; k < movieList.size(); k++) {
                //if (movieList.get(j).ratingMovie < movieList.get(k).ratingMovie) {
                if (Double.compare(movieList.get(j).ratingMovie, movieList.get(k).ratingMovie) < 0) {
                    aux = new MyMovie(movieList.get(j));
                    movieList.set(j, movieList.get(k));
                    movieList.set(k, aux);
                }
            }
        }
        message = new StringBuilder("Query result: [");
        j = 0;
        while(j < movieList.size() && number != 0) {
            if(movieList.get(j).ratingMovie != 0d) {
                message.append(movieList.get(j).title);
                if ((j != movieList.size() - 1) && (number > 1)) { //  AICI!!!!!
                    message.append(", ");
                }
                j++;
                number--;
            }
            else {
                j++;
            }
        }
        message.append("]");

        return message.toString();
    }

    public String DescSortByLongest(ArrayList<MyMovie> movieList, int number) {

        int j, k;
        MyMovie aux;
        StringBuilder message;

        Collections.sort(movieList, new Comparator<MyMovie>() {
            public int compare(MyMovie v1, MyMovie v2) {
                return v1.getTitle().compareTo(v2.getTitle());
            }
        });

        Collections.sort(movieList, Comparator.comparing(MyMovie::getTitle));
        Collections.reverse(movieList);

        for (j = 0; j < movieList.size() - 1; j++) {
            for (k = j + 1; k < movieList.size(); k++) {
                if (movieList.get(j).duration < movieList.get(k).duration) {
                    aux = new MyMovie(movieList.get(j));
                    movieList.set(j, movieList.get(k));
                    movieList.set(k, aux);
                }
            }
        }
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

    public String DescSortByFavorite(ArrayList<MyMovie> movieList, int number) {

        int j, k;
        MyMovie aux;
        StringBuilder message;

        Collections.sort(movieList, new Comparator<MyMovie>() {
            public int compare(MyMovie v1, MyMovie v2) {
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
        while(j < movieList.size() && number != 0) {
            if(movieList.get(j).noFavorite != 0) {
                message.append(movieList.get(j).title);
                if ((j != movieList.size() - 1) && (number > 1)) { //  AICI!!!!!
                    message.append(", ");
                }
                j++;
                number--;
            }
            else {
                j++;
            }
        }
        message.append("]");

        return message.toString();
    }

    public String DescSortByViews(ArrayList<MyMovie> movieList, int number) {

        int j, k;
        MyMovie aux;
        StringBuilder message;

        Collections.sort(movieList, new Comparator<MyMovie>() {
            public int compare(MyMovie v1, MyMovie v2) {
                return v1.getTitle().compareTo(v2.getTitle());
            }
        });

        Collections.sort(movieList, Comparator.comparing(MyMovie::getTitle));
        Collections.reverse(movieList);

        for (j = 0; j < movieList.size() - 1; j++) {
            for (k = j + 1; k < movieList.size(); k++) {
                if (movieList.get(j).noViews < movieList.get(k).noViews) {
                    aux = new MyMovie(movieList.get(j));
                    movieList.set(j, movieList.get(k));
                    movieList.set(k, aux);
                }
            }
        }

        message = new StringBuilder("Query result: [");
        j = 0;
        while(j < movieList.size() && number != 0) {
            if(movieList.get(j).noViews != 0) {
                message.append(movieList.get(j).title);
                if ((j != movieList.size() - 1) && (number > 1)) { //  AICI!!!!!
                    message.append(", ");
                }
                j++;
                number--;
            }
            else {
                j++;
            }
        }

        message = new StringBuilder(message.substring(0, message.length() - 1));
        message = new StringBuilder(message.substring(0, message.length() - 1));
        message.append("]");

        return message.toString();
    }


}



