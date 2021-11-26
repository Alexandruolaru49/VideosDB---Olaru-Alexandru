package myclasses;

import actor.ActorsAwards;
import fileio.ActorInputData;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import utils.Utils;

public class MyActor {

    private String name;
    private String careerDescription;
    private ArrayList<String> filmography;
    private Map<ActorsAwards, Integer> awards;
    private Double actorAverage = 0d;
    private int awardsNumber = 0;

    /**
     * Copy-Constructor
     * @param actor
     * actor de tipul ActorInputData (clasa din scheletul temei)
     */
    public MyActor(final ActorInputData actor) {
        this.name = actor.getName();
        this.careerDescription = actor.getCareerDescription();
        this.filmography = actor.getFilmography();
        this.awards = actor.getAwards();
    }

    /**
     * Copy-Constructor
     * @param actor
     * actor de tipul MyActor (clasa proprie)
     */
    public MyActor(final MyActor actor) {
        this.name = actor.getName();
        this.careerDescription = actor.getCareerDescription();
        this.filmography = actor.getFilmography();
        this.awards = actor.getAwards();
        this.actorAverage = actor.actorAverage;
        this.awardsNumber = actor.awardsNumber;
    }

    /**
     * Metoda care parcurge listele cu filme si seriale, verifica daca actorul
     * joaca in acestea, iar apoi calculeaza media sa, in functie de ratingurile
     * videoclipurilor.
     * @param movies
     * lista cu filme din database
     * @param serials
     * lista cu seriale din database
     */
    public Double getActorAverage(final ArrayList<MyMovie> movies,
                                  final ArrayList<MySerialInput> serials) {
        int count = 0;
        Double sum = 0d;
        for (MyMovie i : movies) {
            if (this.isInMovie(i)) {
                if (i.getRating() != 0d) {
                    count++;
                    sum = sum + i.getRating();
                }
            }
        }
        for (MySerialInput i : serials) {
            if (this.isInSerial(i)) {
                if (i.getRating() != 0d) {
                    count++;
                    sum = sum + i.getRating();
                }
            }
        }

        if (count != 0) {
            actorAverage = sum / count;
        }

        return actorAverage;
    }

    /**
     * Setter pentru campul actorAverage.
     */
    public void setActorAverage(final Double actorAverage) {
        this.actorAverage = actorAverage;
    }

    /**
     * Metoda care verifica daca actorul apare in distributia filmului.
     * @param movie
     * film
     */
    public boolean isInMovie(final MyMovie movie) {
        for (String i : filmography) {
            if (i.equals(movie.getTitle())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Metoda care verifica daca actorul apare in distributia serialuli.
     * @param serial
     * serial
     */
    public boolean isInSerial(final MySerialInput serial) {
        for (String i : filmography) {
            if (i.equals(serial.getTitle())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Metoda care verifica daca actorul are toate premiile
     * cerute (cele care sunt date ca parametru).
     * @param premii
     * lista cu premii
     */
    public boolean hasAllAwards(final List<String> premii) {
        int nr = 0;
        for (String s : premii) {
            for (Map.Entry<ActorsAwards, Integer> i : this.awards.entrySet()) {
                if (Utils.stringToAwards(s).equals(i.getKey())) {
                    nr++;
                }
            }
        }
        if (nr == premii.size()) {
            return true;
        }
        return false;
    }

    /**
     * Metoda care verifica daca actorul are in descrierea sa
     * toate cuvintele cheie date ca parametru.
     * @param words
     * lista cu cuvinte-cheie
     */
    public boolean hasAllKeywords(final List<String> words) {
        int nr = 0;
        String[] description = getCareerDescription().toLowerCase().split("[, -.() ']+");
        for (String s : words) {
            for (String i : description) {
                if (i.equals(s)) {
                    nr++;
                    break;
                }
            }
        }
        if (nr == words.size()) {
            return true;
        }
        return false;
    }

    /**
     * Metoda care intoarce numarul total de premii ale actorului
     */
    public int getAwardsNumber() {
        int sum = 0;
        for (Map.Entry<ActorsAwards, Integer> i : this.awards.entrySet()) {
            sum = sum + i.getValue();
        }
        awardsNumber = sum;
        return awardsNumber;
    }

    /**
     * Setter pentru campul awardsNumber.
     */
    public void setAwardsNumber(final int awardsNumber) {
        this.awardsNumber = awardsNumber;
    }

    /**
     * Getter pentru campul name.
     */
    public String getName() {
        return name;
    }

    /**
     * Setter pentru campul name.
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * Getter pentru campul filmography.
     */
    public ArrayList<String> getFilmography() {
        return filmography;
    }

    /**
     * Setter pentru campul filmography.
     */
    public void setFilmography(final ArrayList<String> filmography) {
        this.filmography = filmography;
    }

    /**
     * Getter pentru campul awards.
     */
    public Map<ActorsAwards, Integer> getAwards() {
        return awards;
    }

    /**
     * Setter pentru campul awards.
     */
    public void setAwards(final Map<ActorsAwards, Integer> awards) {
        this.awards = awards;
    }

    /**
     * Getter pentru campul careerDescription.
     */
    public String getCareerDescription() {
        return careerDescription;
    }

    /**
     * Setter pentru campul careerDescription.
     */
    public void setCareerDescription(final String careerDescription) {
        this.careerDescription = careerDescription;
    }

    /**
     * Metoda toString
     */
    @Override
    public String toString() {
        return "MyActor{"
                + "name='"
                + name
                + '\''
                + ", careerDescription='"
                + careerDescription
                + '\''
                + ", filmography="
                + filmography
                + ", awards="
                + awards
                + '}';
    }
}
