package myclasses;

import entertainment.Season;
import fileio.SerialInputData;

import java.util.ArrayList;

public class MySerialInput extends MyShowInput {

    public int numberOfSeasons;
    public ArrayList<Season> seasons;
    public Double ratingSerial = 0d; // Media aritmetica a tuturor mediilor sezoanelor
    public int serialDuration;

    public MySerialInput(final SerialInputData serial) {
        this.title = serial.getTitle();
        this.year = serial.getYear();
        this.cast = serial.getCast();
        this.genres = serial.getGenres();
        this.numberOfSeasons = serial.getNumberSeason();
        this.seasons = serial.getSeasons();
    }

    public MySerialInput(final MySerialInput serial) {
        this.title = serial.getTitle();
        this.year = serial.getYear();
        this.cast = serial.getCast();
        this.genres = serial.getGenres();
        this.numberOfSeasons = serial.getNumberSeason();
        this.seasons = serial.getSeasons();
        this.ratingSerial = serial.ratingSerial;
        this.rating = serial.rating;
        this.noViews = serial.noViews;
        this.serialDuration = serial.getSerialDuration();
        this.noFavorite = serial.noFavorite;
    }

    public int getNumberSeason() {
        return numberOfSeasons;
    }

    public ArrayList<Season> getSeasons() {
        return seasons;
    }

    public int getSerialDuration() {
        int duration = 0;
        for (Season i: seasons) {
            duration = i.getDuration();
        }
        return duration;
    }

    public Double getRating() {
        Double sumAllseasons = 0d;
        Double sumPerSeason = 0d;
        for (Season i : seasons) {
            sumPerSeason = 0d;
            if (i.getRatings().size() != 0) {
                for (int j = 0; j < i.getRatings().size(); j++) {
                    sumPerSeason = sumPerSeason + i.getRatings().get(j);
                }
                sumPerSeason = sumPerSeason / i.getRatings().size();
                sumAllseasons = sumAllseasons + sumPerSeason;
            }
        }
        sumAllseasons = sumAllseasons / numberOfSeasons;
        rating = sumAllseasons;
        return rating;

//        Double sumAllseasons = 0d;
//        Double sumPerSeason = 0d;
//        for (Season season : seasons) {
//            if (season.getRatings().size() != 0) {
//               for(Double rating : season.getRatings()) {
//                   sumPerSeason = sumPerSeason + rating / season.getRatings().size();
//               }
//               sumAllseasons = sumAllseasons + sumPerSeason / numberOfSeasons;
//            }
//        }
//        rating = sumAllseasons;
//        return rating;
    }

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
