package myclasses.Query;

import myclasses.CopyClasses.MyActor;
import myclasses.CopyClasses.MyMovie;
import myclasses.CopyClasses.MySerialInput;
import java.util.ArrayList;
import java.util.List;

public class QueryActors {

    /**
     * Metoda care initializeaza lista de actori care urmeaza a fi sortata ulterior
     * @param actors
     * lista de actori din database
     * @param actorList
     * lista noua de actori de tipul propriu in care vor fi adaugati actorii din database
     */
    public void initializeActors(final ArrayList<MyActor> actors,
                                     final ArrayList<MyActor> actorList) {
        for (MyActor j : actors) {
            actorList.add(j);
        }
    }

    /**
     * Metoda care apeleaza metoda de aflare a mediei actorilor specifica acestora
     * si sorteaza crescator lista in functie de acest criteriu. Am suprascris
     * metoda sort a interfetei List pentru a sorta crescator dupa media actorului,
     * iar apoi crescator alfabetic.
     * @param movies
     * lista de filme din database
     * @param serials
     * lista de seriale din database
     * @param actorList
     * lista de actori ce va fi sortata
     * @param number
     * primii "number" actori ce se doresc afisati
     */
    public String ascAverage(final ArrayList<MyActor> actorList,
                                   final int number,
                                   final ArrayList<MyMovie> movies,
                                   final ArrayList<MySerialInput> serials) {
        int j;
        StringBuilder message;
        int count = number;

        actorList.sort((v1, v2) -> {
            if (v1.getActorAverage(movies, serials).equals(v2.getActorAverage(movies, serials))) {
                return v1.getName().compareTo(v2.getName());
            } else {
                return Double.compare(v1.getActorAverage(movies, serials),
                        v2.getActorAverage(movies, serials));
            }
        });

        message = new StringBuilder("Query result: [");

        j = 0;
        while (j < actorList.size() && count != 0) {
            if ((actorList.get(j).getActorAverage(movies, serials)) != 0d) {
                message.append(actorList.get(j).getName());
                if ((j != actorList.size() - 1) && (count > 1)) {
                    message.append(", ");
                }
                j++;
                count--;
            } else {
                j++;
            }
        }

        message.append("]");

        return message.toString();
    }

    /**
     * Metoda care apeleaza metoda de aflare a mediei actorilor specifica acestora
     * si sorteaza crescator lista in functie de acest criteriu. Am suprascris
     * metoda sort a interfetei List pentru a sorta descrescator dupa media actorului,
     * iar apoi descrescator alfabetic.
     * @param movies
     * lista de filme din database
     * @param serials
     * lista de seriale din database
     * @param actorList
     * lista de actori ce va fi sortata
     * @param number
     * primii "number" actori ce se doresc afisati
     */
    public String descAverage(final ArrayList<MyActor> actorList,
                                    final int number,
                                    final ArrayList<MyMovie> movies,
                                    final ArrayList<MySerialInput> serials) {
        int j;
        StringBuilder message;
        int count = number;

        actorList.sort((v1, v2) -> {
            if (v1.getActorAverage(movies, serials).equals(v2.getActorAverage(movies, serials))) {
                return v2.getName().compareTo(v1.getName());
            } else {
                return Double.compare(v2.getActorAverage(movies, serials),
                        v1.getActorAverage(movies, serials));
            }
        });

        int end;
        for (end = 0; end < actorList.size(); end++) {
            if (actorList.get(end).getActorAverage(movies, serials) == 0d) {
                break;
            }
        }

        message = new StringBuilder("Query result: [");

        j = 0;
        while (j < end && count != 0) {
            if ((actorList.get(j).getActorAverage(movies, serials)) != 0d) {
                message.append(actorList.get(j).getName());
                if ((j != end - 1) && (count > 1)) {
                    message.append(", ");
                }
                j++;
                count--;
            } else {
                j++;
            }
        }

        message.append("]");

        return message.toString();
    }

