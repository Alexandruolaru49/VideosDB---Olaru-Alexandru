package myclasses;

import fileio.MovieInputData;

import java.util.ArrayList;

public class MyMovie extends  MyShowInput{

    public int duration;
    public ArrayList<Double> ratings = new ArrayList<>();
    public Double ratingMovie = 0d; // Media aritmetica a tuturor notelor


    public MyMovie(final MovieInputData movie) {
        this.title = movie.getTitle();
        this.year = movie.getYear();
        this.cast = movie.getCast();
        this.genres = movie.getGenres();
        this.duration = movie.getDuration();
    }

    public MyMovie(final MyMovie movie) {
        this.title = movie.getTitle();
        this.year = movie.getYear();
        this.cast = movie.getCast();
        this.genres = movie.getGenres();
        this.duration = movie.getDuration();
        this.ratings = movie.ratings;
        this.ratingMovie = movie.ratingMovie;
        this.rating = movie.rating;
        this.noFavorite = movie.noFavorite;
        this.noViews = movie.noViews;
    }

    public int getDuration() {
        return duration;
    }

    public Double getRating() {
        Double sum = 0d;
        for (int i = 0; i < this.ratings.size(); i++) {
            sum = sum + this.ratings.get(i);
        }
        if (this.ratings.size() != 0) {
            this.ratingMovie = (sum / this.ratings.size());
        }
        rating = ratingMovie;
        return rating;
    }

    @Override
    public String toString() {
        return "MovieInputData{" + "title= "
                + super.getTitle() + "year= "
                + super.getYear() + "duration= "
                + duration + "cast {"
                + super.getCast() + " }\n"
                + "genres {" + super.getGenres() + " }\n ";
    }
}
