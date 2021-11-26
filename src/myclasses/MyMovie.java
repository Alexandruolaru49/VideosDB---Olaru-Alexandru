package myclasses;

import fileio.MovieInputData;

import java.util.ArrayList;

public class MyMovie extends MyShowInput {

    private int duration;
    private ArrayList<Double> ratings = new ArrayList<>();
    private Double ratingMovie = 0d; // Media aritmetica a tuturor notelor


    /**
     * Copy-Constructor
     * @param movie
     * film de tipul MovieInputData (clasa din scheletul temei)
     */
    public MyMovie(final MovieInputData movie) {

        this.setTitle(movie.getTitle());
        this.setYear(movie.getYear());
        this.setCast(movie.getCast());
        this.setGenres(movie.getGenres());
        this.setDuration(movie.getDuration());
    }

    /**
     * Copy-Constructor
     * @param movie
     * film de tipul propriu creat
     */
    public MyMovie(final MyMovie movie) {

        this.setTitle(movie.getTitle());
        this.setYear(movie.getYear());
        this.setCast(movie.getCast());
        this.setGenres(movie.getGenres());
        this.setDuration(movie.getDuration());
        this.setRatings(movie.getRatings());
        this.setRatingMovie(movie.getRatingMovie());
        this.setRating(movie.getRating());
        this.setNoFavorite(movie.getNoFavorite());
        this.setNoViews(movie.getNoViews());

    }

    /**
     * Getter pentru campul ratingMovie.
     */
    public Double getRatingMovie() {
        return ratingMovie;
    }

    /**
     * Setter pentru campul ratingMovie.
     */
    public void setRatingMovie(final Double ratingMovie) {
        this.ratingMovie = ratingMovie;
    }

    /**
     * Getter pentru campul ratings.
     */
    public ArrayList<Double> getRatings() {
        return ratings;
    }

    /**
     * Setter pentru campul ratings.
     */
    public void setRatings(final ArrayList<Double> ratings) {
        this.ratings = ratings;
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
     * Metoda care calculeaza ratingul final al unui film,
     * reprezentand media aritmetica a tuturor notelor primite.
     */
    public Double getRating() {
        Double sum = 0d;
        for (int i = 0; i < this.ratings.size(); i++) {
            sum = sum + this.ratings.get(i);
        }
        if (this.ratings.size() != 0) {
            this.ratingMovie = (sum / this.ratings.size());
        }

        setRating(ratingMovie);
        return ratingMovie;
    }

    /**
     * Metoda toString.
     */
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
