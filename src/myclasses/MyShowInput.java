package myclasses;

import fileio.ShowInput;

import java.util.ArrayList;
import java.util.Map;

public abstract class MyShowInput {

    public String title;
    public int year;
    public ArrayList<String> cast;
    public ArrayList<String> genres;
    public int noFavorite;
    public int noViews;
    public Double rating = 0d;

    public MyShowInput() {

    }

    public MyShowInput(final ShowInput show) {
        this.title = show.getTitle();
        this.year = show.getYear();
        this.cast = show.getCast();
        this.genres = show.getGenres();
    }


    public void initializeViews(final ArrayList<MyUser> users) {
        // in Main pt fiecare movie/show o sa apelez functia, cu parametrul "users"
        for (MyUser i : users) {
            for (Map.Entry<String, Integer> entry : i.history.entrySet()) {
                if (entry.getKey().equals(this.title)) {
                    noViews = noViews + entry.getValue();
                }
            }
        }
    }

    public void initializeFavorites(final ArrayList<MyUser> users) {
        for (MyUser i : users) {
            for (int j = 0; j < i.favoriteMovies.size(); j++) {
                if (this.title.equals(i.favoriteMovies.get(j))) {
                    noFavorite++;
                }
            }
        }
    }

    public String getTitle() {
        return title;
    }

    public int getYear() {
        return year;
    }

    public ArrayList<String> getCast() {
        return cast;
    }

    public ArrayList<String> getGenres() {
        return genres;
    }
}
