package myclasses.Query;

import myclasses.MyActor;
import myclasses.MyMovie;
import myclasses.MySerialInput;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class QueryActors {

    public void InitializeActorsList(final ArrayList<MyActor> actors,
                                     final ArrayList<MyActor> actorList) {
        for (MyActor j : actors) {
            actorList.add(j);
        }
    }

    public String AscSortByAverage(final ArrayList<MyActor> actorList,
                                   final int number,
                                   final ArrayList<MyMovie> movies,
                                   final ArrayList<MySerialInput> serials) {
        int j, k;
        MyActor aux;
        StringBuilder message;
        int count = number;

        Collections.sort(actorList, new Comparator<MyActor>() {
            public int compare(final MyActor v1, final MyActor v2) {
                return v1.getName().compareTo(v2.getName());
            }
        });

        Collections.sort(actorList, Comparator.comparing(MyActor::getName));

        for (j = 0; j < actorList.size() - 1; j++) {
            for (k = j + 1; k < actorList.size(); k++) {
                if ((actorList.get(j).getActorAverage(movies, serials)) > (actorList.get(k).getActorAverage(movies, serials))) {
                    aux = new MyActor(actorList.get(j));
                    actorList.set(j, actorList.get(k));
                    actorList.set(k, aux);
                }
            }
        }

        message = new StringBuilder("Query result: [");
        j = 0;
        while (j < actorList.size() && count != 0) {
            if ((actorList.get(j).getActorAverage(movies, serials)) != 0d) {
                message.append(actorList.get(j).getName());
                if ((j != actorList.size() - 1) && (count > 1)) { //  AICI!!!!!
                    message.append(", ");
                }
                j++;
                count--;
            }
            else {
                j++;
            }
        }

        message.append("]");

        return message.toString();
    }


    public String DescSortByAverage(final ArrayList<MyActor> actorList,
                                    final int number,
                                    final ArrayList<MyMovie> movies,
                                    final ArrayList<MySerialInput> serials) {
        int j, k;
        MyActor aux;
        StringBuilder message;
        int count = number;

        Collections.sort(actorList, new Comparator<MyActor>() {
            public int compare(final MyActor v1, final MyActor v2) {
                return v1.getName().compareTo(v2.getName());
            }
        });

        Collections.sort(actorList, Comparator.comparing(MyActor::getName));

        Collections.reverse(actorList);

        for (j = 0; j < actorList.size() - 1; j++) {
            for (k = j + 1; k < actorList.size(); k++) {
                if ((actorList.get(j).getActorAverage(movies, serials)) < (actorList.get(k).getActorAverage(movies, serials))) {
                    aux = new MyActor(actorList.get(j));
                    actorList.set(j, actorList.get(k));
                    actorList.set(k, aux);
                }
            }
        }

        message = new StringBuilder("Query result: [");
        j = 0;
        while (j < actorList.size() && count != 0) {
            if ((actorList.get(j).getActorAverage(movies, serials)) != 0d) {
                message.append(actorList.get(j).getName());
                if ((j != actorList.size() - 1) && (count > 1)) { //  AICI!!!!!
                    message.append(", ");
                }
                j++;
                count--;
            }
            else {
                j++;
            }
        }

        message.append("]");

        return message.toString();
    }


    public String AscSortByAwards(final ArrayList<MyActor> actors,
                                  final List<String> awards) {

        int j, k;
        MyActor aux;
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

        Collections.sort(actorList, new Comparator<MyActor>() {
            public int compare(final MyActor v1, final MyActor v2) {
                return v1.getName().compareTo(v2.getName());
            }
        });

        Collections.sort(actorList, Comparator.comparing(MyActor::getName));

        for (j = 0; j < actorList.size() - 1; j++) {
            for (k = j + 1; k < actorList.size(); k++) {
                if ((actorList.get(j).getAwardsNumber()) > (actorList.get(k).getAwardsNumber())) {
                    aux = new MyActor(actorList.get(j));
                    actorList.set(j, actorList.get(k));
                    actorList.set(k, aux);
                }
            }
        }

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


    public String DescSortByAwards(final ArrayList<MyActor> actors,
                                   final List<String> awards) {

        int j, k;
        MyActor aux;
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

        Collections.sort(actorList, new Comparator<MyActor>() {
            public int compare(final MyActor v1, final MyActor v2) {
                return v1.getName().compareTo(v2.getName());
            }
        });

        Collections.sort(actorList, Comparator.comparing(MyActor::getName));
        Collections.reverse(actorList);

        for (j = 0; j < actorList.size() - 1; j++) {
            for (k = j + 1; k < actorList.size(); k++) {
                if ((actorList.get(j).getAwardsNumber()) < (actorList.get(k).getAwardsNumber())) {
                    aux = new MyActor(actorList.get(j));
                    actorList.set(j, actorList.get(k));
                    actorList.set(k, aux);
                }
            }
        }

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


    public String AscSortByDescription(final ArrayList<MyActor> actors,
                                       final List<String> words) {

        int j, k;
        MyActor aux;
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

        Collections.sort(actorList, new Comparator<MyActor>() {
            public int compare(final MyActor v1, final MyActor v2) {
                return v1.getName().compareTo(v2.getName());
            }
        });

        Collections.sort(actorList, Comparator.comparing(MyActor::getName));


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

    public String DescSortByDescription(final ArrayList<MyActor> actors,
                                        final List<String> words) {

        int j, k;
        MyActor aux;
        StringBuilder message;
        ArrayList<MyActor> actorList = new ArrayList<MyActor>();

        for (MyActor i : actors) {
            if (i.hasAllKeywords(words)) {
                actorList.add(i);
            }
        }

        if(actorList.size() == 0) {
            message = new StringBuilder("Query result: []");
            return message.toString();
        }

        Collections.sort(actorList, new Comparator<MyActor>() {
            public int compare(final MyActor v1, final MyActor v2) {
                return v1.getName().compareTo(v2.getName());
            }
        });

        Collections.sort(actorList, Comparator.comparing(MyActor::getName));
        Collections.reverse(actorList);


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
