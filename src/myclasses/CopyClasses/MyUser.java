package myclasses.CopyClasses;


import fileio.UserInputData;;
import java.util.ArrayList;
import java.util.Map;

public class MyUser {

    private String username;
    private String subscriptionType;
    private Map<String, Integer> history;
    private ArrayList<String> favoriteMovies;
    private ArrayList<String> ratedVideos = new ArrayList<String>();
    private int ratingsGiven = 0;

    /**
     * Copy-Constructor
     * @param user
     * utilizator de tipul UserInputData (clasa din scheletul temei)
     */
    public MyUser(final UserInputData user) {
        this.username = user.getUsername();
        this.subscriptionType = user.getSubscriptionType();
        this.history = user.getHistory();
        this.favoriteMovies = user.getFavoriteMovies();
    }

    /**
     * Copy-Constructor
     * @param user
     * utilizator de tipul propriu creat.
     */
    public MyUser(final MyUser user) {
        this.username = user.getUsername();
        this.subscriptionType = user.getSubscriptionType();
        this.history = user.getHistory();
        this.favoriteMovies = user.getFavoriteMovies();
        this.ratingsGiven = user.ratingsGiven;
    }

    /**
     * Getter pentru campul ratingsGiven.
     */
    public int getRatingsGiven() {
        return ratingsGiven;
    }

    /**
     * Setter pentru campul ratingsGiven.
     */
    public void setRatingsGiven(final int ratingsGiven) {
        this.ratingsGiven = ratingsGiven;
    }

    /**
     * Getter pentru campul ratedVideos.
     */
    public ArrayList<String> getRatedVideos() {
        return ratedVideos;
    }

    /**
     * Setter pentru campul ratedVideos.
     */
    public void setRatedVideos(final ArrayList<String> ratedVideos) {
        this.ratedVideos = ratedVideos;
    }

    /**
     * Setter pentru campul history.
     */
    public void setHistory(final Map<String, Integer> history) {
        this.history = history;
    }

    /**
     * Getter pentru campul username.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Setter pentru campul username.
     */
    public void setUsername(final String username) {
        this.username = username;
    }

    /**
     * Getter pentru campul subscriptionType.
     */
    public String getSubscriptionType() {
        return subscriptionType;
    }

    /**
     * Setter pentru campul subscriptionType.
     */
    public void setSubscriptionType(final String subscriptionType) {
        this.subscriptionType = subscriptionType;
    }

    /**
     * Getter pentru campul history.
     */
    public Map<String, Integer> getHistory() {
        return history;
    }

    /**
     * Getter pentru campul favoriteMovies.
     */
    public ArrayList<String> getFavoriteMovies() {
        return favoriteMovies;
    }

    /**
     * Setter pentru campul favoriteMovies.
     */
    public void setFavoriteMovies(final ArrayList<String> favoriteMovies) {
        this.favoriteMovies = favoriteMovies;
    }

    /**
     * Metoda de adaugare a unui videoclip in lista de favorite
     * a utilizatorului, care creste si numarul de favorite al
     * videoclipului respectiv.
     * @param show
     * videoclipul ce urmeaza a fi introdus in lista de favorite
     * @param title
     * numele videoclipului
     */
    public void addToFavorites(final String title, final MyShowInput show) {
        favoriteMovies.add(title);
        //show.noFavorite++;
        show.setNoFavorite(show.getNoFavorite() + 1);
    }

    /**
     * Metoda care verifica daca un videoclip este deja
     * in lista de favorite a utilizatorului.
     * @param movie
     * titlul pentru care se face verificarea
     */
    public boolean isFavorite(final String movie) {
        int i;
        for (i = 0; i < this.favoriteMovies.size(); i++) {
            if (this.favoriteMovies.get(i).equals(movie)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Metoda care verifica daca un videoclip a fost deja vizionat
     * de catre utilizator.
     * @param movie
     * titlul pentru care se face verificarea
     */
    public boolean wasWatched(final String movie) {
        for (Map.Entry<String, Integer> i : history.entrySet()) {
            if ((i.getKey().equals(movie)) && (i.getValue() != 0)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Metoda care verifica daca unui videoclip i-a fost deja
     * data o nota de catre utilizator.
     * @param movie
     * titlul pentru care se face verificarea
     */
    public boolean isRated(final String movie) {
        int i;
        for (i = 0; i < this.ratedVideos.size(); i++) {
            if (this.ratedVideos.get(i).equals(movie)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Metoda prin care utilizatorul vizioneaza un videoclip,
     * adaugandu-l in istoric. Numarul de vizualizari se incrementeaza
     * pentru videoclipul urmarit.
     * @param show
     * videoclipul vizualizat
     * @param title
     * titlul videoclipului vizualizat
     */
    public int view(final String title, final MyShowInput show) {
        int value = 0;
        for (Map.Entry<String, Integer> i : history.entrySet()) {
            if (i.getKey().equals(title)) {
                value = i.getValue();
                value = value + 1;
                i.setValue(value);
                //show.noViews++;
                show.setNoViews(show.getNoViews() + 1);
                return value;
            }
        }
        value = 1;
        this.history.put(title, value);
        //show.noViews++;
        show.setNoViews(show.getNoViews() + 1);
        return value;
    }

    /**
     * Metoda prin care utilizatorul da o nota unui film,
     * adaugand filmul in lista utilizatorului cu videoclipuri deja notate,
     * iar campul ratingsGiven se incrementeaza. Dupa adaugarea notei,
     * se actualizeaza ratingul total al filmului.
     * @param movie
     * filmul care urmeaza a fi notat
     * @param grade
     * nota
     */
    public void rateMovie(final Double grade, final MyMovie movie) {

        ratedVideos.add(movie.getTitle());
        movie.getRatings().add(grade);
        movie.getRating();
        ratingsGiven++;
    }

    /**
     * Metoda prin care utilizatorul da o nota unui sezon dintr-un serial,
     * adaugandu-l in lista utilizatorului cu videoclipuri deja notate,
     * iar campul ratingsGiven se incrementeaza. Dupa adaugarea notei,
     * se actualizeaza ratingul total al serialului.
     * @param serial
     * serialul care urmeaza a fi notat
     * @param grade
     * nota
     * @param seasonNo
     * numarul sezonului notat
     */
    public void rateSerial(final Double grade, final int seasonNo,
                           final MySerialInput serial) {

        ratedVideos.add(serial.getTitle() + seasonNo);
        serial.getSeasons().get(seasonNo - 1).getRatings().add(grade);
        serial.getRating();
        ratingsGiven++;
    }

    /**
     * Metoda toString.
     */
    @Override
    public String toString() {
        return "UserInputData{" + "username='"
                + username + '\'' + ", subscriptionType='"
                + subscriptionType + '\'' + ", history="
                + history + ", favoriteMovies="
                + favoriteMovies + '}';
    }
}
