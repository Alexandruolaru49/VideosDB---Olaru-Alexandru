package myclasses;


import fileio.UserInputData;;
import java.util.ArrayList;
import java.util.Map;

public class MyUser {

    public String username;
    public String subscriptionType;
    public Map<String, Integer> history;
    public ArrayList<String> favoriteMovies;
    public ArrayList<String> ratedVideos = new ArrayList<String>();
    public int ratingsGiven = 0;

    public MyUser(final UserInputData user) {
        this.username = user.getUsername();
        this.subscriptionType = user.getSubscriptionType();
        this.history = user.getHistory();
        this.favoriteMovies = user.getFavoriteMovies();
    }

    public MyUser(final MyUser user) {
        this.username = user.getUsername();
        this.subscriptionType = user.getSubscriptionType();
        this.history = user.getHistory();
        this.favoriteMovies = user.getFavoriteMovies();
        this.ratingsGiven = user.ratingsGiven;
    }

    public String getUsername() {
        return username;
    }

    public String getSubscriptionType() {
        return subscriptionType;
    }

    public Map<String, Integer> getHistory() {
        return history;
    }

    public ArrayList<String> getFavoriteMovies() {
        return favoriteMovies;
    }

    public void addToFavorites(final String title, final MyShowInput show) {
        favoriteMovies.add(title);
        show.noFavorite++;
    }

    public boolean isFavorite(final String movie) {
        int i;
        for (i = 0; i < this.favoriteMovies.size(); i++) {
            if (this.favoriteMovies.get(i).equals(movie)) {
                return true;
            }
        }
        return false;
    }

    public boolean wasWatched(final String movie) {
        for (Map.Entry<String, Integer> i : history.entrySet()) {
            if ((i.getKey().equals(movie)) && (i.getValue() != 0)) {
                return true;
            }
        }
        return false;
    }

    public boolean isRated(final String movie) {
        int i;
        for (i = 0; i < this.ratedVideos.size(); i++) {
            if (this.ratedVideos.get(i).equals(movie)) {
                return true;
            }
        }
        return false;
    }

    public int view(final String title, final MyShowInput show) {
        int value = 0;
        for (Map.Entry<String, Integer> i : history.entrySet()) {
            if (i.getKey().equals(title)) {
                value = i.getValue();
                value = value + 1;
                i.setValue(value);
                show.noViews++;
                return value;
            }
        }
        value = 1;
        this.history.put(title, value);
        show.noViews++;
        return value;
    }

    public void rateMovie(final Double grade, final MyMovie movie) {

        ratedVideos.add(movie.title);
        movie.ratings.add(grade);
        // DUPA ADAUGAREA UNEI NOTE, MODIFIC RATINGUL FILMULUI (MEDIA ARITMETICA)
        //movie.updateRating();
        //movie.getRatingMovie();
        movie.getRating();
        ratingsGiven++;
    }

    public void rateSerial(final Double grade, final int seasonNo,
                           final MySerialInput serial) {

        int i;
        //ratedVideos.add(serial.title);
        serial.seasons.get(seasonNo - 1).getRatings().add(grade);
        // DUPA ADAUGAREA UNEI NOTE PENTRU UN SEZON, MODIFIC RATINGUL SERIALULUI

        //serial.getRatingSerial();
        serial.getRating();
        ratingsGiven++;
    }

    @Override
    public String toString() {
        return "UserInputData{" + "username='"
                + username + '\'' + ", subscriptionType='"
                + subscriptionType + '\'' + ", history="
                + history + ", favoriteMovies="
                + favoriteMovies + '}';
    }
}
