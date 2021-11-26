package myclasses;

import fileio.ShowInput;

import java.util.ArrayList;
import java.util.Map;

public abstract class MyShowInput {

    private String title;
    private int year;
    private ArrayList<String> cast;
    private ArrayList<String> genres;
    private int noFavorite;
    private int noViews;
    private Double rating = 0d;

    /**
     * Default Constructor.
     */
    public MyShowInput() {

    }

    /**
     * Setter pentru campul title.
     */
    public void setTitle(final String title) {
        this.title = title;
    }

    /**
     * Setter pentru campul year.
     */
    public void setYear(final int year) {
        this.year = year;
    }

    /**
     * Setter pentru campul cast.
     */
    public void setCast(final ArrayList<String> cast) {
        this.cast = cast;
    }

    /**
     * Setter pentru campul genres.
     */
    public void setGenres(final ArrayList<String> genres) {
        this.genres = genres;
    }

    /**
     * Getter pentru campul noFavorite.
     */
    public int getNoFavorite() {
        return noFavorite;
    }

    /**
     * Setter pentru campul noFavorite.
     */
    public void setNoFavorite(final int noFavorite) {
        this.noFavorite = noFavorite;
    }

    /**
     * Getter pentru campul noViews.
     */
    public int getNoViews() {
        return noViews;
    }

    /**
     * Setter pentru campul noViews.
     */
    public void setNoViews(final int noViews) {
        this.noViews = noViews;
    }

    /**
     * Getter pentru campul rating.
     */
    public Double getRating() {
        return rating;
    }

    /**
     * Setter pentru campul rating.
     */
    public void setRating(final Double rating) {
        this.rating = rating;
    }

    /**
     * Copy-Constructor
     * @param show
     * videoclip de tipul ShowInput (clasa din scheletul temei)
     */
    public MyShowInput(final ShowInput show) {
        this.title = show.getTitle();
        this.year = show.getYear();
        this.cast = show.getCast();
        this.genres = show.getGenres();
    }

    /**
     * Metoda care va fi apelata in metoda Main si care initializeaza
     * vizualizarile videoclipului
     * @param users
     * lista cu utilizatorii din database
     */
    public void initializeViews(final ArrayList<MyUser> users) {
        // in Main pt fiecare movie/show o sa apelez functia, cu parametrul "users"
        for (MyUser i : users) {
            for (Map.Entry<String, Integer> entry : i.getHistory().entrySet()) {
                if (entry.getKey().equals(this.title)) {
                    noViews = noViews + entry.getValue();
                }
            }
        }
    }

    /**
     * Metoda care va fi apelata in metoda Main si care initializeaza
     * numarul de favorite ale videoclipului
     * @param users
     * lista cu utilizatorii din database
     */
    public void initializeFavorites(final ArrayList<MyUser> users) {
        for (MyUser i : users) {
            for (int j = 0; j < i.getFavoriteMovies().size(); j++) {
                if (this.title.equals(i.getFavoriteMovies().get(j))) {
                    noFavorite++;
                }
            }
        }
    }

    /**
     * Metoda care va verifica daca videoclipul apartine genului
     * dat ca parametru
     * @param genre
     * genul pentru care se face verificarea
     */
    public boolean hasGenre(final String genre) {
        int ok = 0;
        for (String i : genres) {
            if (i.equals(genre)) {
                ok = 1;
            }
        }
        return ok == 1;
    }

    /**
     * Getter pentru campul title.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Getter pentru campul year.
     */
    public int getYear() {
        return year;
    }

    /**
     * Getter pentru campul cast.
     */
    public ArrayList<String> getCast() {
        return cast;
    }

    /**
     * Getter pentru campul genres.
     */
    public ArrayList<String> getGenres() {
        return genres;
    }
}
