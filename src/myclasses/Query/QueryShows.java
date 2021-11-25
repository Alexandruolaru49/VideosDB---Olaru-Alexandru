package myclasses.Query;

import myclasses.MyMovie;
import myclasses.MySerialInput;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class QueryShows {

    public void InitializeShowList(List<String> genre, List<String> year, ArrayList<MySerialInput> serials, ArrayList<MySerialInput> showList) {

        int j, k, ok;
        if((genre.size() > 0) && (year.size() > 0)) {     // DACA GENRE SI YEAR SUNT DIFERITE DE NULL
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

        if((genre.size() == 0) && (year.size() > 0)) {
            for(j = 0; j < serials.size(); j++) {
                if(String.valueOf(serials.get(j).year).equals(year.get(0))) {
                    showList.add(serials.get(j));
                }
            }
        }

        if((genre.size() > 0) && (year.size() == 0)) {
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

    public String AscSortByRating(ArrayList<MySerialInput> showList, int number) {

        int j, k;
        MySerialInput aux;
        StringBuilder message;

        Collections.sort(showList, new Comparator<MySerialInput>() {
            public int compare(MySerialInput v1, MySerialInput v2) {
                return v1.getTitle().compareTo(v2.getTitle());
            }
        });

        Collections.sort(showList, Comparator.comparing(MySerialInput::getTitle));


        for (j = 0; j < showList.size() - 1; j++) {
            for (k = j + 1; k < showList.size(); k++) {
                if (showList.get(j).ratingSerial > showList.get(k).ratingSerial) {
                    aux = new MySerialInput(showList.get(j));
                    showList.set(j, showList.get(k));
                    showList.set(k, aux);
                }
            }
        }
        message = new StringBuilder("Query result: [");
        j = 0;
        while(j < showList.size() && number != 0) {
            if(showList.get(j).ratingSerial != 0d) {
                message.append(showList.get(j).title);
                if ((j != showList.size() - 1) && (number > 1)) { //  AICI!!!!!
                    message.append(", ");
                }
                j++;
                number--;
            }
            else {
                j++;
            }
        }
        message.append("]");

        return message.toString();
    }

    public String AscSortByLongest(ArrayList<MySerialInput> showList, int number) {

        int j, k;
        MySerialInput aux;
        StringBuilder message;

        Collections.sort(showList, new Comparator<MySerialInput>() {
            public int compare(MySerialInput v1, MySerialInput v2) {
                return v1.getTitle().compareTo(v2.getTitle());
            }
        });

        Collections.sort(showList, Comparator.comparing(MySerialInput::getTitle));

        for (j = 0; j < showList.size() - 1; j++) {
            for (k = j + 1; k < showList.size(); k++) {
                if (showList.get(j).serialDuration > showList.get(k).serialDuration) {
                    aux = new MySerialInput(showList.get(j));
                    showList.set(j, showList.get(k));
                    showList.set(k, aux);
                }
            }
        }
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

    public String AscSortByFavorite(ArrayList<MySerialInput> showList, int number) {

        int j, k;
        MySerialInput aux;
        StringBuilder message;

        Collections.sort(showList, new Comparator<MySerialInput>() {
            public int compare(MySerialInput v1, MySerialInput v2) {
                return v1.getTitle().compareTo(v2.getTitle());
            }
        });

        Collections.sort(showList, Comparator.comparing(MySerialInput::getTitle));

        for (j = 0; j < showList.size() - 1; j++) {
            for (k = j + 1; k < showList.size(); k++) {
                if (showList.get(j).noFavorite > showList.get(k).noFavorite) {
                    aux = new MySerialInput(showList.get(j));
                    showList.set(j, showList.get(k));
                    showList.set(k, aux);
                }
            }
        }
        message = new StringBuilder("Query result: [");
        j = 0;
        while(j < showList.size() && number != 0) {
            if(showList.get(j).noFavorite != 0d) {
                message.append(showList.get(j).title);
                if ((j != showList.size() - 1) && (number > 1)) { //  AICI!!!!!
                    message.append(", ");
                }
                j++;
                number--;
            }
            else {
                j++;
            }
        }
        message.append("]");

        return message.toString();
    }

    public String AscSortByViews(ArrayList<MySerialInput> showList, int number) {

        int j, k;
        MySerialInput aux;
        StringBuilder message;

        Collections.sort(showList, new Comparator<MySerialInput>() {
            public int compare(MySerialInput v1, MySerialInput v2) {
                return v1.getTitle().compareTo(v2.getTitle());
            }
        });

        Collections.sort(showList, Comparator.comparing(MySerialInput::getTitle));

        for (j = 0; j < showList.size() - 1; j++) {
            for (k = j + 1; k < showList.size(); k++) {
                if (showList.get(j).noViews > showList.get(k).noViews) {
                    aux = new MySerialInput(showList.get(j));
                    showList.set(j, showList.get(k));
                    showList.set(k, aux);
                }
            }
        }
        message = new StringBuilder("Query result: [");
        j = 0;
        while(j < showList.size() && number != 0) {
            if(showList.get(j).noViews != 0d) {
                message.append(showList.get(j).title);
                if ((j != showList.size() - 1) && (number > 1)) { //  AICI!!!!!
                    message.append(", ");
                }
                j++;
                number--;
            }
            else {
                j++;
            }
        }
        message.append("]");

        return message.toString();
    }


    public String DescSortByRating(ArrayList<MySerialInput> showList, int number) {

        int j, k;
        MySerialInput aux;
        StringBuilder message;

        Collections.sort(showList, new Comparator<MySerialInput>() {
            public int compare(MySerialInput v1, MySerialInput v2) {
                return v1.getTitle().compareTo(v2.getTitle());
            }
        });

        Collections.sort(showList, Comparator.comparing(MySerialInput::getTitle));
        Collections.reverse(showList);

        for (j = 0; j < showList.size() - 1; j++) {
            for (k = j + 1; k < showList.size(); k++) {
                if (showList.get(j).ratingSerial < showList.get(k).ratingSerial) {
                    aux = new MySerialInput(showList.get(j));
                    showList.set(j, showList.get(k));
                    showList.set(k, aux);
                }
            }
        }
        message = new StringBuilder("Query result: [");
        j = 0;
        while(j < showList.size() && number != 0) {
            if(showList.get(j).ratingSerial != 0d) {
                message.append(showList.get(j).title);
                if ((j != showList.size() - 1) && (number > 1)) { //  AICI!!!!!
                    message.append(", ");
                }
                j++;
                number--;
            }
            else {
                j++;
            }
        }
        message.append("]");

        return message.toString();
    }

    public String DescSortByLongest(ArrayList<MySerialInput> showList, int number) {

        int j, k;
        MySerialInput aux;
        StringBuilder message;

        Collections.sort(showList, new Comparator<MySerialInput>() {
            public int compare(MySerialInput v1, MySerialInput v2) {
                return v1.getTitle().compareTo(v2.getTitle());
            }
        });

        Collections.sort(showList, Comparator.comparing(MySerialInput::getTitle));
        Collections.reverse(showList);

        for (j = 0; j < showList.size() - 1; j++) {
            for (k = j + 1; k < showList.size(); k++) {
                if (showList.get(j).serialDuration < showList.get(k).serialDuration) {
                    aux = new MySerialInput(showList.get(j));
                    showList.set(j, showList.get(k));
                    showList.set(k, aux);
                }
            }
        }
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

    public String DescSortByFavorite(ArrayList<MySerialInput> showList, int number) {

        int j, k;
        MySerialInput aux;
        StringBuilder message;

        Collections.sort(showList, new Comparator<MySerialInput>() {
            public int compare(MySerialInput v1, MySerialInput v2) {
                return v1.getTitle().compareTo(v2.getTitle());
            }
        });

        Collections.sort(showList, Comparator.comparing(MySerialInput::getTitle));
        Collections.reverse(showList);

        for (j = 0; j < showList.size() - 1; j++) {
            for (k = j + 1; k < showList.size(); k++) {
                if (showList.get(j).noFavorite < showList.get(k).noFavorite) {
                    aux = new MySerialInput(showList.get(j));
                    showList.set(j, showList.get(k));
                    showList.set(k, aux);
                }
            }
        }
        message = new StringBuilder("Query result: [");
        j = 0;
        while(j < showList.size() && number != 0) {
            if(showList.get(j).noFavorite != 0d) {
                message.append(showList.get(j).title);
                if ((j != showList.size() - 1) && (number > 1)) { //  AICI!!!!!
                    message.append(", ");
                }
                j++;
                number--;
            }
            else {
                j++;
            }
        }
        message = new StringBuilder(message.substring(0, message.length() - 1));
        message = new StringBuilder(message.substring(0, message.length() - 1));
        message.append("]");


        return message.toString();
    }

    public String DescSortByViews(ArrayList<MySerialInput> showList, int number) {

        int j, k;
        MySerialInput aux;
        StringBuilder message;

        Collections.sort(showList, new Comparator<MySerialInput>() {
            public int compare(MySerialInput v1, MySerialInput v2) {
                return v1.getTitle().compareTo(v2.getTitle());
            }
        });

        Collections.sort(showList, Comparator.comparing(MySerialInput::getTitle));
        Collections.reverse(showList);

        for (j = 0; j < showList.size() - 1; j++) {
            for (k = j + 1; k < showList.size(); k++) {
                if (showList.get(j).noViews < showList.get(k).noViews) {
                    aux = new MySerialInput(showList.get(j));
                    showList.set(j, showList.get(k));
                    showList.set(k, aux);
                }
            }
        }
        message = new StringBuilder("Query result: [");
        j = 0;
        while(j < showList.size() && number != 0) {
            if(showList.get(j).noViews != 0d) {
                message.append(showList.get(j).title);
                if ((j != showList.size() - 1) && (number > 1)) { //  AICI!!!!!
                    message.append(", ");
                }
                j++;
                number--;
            }
            else {
                j++;
            }
        }
        message.append("]");

        return message.toString();
    }


}