    /**
     * Metoda care filtreaza doar actorii care au toate premiile
     * primite ca parametru, apeleaza metoda specifica actorilor de
     * a obtine numarul total de premii pentru fiecare, sortarea
     * fiind facuta crescator dupa acest criteriu, apoi crescator
     * alfabetic.
     * @param actors
     * lista cu actori din database
     * @param awards
     * lista cu premii din database
     */
    public String ascAwards(final ArrayList<MyActor> actors,
                                  final List<String> awards) {

        StringBuilder message;
        ArrayList<MyActor> actorList = new ArrayList<MyActor>();

        for (MyActor i : actors) {
            if (i.hasAllAwards(awards)) {
                actorList.add(i);
            }
        }

        if (actorList.size() == 0) {
            message = new StringBuilder("Query result: []");
            return message.toString();
        }

        actorList.sort((v1, v2) -> {
            if (v1.getAwardsNumber() == v2.getAwardsNumber()) {
                return v1.getName().compareTo(v2.getName());
            } else {
                return Integer.compare(v1.getAwardsNumber(), v2.getAwardsNumber());
            }
        });

        message = new StringBuilder("Query result: [");

        for (int i = 0; i < actorList.size(); i++) {
            message.append(actorList.get(i).getName());
            if (i != (actorList.size() - 1)) {
                message.append(", ");
            }
        }
        message.append("]");

        return message.toString();
    }

    /**
     * Metoda care filtreaza doar actorii care au toate premiile
     * primite ca parametru, apeleaza metoda specifica actorilor de
     * a obtine numarul total de premii pentru fiecare, sortarea
     * fiind facuta descrescator dupa acest criteriu, apoi descrescator
     * alfabetic.
     * @param actors
     * lista cu actori din database
     * @param awards
     * lista cu premii din database
     */
    public String descAwards(final ArrayList<MyActor> actors,
                                   final List<String> awards) {

        StringBuilder message;
        ArrayList<MyActor> actorList = new ArrayList<MyActor>();

        for (MyActor i : actors) {
            if (i.hasAllAwards(awards)) {
                actorList.add(i);
            }
        }

        if (actorList.size() == 0) {
            message = new StringBuilder("Query result: []");
            return message.toString();
        }

        actorList.sort((v1, v2) -> {
            if (v1.getAwardsNumber() == v2.getAwardsNumber()) {
                return v2.getName().compareTo(v1.getName());
            } else {
                return Integer.compare(v2.getAwardsNumber(), v1.getAwardsNumber());
            }
        });

        message = new StringBuilder("Query result: [");

        for (int i = 0; i < actorList.size(); i++) {
            message.append(actorList.get(i).getName());
            if (i != (actorList.size() - 1)) {
                message.append(", ");
            }
        }
        message.append("]");

        return message.toString();

    }

    /**
     * Metoda care filtreaza doar actorii care au toate cuvintele
     * cheie in descrierea acestora, fiind apelata o metoda
     * caracteristica actorilor. Acestia urmeaza a fi sortati
     * crescator alfabetic dupa nume.
     * @param actors
     * lista cu actori din database
     * @param words
     * lista cu cuvintele cheie din database
     */
    public String ascDescription(final ArrayList<MyActor> actors,
                                       final List<String> words) {

        StringBuilder message;
        ArrayList<MyActor> actorList = new ArrayList<MyActor>();

        for (MyActor i : actors) {
            if (i.hasAllKeywords(words)) {
                actorList.add(i);
            }
        }

        if (actorList.size() == 0) {
            message = new StringBuilder("Query result: []");
            return message.toString();
        }

        actorList.sort((v1, v2) -> {
            return v1.getName().compareTo(v2.getName());
        });

        message = new StringBuilder("Query result: [");

        for (int i = 0; i < actorList.size(); i++) {
            message.append(actorList.get(i).getName());
            if (i != (actorList.size() - 1)) {
                message.append(", ");
            }
        }
        message.append("]");

        return message.toString();

    }

    /**
     * Metoda care filtreaza doar actorii care au toate cuvintele
     * cheie in descrierea acestora, fiind apelata o metoda
     * caracteristica actorilor. Acestia urmeaza a fi sortati
     * descrescator alfabetic dupa nume.
     * @param actors
     * lista cu actori din database
     * @param words
     * lista cu cuvintele cheie din database
     */
    public String descDescription(final ArrayList<MyActor> actors,
                                        final List<String> words) {

        StringBuilder message;
        ArrayList<MyActor> actorList = new ArrayList<MyActor>();

        for (MyActor i : actors) {
            if (i.hasAllKeywords(words)) {
                actorList.add(i);
            }
        }

        if (actorList.size() == 0) {
            message = new StringBuilder("Query result: []");
            return message.toString();
        }

        actorList.sort((v1, v2) -> {
            return v2.getName().compareTo(v1.getName());
        });

        message = new StringBuilder("Query result: [");

        for (int i = 0; i < actorList.size(); i++) {
            message.append(actorList.get(i).getName());
            if (i != (actorList.size() - 1)) {
                message.append(", ");
            }
        }
        message.append("]");

        return message.toString();

    }


}
