package myclasses.CopyClasses;
import entertainment.Season;

import java.util.List;

public class MySeason {

    private int currentSeason;
    private int duration;
    private List<Double> ratings;


    /**
     * Copy-Constructor
     * @param season
     * sezon de tipul Season (clasa din scheletul temei)
     * @param currentSeason
     * sezonul curent
     */
    public MySeason(final Season season, final int currentSeason) {
        this.currentSeason = currentSeason;
        this.duration = season.getDuration();
        this.ratings = season.getRatings();
    }

    /**
     * Getter pentru campul currentSeason.
     */
    public int getCurrentSeason() {
        return currentSeason;
    }

    /**
     * Setter pentru campul currentSeason.
     */
    public void setCurrentSeason(final int currentSeason) {
        this.currentSeason = currentSeason;
    }

    /**
     * Getter pentru campul duration.
     */
    public int getDuration() {
        return duration;
    }

    /**
     * Setter pentru campul duration.
     */
    public void setDuration(final int duration) {
        this.duration = duration;
    }

    /**
     * Getter pentru campul ratings, ce reprezinta o lista cu toate
     * notele date sezonului.
     */
    public List<Double> getRatings() {
        return ratings;
    }

    /**
     * Setter pentru campul ratings.
     */
    public void setRatings(final List<Double> ratings) {
        this.ratings = ratings;
    }

    /**
     * Metoda toString.
     */
    @Override
    public String toString() {
        return "Episode{"
                + "currentSeason="
                + currentSeason
                + ", duration="
                + duration
                + '}';
    }

}
