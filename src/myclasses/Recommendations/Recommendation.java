package myclasses.Recommendations;

import myclasses.CopyClasses.MyUser;
import myclasses.CopyClasses.MyShowInput;
import myclasses.CopyClasses.MySerialInput;
import myclasses.CopyClasses.MyMovie;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Recommendation {

    /**
     * Metoda care gaseste si returneaza utilizatorul cu numele
     * dat ca parametru.
     * @param users
     * lista de utilizatori din database
     * @param username
     * numele utilizatorului care va fi cautat in lista
     */
    public MyUser getUser(final ArrayList<MyUser> users, final String username) {
        MyUser j = users.get(0);
        for (MyUser i : users) {
            if (i.getUsername().equals(username)) {
                return i;
            }
        }
        return j;
    }

    /**
     * Metoda care primeste o lista cu videoclipuri. Se creeaza un HashMap
     * care are ca si cheie genul videoclipului, iar ca valoare, numarul
     * total de vizualizari al respectivului gen. Se parcurg toate videoclipurile
     * si se initializeaza HashMap-ul , iar apoi se sorteaza descrescator dupa valoare,
     * adica dupa numarul de vizualizari al fiecarui gen.
     * @param videos
     * lista cu videoclipuri
     */
    public HashMap<String, Integer> getGenremap(final ArrayList<MyShowInput> videos) {
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        for (MyShowInput i : videos) {
            for (String j : i.getGenres()) {
                if (!map.containsKey(j)) {
                    map.put(j, i.getNoViews());
                } else {
                    map.put(j, map.get(j) + i.getNoViews());
                }
            }
        }

        List<HashMap.Entry<String, Integer>> list =
                new LinkedList<HashMap.Entry<String, Integer>>(map.entrySet());

        Collections.sort(list, new Comparator<HashMap.Entry<String, Integer>>() {
            public int compare(final HashMap.Entry<String, Integer> v1,
                               final HashMap.Entry<String, Integer> v2) {
                if (v2.getValue().equals(v1.getValue())) {
                    return v2.getKey().compareTo(v1.getKey());
                } else {
                    return Integer.compare(v2.getValue(), v1.getValue());
                }
            }
        });

        HashMap<String, Integer> newMap = new LinkedHashMap<String, Integer>();
        for (Map.Entry<String, Integer> i : list) {
            newMap.put(i.getKey(), i.getValue());
        }

        return newMap;
    }

    /**
     * Metoda care se ajuta de metoda utilizatorului pentru a
     * putea afisa primul videoclip nevizualizat din database.
     * @param movies
     * lista de filme din database
     * @param serials
     * lista de seriale din database
     * @param username
     * numele utilizatorului pentru care se face recomandarea
     * @param users
     * lista de utilizatori din database
     */
    public String recommendationStandard(final ArrayList<MyUser> users,
                           final String username,
                           final ArrayList<MyMovie> movies,
                           final ArrayList<MySerialInput> serials) {

        String message = "";

        MyUser user = new MyUser(getUser(users, username));

        for (MyMovie i : movies) {
            if (!user.wasWatched(i.getTitle())) {
                message = "StandardRecommendation result: " + i.getTitle();
                return message;
            }
        }

        for (MySerialInput i : serials) {
            if (!user.wasWatched(i.getTitle())) {
                message = "StandardRecommendation result: " + i.getTitle();
                return message;
            }
        }

        message = "StandardRecommendation cannot be applied!";
        return message;
    }

    /**
     * Metoda care adauga toate filmele si serialele intr-o lista cu videoclipuri
     * de tipul MyShowInput, pe care o sorteaza descrescator dupa ratingul
     * clipului. Se va afisa videoclipul cel mai bine notat si care nu a fost
     * vazut de catre utilizatorul pentru care se face recomandarea.
     * @param movies
     * lista de filme din database
     * @param serials
     * lista de seriale din database
     * @param username
     * numele utilizatorului pentru care se face recomandarea
     * @param users
     * lista de utilizatori din database
     */
    public String bestUnseen(final ArrayList<MyUser> users,
                             final String username,
                             final ArrayList<MyMovie> movies,
                             final ArrayList<MySerialInput> serials) {

        StringBuilder message;

        ArrayList<MyShowInput> videos = new ArrayList<MyShowInput>();
        for (MyMovie i : movies) {
            videos.add(i);
        }
        for (MySerialInput i : serials) {
            videos.add(i);
        }

        videos.sort((v1, v2) -> Double.compare(v2.getRating(), v1.getRating()));

        MyUser user = new MyUser(getUser(users, username));

        int i = 0;
        int ok = 0;
        while (i < videos.size() && ok == 0) {
            if (!user.wasWatched(videos.get(i).getTitle())) {
                ok = 1;
            } else {
                i++;
            }
        }

        if (ok == 1) {
            message = new StringBuilder("BestRatedUnseenRecommendation result: ");
            message.append(videos.get(i).getTitle());
        } else {
            message = new StringBuilder("BestRatedUnseenRecommendation cannot be applied!");
        }
        return message.toString();
    }

    /**
     * Metoda care adauga toate filmele si serialele dintr-un gen specific si care nu
     * au fost vazute inca de catre utilizator intr-o lista cu videoclipuri
     * de tipul MyShowInput. Aceasta lista va fi sortata crescator dupa rating si
     * alfabetic si va fi afisata, recomandarea fiind aplicata numai in cazul in care
     * utilizatorul are o subscriptie de tip "PREMIUM".
     * @param movies
     * lista de filme din database
     * @param serials
     * lista de seriale din database
     * @param username
     * numele utilizatorului pentru care se face recomandarea
     * @param users
     * lista de utilizatori din database
     * @param genre
     * genul cerut
     */
    public String recommendationSearch(final ArrayList<MyUser> users,
                                       final String genre,
                                       final String username,
                                       final ArrayList<MyMovie> movies,
                                       final ArrayList<MySerialInput> serials) {

        StringBuilder message;

        ArrayList<MyShowInput> videos = new ArrayList<MyShowInput>();
        MyUser user = new MyUser(getUser(users, username));

        for (MyMovie i : movies) {
            if ((i.hasGenre(genre) && (!user.wasWatched(i.getTitle())))) {
                videos.add(i);
            }
        }
        for (MySerialInput i : serials) {
            if ((i.hasGenre(genre) && (!user.wasWatched(i.getTitle())))) {
                videos.add(i);
            }
        }

        if (videos.size() == 0) {
            message = new StringBuilder("SearchRecommendation cannot be applied!");
            return message.toString();
        }

        if (user.getSubscriptionType().equals("BASIC")) {
            message = new StringBuilder("SearchRecommendation cannot be applied!");
            return message.toString();
        }

        message = new StringBuilder("SearchRecommendation result: [");

        videos.sort((v1, v2) -> {
            if (v1.getRating().equals(v2.getRating())) {
                return v1.getTitle().compareTo(v2.getTitle());
            } else {
                return Double.compare(v1.getRating(), v2.getRating());
            }

        });

        for (int i = 0; i < videos.size(); i++) {
            message.append(videos.get(i).getTitle());
            if (i < videos.size() - 1) {
                message.append(", ");
            }
        }
        message.append("]");
        return message.toString();
    }


    /**
     * Metoda care adauga toate filmele si serialele nevazute de
     * utilizator intr-o lista cu videoclipuri de tipul MyShowInput,
     * pe care o sorteaza descrescator dupa numarul de favorite ale clipului. Se va afisa
     * videoclipul cel mai des intalnit in lista de favorite ale tuturor utilizatorilor si
     * care nu a fost vazut de catre utilizatorul pentru care se face recomandarea.
     * @param movies
     * lista de filme din database
     * @param serials
     * lista de seriale din database
     * @param username
     * numele utilizatorului pentru care se face recomandarea
     * @param users
     * lista de utilizatori din database
     */
    public String recommendationFavorite(final ArrayList<MyUser> users,
                                         final String username,
                                         final ArrayList<MyMovie> movies,
                                         final ArrayList<MySerialInput> serials) {

        StringBuilder message;

        ArrayList<MyShowInput> videos = new ArrayList<MyShowInput>();
        MyUser user = new MyUser(getUser(users, username));

        for (MyMovie i : movies) {
            if (!user.wasWatched(i.getTitle())) {
                videos.add(i);
            }
        }
        for (MySerialInput i : serials) {
            if (!user.wasWatched(i.getTitle())) {
                videos.add(i);
            }
        }

        if (videos.size() == 0) {
            message = new StringBuilder("FavoriteRecommendation cannot be applied!");
            return message.toString();
        }

        if (user.getSubscriptionType().equals("BASIC")) {
            message = new StringBuilder("FavoriteRecommendation cannot be applied!");
            return message.toString();
        }

        message = new StringBuilder("FavoriteRecommendation result: ");

        videos.sort((v1, v2) -> Double.compare(v2.getNoFavorite(), v1.getNoFavorite()));

        message.append(videos.get(0).getTitle());
        return message.toString();
    }

    /**
     * Metoda care adauga toate filmele si serialele nevazute de utilizator
     * intr-o lista de videoclipuri. Se va apela metoda "getGenremap" care
     * va intoarce un HashMap sortat descrescator dupa cel mai popular gen.
     * Se va parcurge lista de videoclipuri, pana cand se va gasi unul din cel mai popular gen,
     * pe care utilizatorul nu il vizionase deja, acesta fiind afisat.
     * @param movies
     * lista de filme din database
     * @param serials
     * lista de seriale din database
     * @param username
     * numele utilizatorului pentru care se face recomandarea
     * @param users
     * lista de utilizatori din database
     */
    public String recommendationPopular(final ArrayList<MyUser> users,
                                         final String username,
                                         final ArrayList<MyMovie> movies,
                                         final ArrayList<MySerialInput> serials) {

        StringBuilder message;

        ArrayList<MyShowInput> videos = new ArrayList<MyShowInput>();
        MyUser user = new MyUser(getUser(users, username));

        for (MyMovie i : movies) {
            if (!user.wasWatched(i.getTitle())) {
                videos.add(i);
            }
        }
        for (MySerialInput i : serials) {
            if (!user.wasWatched(i.getTitle())) {
                videos.add(i);
            }
        }

        if (videos.size() == 0) {
            message = new StringBuilder("PopularRecommendation cannot be applied!");
            return message.toString();
        }

        if (user.getSubscriptionType().equals("BASIC")) {
            message = new StringBuilder("PopularRecommendation cannot be applied!");
            return message.toString();
        }

        message = new StringBuilder("PopularRecommendation result: ");

        HashMap<String, Integer> map = new HashMap<String, Integer>();
        map = getGenremap(videos);

        for (HashMap.Entry<String, Integer> entry : map.entrySet()) {
            for (MyShowInput i : videos) {
                if ((i.hasGenre(entry.getKey())) && (!user.wasWatched(i.getTitle()))) {
                    message.append(i.getTitle());
                    return message.toString();
                }
            }
        }


        return message.toString();
    }

}

