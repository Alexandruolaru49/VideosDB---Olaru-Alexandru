package myclasses;

import entertainment.Season;
import fileio.SerialInputData;

import java.util.ArrayList;

public class MySerialInput extends MyShowInput {

    private int numberOfSeasons;
    private ArrayList<Season> seasons;
    private Double ratingSerial = 0d;
    private int serialDuration;

    /**
     * Copy-Constructor
     * @param serial
     * serial de tip SerialInputData (clasa din scheletul temei)
     */
    public MySerialInput(final SerialInputData serial) {

        this.setTitle(serial.getTitle());
        this.setYear(serial.getYear());
        this.setCast(serial.getCast());
        this.setGenres(serial.getGenres());
        this.setNumberOfSeasons(serial.getNumberSeason());
        this.setSeasons(serial.getSeasons());
    }

    /**
     * Copy-Constructor
     * @param serial
     * serial de tipul propriu creat
     */
    public MySerialInput(final MySerialInput serial) {

        this.setTitle(serial.getTitle());
        this.setYear(serial.getYear());
        this.setCast(serial.getCast());
        this.setGenres(serial.getGenres());
        this.setNumberOfSeasons(serial.getNumberOfSeasons());
        this.setSeasons(serial.getSeasons());
        this.setRatingSerial(serial.getRatingSerial());
        this.setRating(serial.getRating());
        this.setNoViews(serial.getNoViews());
        this.setSerialDuration(serial.getSerialDuration());
        this.setNoFavorite(serial.getNoFavorite());
    }

    /**
     * Getter pentru campul numberOfSeasons.
     */
    public int getNumberOfSeasons() {
        return numberOfSeasons;
    }
    /**
     * Setter pentru campul numberOfSeasons.
     */
    public void setNumberOfSeasons(final int numberOfSeasons) {
        this.numberOfSeasons = numberOfSeasons;
    }

    /**
     * Setter pentru campul seasons.
     */
    public void setSeasons(final ArrayList<Season> seasons) {
        this.seasons = seasons;
    }

    /**
     * Getter pentru campul ratingSerial.
     */
    public Double getRatingSerial() {
        return ratingSerial;
    }

    /**
     * Setter pentru campul ratingSerial.
     */
    public void setRatingSerial(final Double ratingSerial) {
        this.ratingSerial = ratingSerial;
    }

    /**
     * Setter pentru campul serialDuration.
     */
    public void setSerialDuration(final int serialDuration) {
        this.serialDuration = serialDuration;
    }

    /**
     * Getter pentru campul numberOfSeasons.
     */
    public int getNumberSeason() {
        return numberOfSeasons;
    }

    /**
     * Getter pentru campul seasons.
     */
    public ArrayList<Season> getSeasons() {
        return seasons;
    }

    /**
     * Metoda care calculeaza durata unui intreg serial.
     */
    public int getSerialDuration() {
        int duration = 0;
        for (Season i: seasons) {
            duration = duration + i.getDuration();
        }
        serialDuration = duration;
        return serialDuration;
    }

    /**
     * Metoda care calculeaza ratingul final al intregului serial
     */
    public Double getRating() {
        Double sumAllseasons = 0d;
        Double sumPerSeason = 0d;
        for (Season season : seasons) {
            if (season.getRatings().size() != 0) {
               for (Double rating : season.getRatings()) {
                   sumPerSeason = sumPerSeason + rating / season.getRatings().size();
               }
               sumAllseasons = sumAllseasons + sumPerSeason / numberOfSeasons;
            }
        }
        setRating(sumAllseasons);
        ratingSerial = sumAllseasons;

        return ratingSerial;
    }

    /**
     * Metoda toString.
     */
    @Override
    public String toString() {
        return "SerialInputData{" + " title= "
                + super.getTitle() + " " + " year= "
                + super.getYear() + " cast {"
                + super.getCast() + " }\n" + " genres {"
                + super.getGenres() + " }\n "
                + " numberSeason= " + numberOfSeasons
                + ", seasons=" + seasons + "\n\n" + '}';
    }
}
