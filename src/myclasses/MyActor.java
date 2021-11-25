package myclasses;

import actor.ActorsAwards;
import fileio.ActorInputData;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MyActor {

    private String name;
    private String careerDescription;
    private ArrayList<String> filmography;
    public Map<ActorsAwards, Integer> awards;
    public Double actorAverage = 0d;
    public int awardsNumber = 0;

    public MyActor(final ActorInputData actor) {
        this.name = actor.getName();
        this.careerDescription = actor.getCareerDescription();
        this.filmography = actor.getFilmography();
        this.awards = actor.getAwards();
    }

    public MyActor(final MyActor actor) {
        this.name = actor.getName();
        this.careerDescription = actor.getCareerDescription();
        this.filmography = actor.getFilmography();
        this.awards = actor.getAwards();
        this.actorAverage = actor.actorAverage;
        this.awardsNumber = actor.awardsNumber;
    }

    public Double getActorAverage(final ArrayList<MyMovie> movies,
                                  final ArrayList<MySerialInput> serials) {
        int count = 0;
        Double sum = 0d;
        for (MyMovie i : movies) {
            if (this.isInMovie(i)) {
//                if (i.getRatingMovie() != 0d) {
//                    count++;
//                    sum = sum + i.getRatingMovie();
//                }
                if (i.getRating() != 0d) {
                    count++;
                    sum = sum + i.getRating();
                }
            }
        }
        for (MySerialInput i : serials) {
            if (this.isInSerial(i)) {
//                if(i.getRatingSerial() != 0d) {
//                    count++;
//                    sum = sum + i.getRatingSerial();
//                }
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

    public boolean isInMovie(final MyMovie movie) {
        for (String i : filmography) {
            if (i.equals(movie.title)) {
                return true;
            }
        }
        return false;
    }

    public boolean isInSerial(final MySerialInput serial) {
        for (String i : filmography) {
            if (i.equals(serial.title)) {
                return true;
            }
        }
        return false;
    }

    public boolean hasAllAwards(final List<String> awards) {
        int nr = 0;
        for (String s : awards) {
            for (Map.Entry<ActorsAwards, Integer> i : this.awards.entrySet()) {
                if (s.equals(i.getKey())) {
                    nr++;
                }
            }
        }
        if (nr == awards.size()) {
            return true;
        }
        return false;
    }

    public boolean hasAllKeywords(final List<String> words) {
        int nr = 0;
        for (String s : words) {
            if (careerDescription.toLowerCase().contains(s.toLowerCase())) {
                nr++;
            }
        }
        if (nr == words.size()) {
            return true;
        }
        return false;
    }

    public int getAwardsNumber() {
        int sum = 0;
        for (Map.Entry<ActorsAwards, Integer> i : this.awards.entrySet()) {
            sum = sum + i.getValue();
        }
        awardsNumber = sum;
        return awardsNumber;
    }


    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public ArrayList<String> getFilmography() {
        return filmography;
    }

    public void setFilmography(final ArrayList<String> filmography) {
        this.filmography = filmography;
    }

    public Map<ActorsAwards, Integer> getAwards() {
        return awards;
    }

    public String getCareerDescription() {
        return careerDescription;
    }

    public void setCareerDescription(final String careerDescription) {
        this.careerDescription = careerDescription;
    }

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
