package myclasses;
import entertainment.Season;

import java.util.List;

public class MySeason {

    public int currentSeason;
    public int duration;
    public List<Double> ratings; // ratingurile tuturor userilor pentru un sezon dintr-un serial


    public MySeason(final Season season, final int currentSeason) {
        this.currentSeason = currentSeason;
        this.duration = season.getDuration();
        this.ratings = season.getRatings();
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(final int duration) {
        this.duration = duration;
    }

    public List<Double> getRatings() {
        return ratings;
    }

    public void setRatings(final List<Double> ratings) {
        this.ratings = ratings;
    }

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
