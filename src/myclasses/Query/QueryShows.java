package myclasses.Query;

import myclasses.MySerialInput;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class QueryShows {

    public void initializeShows(final List<String> genre,
                                final List<String> year,
                                final ArrayList<MySerialInput> serials,
                                final ArrayList<MySerialInput> showList) {

        int j, k, ok;
        // DACA GENRE SI YEAR SUNT DIFERITE DE NULL
        if ((genre.size() > 0) && (year.size() > 0)) {
            for (j = 0; j < serials.size(); j++) {
                ok = 0;
                if (String.valueOf(serials.get(j).year).equals(year.get(0))) {
                    for (k = 0; k < serials.get(j).getGenres().size(); k++) {
                        if (serials.get(j).getGenres().get(k).equals(genre.get(0))) {
                            ok = 1;
                        }
                    }
                    if (ok == 1) {
                        showList.add(serials.get(j));
                    }
                }
            }
        }

        if ((genre.size() == 0) && (year.size() > 0)) {
            for (j = 0; j < serials.size(); j++) {
                if (String.valueOf(serials.get(j).year).equals(year.get(0))) {
                    showList.add(serials.get(j));
                }
            }
        }

        if ((genre.size() > 0) && (year.size() == 0)) {
            for (j = 0; j < serials.size(); j++) {
                ok = 0;

                for (k = 0; k < serials.get(j).getGenres().size(); k++) {
                    if (serials.get(j).getGenres().get(k).equals(genre.get(0))) {
                        ok = 1;
                    }
                }
                if (ok == 1) {
                    showList.add(serials.get(j));
                }
            }
        }
    }

    public String ascRating(final ArrayList<MySerialInput> showList, final int number) {

        int j, k;
        MySerialInput aux;
        StringBuilder message;
        int count = number;

        showList.sort((v1, v2) -> {
            if (v1.getRating().equals(v2.getRating())) {
                return v1.getTitle().compareTo(v2.getTitle());
            } else {
                return Double.compare(v1.getRating(), v2.getRating());
            }
        });

//        Collections.sort(showList, new Comparator<MySerialInput>() {
//            public int compare(final MySerialInput v1, final MySerialInput v2) {
//                return v1.getTitle().compareTo(v2.getTitle());
//            }
//        });
//
//        Collections.sort(showList, Comparator.comparing(MySerialInput::getTitle));
//
//
//        for (j = 0; j < showList.size() - 1; j++) {
//            for (k = j + 1; k < showList.size(); k++) {
//                if (showList.get(j).ratingSerial > showList.get(k).ratingSerial) {
//                    aux = new MySerialInput(showList.get(j));
//                    showList.set(j, showList.get(k));
//                    showList.set(k, aux);
//                }
//            }
//        }
        message = new StringBuilder("Query result: [");
        j = 0;
        while (j < showList.size() && count != 0) {
            if (showList.get(j).ratingSerial != 0d) {
                message.append(showList.get(j).title);
                if ((j != showList.size() - 1) && (count > 1)) { //  AICI!!!!!
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

    public String ascLongest(final ArrayList<MySerialInput> showList, final int number) {

        int j, k;
        MySerialInput aux;
        StringBuilder message;

        showList.sort((v1, v2) -> {
            if (v1.getSerialDuration() == v2.getSerialDuration()) {
                return v1.getTitle().compareTo(v2.getTitle());
            } else {
                return Integer.compare(v1.getSerialDuration(), v2.getSerialDuration());
            }
        });

//        Collections.sort(showList, new Comparator<MySerialInput>() {
//            public int compare(final MySerialInput v1, final MySerialInput v2) {
//                return v1.getTitle().compareTo(v2.getTitle());
//            }
//        });
//
//        Collections.sort(showList, Comparator.comparing(MySerialInput::getTitle));
//
//        for (j = 0; j < showList.size() - 1; j++) {
//            for (k = j + 1; k < showList.size(); k++) {
//                if (showList.get(j).serialDuration > showList.get(k).serialDuration) {
//                    aux = new MySerialInput(showList.get(j));
//                    showList.set(j, showList.get(k));
//                    showList.set(k, aux);
//                }
//            }
//        }
        message = new StringBuilder("Query result: [");
        j = 0;
        for (j = 0; j < showList.size(); j++) {
            if (j == (number - 1)) {
                break;
            }
            message.append(showList.get(j).title);
            if ((j != showList.size() - 1) && (j != (number - 1))) { //  AICI!!!!!
                message.append(", ");
            }
        }
        message.append("]");

        return message.toString();
    }

    public String ascFavorite(final ArrayList<MySerialInput> showList, final int number) {

        int j, k;
        MySerialInput aux;
        StringBuilder message;
        int count = number;

        showList.sort((v1, v2) -> {
            if (v1.noFavorite == v2.noFavorite) {
                return v1.getTitle().compareTo(v2.getTitle());
            } else {
                return Integer.compare(v1.noFavorite, v2.noFavorite);
            }
        });

//        Collections.sort(showList, new Comparator<MySerialInput>() {
//            public int compare(final MySerialInput v1, final MySerialInput v2) {
//                return v1.getTitle().compareTo(v2.getTitle());
//            }
//        });
//
//        Collections.sort(showList, Comparator.comparing(MySerialInput::getTitle));
//
//        for (j = 0; j < showList.size() - 1; j++) {
//            for (k = j + 1; k < showList.size(); k++) {
//                if (showList.get(j).noFavorite > showList.get(k).noFavorite) {
//                    aux = new MySerialInput(showList.get(j));
//                    showList.set(j, showList.get(k));
//                    showList.set(k, aux);
//                }
//            }
//        }
        message = new StringBuilder("Query result: [");
        j = 0;
        while (j < showList.size() && count != 0) {
            if (showList.get(j).noFavorite != 0d) {
                message.append(showList.get(j).title);
                if ((j != showList.size() - 1) && (count > 1)) { //  AICI!!!!!
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

    public String ascViews(final ArrayList<MySerialInput> showList, final int number) {

        int j, k;
        MySerialInput aux;
        StringBuilder message;
        int count = number;

        showList.sort((v1, v2) -> {
            if (v1.noViews == v2.noViews) {
                return v1.getTitle().compareTo(v2.getTitle());
            } else {
                return Double.compare(v1.noViews, v2.noViews);
            }
        });

//        Collections.sort(showList, new Comparator<MySerialInput>() {
//            public int compare(final MySerialInput v1, final MySerialInput v2) {
//                return v1.getTitle().compareTo(v2.getTitle());
//            }
//        });
//
//        Collections.sort(showList, Comparator.comparing(MySerialInput::getTitle));
//
//        for (j = 0; j < showList.size() - 1; j++) {
//            for (k = j + 1; k < showList.size(); k++) {
//                if (showList.get(j).noViews > showList.get(k).noViews) {
//                    aux = new MySerialInput(showList.get(j));
//                    showList.set(j, showList.get(k));
//                    showList.set(k, aux);
//                }
//            }
//        }
        message = new StringBuilder("Query result: [");
        j = 0;
        while (j < showList.size() && number != 0) {
            if (showList.get(j).noViews != 0d) {
                message.append(showList.get(j).title);
                if ((j != showList.size() - 1) && (number > 1)) { //  AICI!!!!!
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


    public String descRating(final ArrayList<MySerialInput> showList, final int number) {

        int j, k;
        MySerialInput aux;
        StringBuilder message;
        int count = number;

        showList.sort((v1, v2) -> {
            if (v1.getRating().equals(v2.getRating())) {
                return v2.getTitle().compareTo(v1.getTitle());
            } else {
                return Double.compare(v2.getRating(), v1.getRating());
            }
        });

//        Collections.sort(showList, new Comparator<MySerialInput>() {
//            public int compare(final MySerialInput v1, final MySerialInput v2) {
//                return v1.getTitle().compareTo(v2.getTitle());
//            }
//        });
//
//        Collections.sort(showList, Comparator.comparing(MySerialInput::getTitle));
//        Collections.reverse(showList);
//
//        for (j = 0; j < showList.size() - 1; j++) {
//            for (k = j + 1; k < showList.size(); k++) {
//                if (showList.get(j).ratingSerial < showList.get(k).ratingSerial) {
//                    aux = new MySerialInput(showList.get(j));
//                    showList.set(j, showList.get(k));
//                    showList.set(k, aux);
//                }
//            }
//        }
        message = new StringBuilder("Query result: [");
        j = 0;
        while (j < showList.size() && count != 0) {
            if (showList.get(j).ratingSerial != 0d) {
                message.append(showList.get(j).title);
                if ((j != showList.size() - 1) && (count > 1)) { //  AICI!!!!!
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

    public String descLongest(final ArrayList<MySerialInput> showList, final int number) {

        int j, k;
        MySerialInput aux;
        StringBuilder message;

        showList.sort((v1, v2) -> {
            if (v1.getSerialDuration() == v2.getSerialDuration()) {
                return v2.getTitle().compareTo(v1.getTitle());
            } else {
                return Integer.compare(v2.getSerialDuration(), v1.getSerialDuration());
            }
        });

//        Collections.sort(showList, new Comparator<MySerialInput>() {
//            public int compare(final MySerialInput v1, final MySerialInput v2) {
//                return v1.getTitle().compareTo(v2.getTitle());
//            }
//        });
//
//        Collections.sort(showList, Comparator.comparing(MySerialInput::getTitle));
//        Collections.reverse(showList);
//
//        for (j = 0; j < showList.size() - 1; j++) {
//            for (k = j + 1; k < showList.size(); k++) {
//                if (showList.get(j).serialDuration < showList.get(k).serialDuration) {
//                    aux = new MySerialInput(showList.get(j));
//                    showList.set(j, showList.get(k));
//                    showList.set(k, aux);
//                }
//            }
//        }
        message = new StringBuilder("Query result: [");
        j = 0;
        for (j = 0; j < showList.size(); j++) {
            if (j == (number - 1)) {
                break;
            }
            message.append(showList.get(j).title);
            if ((j != showList.size() - 1) && (j != (number - 1))) { //  AICI!!!!!
                message.append(", ");
            }
        }
        message.append("]");

        return message.toString();
    }

    public String descFavorite(final ArrayList<MySerialInput> showList, final int number) {

        int j, k;
        MySerialInput aux;
        StringBuilder message;
        int count = number;

        showList.sort((v1, v2) -> {
            if (v1.noFavorite == v2.noFavorite) {
                return v2.getTitle().compareTo(v1.getTitle());
            } else {
                return Integer.compare(v2.noFavorite, v1.noFavorite);
            }
        });

//        Collections.sort(showList, new Comparator<MySerialInput>() {
//            public int compare(final MySerialInput v1, final MySerialInput v2) {
//                return v1.getTitle().compareTo(v2.getTitle());
//            }
//        });
//
//        Collections.sort(showList, Comparator.comparing(MySerialInput::getTitle));
//        Collections.reverse(showList);
//
//        for (j = 0; j < showList.size() - 1; j++) {
//            for (k = j + 1; k < showList.size(); k++) {
//                if (showList.get(j).noFavorite < showList.get(k).noFavorite) {
//                    aux = new MySerialInput(showList.get(j));
//                    showList.set(j, showList.get(k));
//                    showList.set(k, aux);
//                }
//            }
//        }
        message = new StringBuilder("Query result: [");
        j = 0;
        while (j < showList.size() && count != 0) {
            if (showList.get(j).noFavorite != 0d) {
                message.append(showList.get(j).title);
                if ((j != showList.size() - 1) && (count > 1)) { //  AICI!!!!!
                    message.append(", ");
                }
                j++;
                count--;
            } else {
                j++;
            }
        }
        message = new StringBuilder(message.substring(0, message.length() - 1));
        message = new StringBuilder(message.substring(0, message.length() - 1));
        message.append("]");


        return message.toString();
    }

    public String descViews(final ArrayList<MySerialInput> showList, final int number) {

        int j, k;
        MySerialInput aux;
        StringBuilder message;
        int count = number;

        showList.sort((v1, v2) -> {
            if (v1.noViews == v2.noViews) {
                return v2.getTitle().compareTo(v1.getTitle());
            } else {
                return Double.compare(v2.noViews, v1.noViews);
            }
        });

//        Collections.sort(showList, new Comparator<MySerialInput>() {
//            public int compare(final MySerialInput v1, final MySerialInput v2) {
//                return v1.getTitle().compareTo(v2.getTitle());
//            }
//        });
//
//        Collections.sort(showList, Comparator.comparing(MySerialInput::getTitle));
//        Collections.reverse(showList);
//
//        for (j = 0; j < showList.size() - 1; j++) {
//            for (k = j + 1; k < showList.size(); k++) {
//                if (showList.get(j).noViews < showList.get(k).noViews) {
//                    aux = new MySerialInput(showList.get(j));
//                    showList.set(j, showList.get(k));
//                    showList.set(k, aux);
//                }
//            }
//        }
        message = new StringBuilder("Query result: [");
        j = 0;
        while (j < showList.size() && count != 0) {
            if (showList.get(j).noViews != 0d) {
                message.append(showList.get(j).title);
                if ((j != showList.size() - 1) && (count > 1)) { //  AICI!!!!!
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


}
