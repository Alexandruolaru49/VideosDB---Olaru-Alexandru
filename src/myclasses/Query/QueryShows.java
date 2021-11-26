package myclasses.Query;

import myclasses.CopyClasses.MySerialInput;
import java.util.ArrayList;
import java.util.List;

public class QueryShows {

    /**
     * Metoda care initializeaza lista de seriale care urmeaza a fi sortata ulterior
     * Se face filtrarea, fiind adaugate in lista noua doar serialele care au anul si genul cerut,
     * in cazul in care este specificat unul anume.
     * @param serials
     * lista de filme din database
     * @param showList
     * lista noua de seriale ce contine serialele filtrate dupa gen si an
     * @param genre
     * genul filmului din database cu care se va face filtrarea
     * @param year
     * anul fimului din database cu care se va face filtrarea
     */
    public void initializeShows(final List<String> genre,
                                final List<String> year,
                                final ArrayList<MySerialInput> serials,
                                final ArrayList<MySerialInput> showList) {

        int j, k, ok;

        if ((genre.get(0) == null) && (year.get(0) == null)) {
            for (j = 0; j < serials.size(); j++) {
                showList.add(serials.get(j));
            }
        }

        if ((genre.get(0) != null) && (year.get(0) != null)) {
            for (j = 0; j < serials.size(); j++) {
                ok = 0;
                if (String.valueOf(serials.get(j).getYear()).equals(year.get(0))) {
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

        if ((genre.get(0) == null) && (year.get(0) != null)) {
            for (j = 0; j < serials.size(); j++) {
                if (String.valueOf(serials.get(j).getYear()).equals(year.get(0))) {
                        showList.add(serials.get(j));
                }
            }
        }

        if ((genre.get(0) != null) && (year.get(0) == null)) {
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

    /**
     * Metoda care compara lista de seriale in mod crescator dupa ratingul acestora,
     * iar apoi crescator dupa nume.
     * @param showList
     * lista ce va fi sortata
     * @param number
     * primele "number" seriale ce se doresc afisate
     */
    public String ascRating(final ArrayList<MySerialInput> showList, final int number) {

        int j;
        StringBuilder message;
        int count = number;

        showList.sort((v1, v2) -> {
            if (v1.getRating().equals(v2.getRating())) {
                return v1.getTitle().compareTo(v2.getTitle());
            } else {
                return Double.compare(v1.getRating(), v2.getRating());
            }
        });

        message = new StringBuilder("Query result: [");
        j = 0;
        while (j < showList.size() && count != 0) {
            if (showList.get(j).getRatingSerial() != 0d) {
                message.append(showList.get(j).getTitle());
                if ((j != showList.size() - 1) && (count > 1)) {
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
     * Metoda care compara lista de seriale in mod crescator dupa durata acestora,
     * iar apoi crescator dupa nume
     * @param showList
     * lista ce va fi sortata
     * @param number
     * primele "number" seriale ce se doresc afisate
     */
    public String ascLongest(final ArrayList<MySerialInput> showList, final int number) {

        int j;
        StringBuilder message;

        showList.sort((v1, v2) -> {
            if (v1.getSerialDuration() == v2.getSerialDuration()) {
                return v1.getTitle().compareTo(v2.getTitle());
            } else {
                return Integer.compare(v1.getSerialDuration(), v2.getSerialDuration());
            }
        });

        message = new StringBuilder("Query result: [");

        for (j = 0; j < showList.size(); j++) {
            if (j == (number)) {
                break;
            }
            message.append(showList.get(j).getTitle());
            if ((j != showList.size() - 1) && (j != (number - 1))) {
                message.append(", ");
            }
        }
        message.append("]");

        return message.toString();
    }

    /**
     * Metoda care compara lista de seriale in mod crescator dupa numarul de favorite
     * ale acestora, iar apoi crescator dupa nume.
     * @param showList
     * lista ce va fi sortata
     * @param number
     * primele "number" seriale ce se doresc afisate
     */
    public String ascFavorite(final ArrayList<MySerialInput> showList, final int number) {

        int j;
        StringBuilder message;
        int count = number;

        showList.sort((v1, v2) -> {
            if (v1.getNoFavorite() == v2.getNoFavorite()) {
                return v1.getTitle().compareTo(v2.getTitle());
            } else {
                return Integer.compare(v1.getNoFavorite(), v2.getNoFavorite());
            }
        });

        message = new StringBuilder("Query result: [");

        j = 0;
        while (j < showList.size() && count != 0) {
            if (showList.get(j).getNoFavorite() != 0d) {
                message.append(showList.get(j).getTitle());
                if ((j != showList.size() - 1) && (count > 1)) {
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
     * Metoda care compara lista de seriale in mod crescator dupa numarul de vizionari
     * ale acestora, iar apoi crescator dupa nume.
     * @param showList
     * lista ce va fi sortata
     * @param number
     * primele "number" seriale ce se doresc afisate
     */
    public String ascViews(final ArrayList<MySerialInput> showList, final int number) {

        int j;
        StringBuilder message;
        int count = number;

        showList.sort((v1, v2) -> {
            if (v1.getNoViews() == v2.getNoViews()) {
                return v1.getTitle().compareTo(v2.getTitle());
            } else {
                return Double.compare(v1.getNoViews(), v2.getNoViews());
            }
        });

        message = new StringBuilder("Query result: [");

        j = 0;
        while (j < showList.size() && count != 0) {
            if (showList.get(j).getNoViews() != 0d) {
                message.append(showList.get(j).getTitle());
                if ((j != showList.size() - 1) && (count > 1)) {
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
     * Metoda care compara lista de seriale in mod descrescator dupa ratingul acestora,
     * iar apoi descrescator dupa nume.
     * @param showList
     * lista ce va fi sortata
     * @param number
     * primele "number" seriale ce se doresc afisate
     */
    public String descRating(final ArrayList<MySerialInput> showList, final int number) {

        int j;
        StringBuilder message;
        int count = number;

        showList.sort((v1, v2) -> {
            if (v1.getRating().equals(v2.getRating())) {
                return v2.getTitle().compareTo(v1.getTitle());
            } else {
                return Double.compare(v2.getRating(), v1.getRating());
            }
        });

        int end;
        for (end = 0; end < showList.size(); end++) {
            if (showList.get(end).getRatingSerial() == 0d) {
                break;
            }
        }

        message = new StringBuilder("Query result: [");
        j = 0;
        while (j < end && count != 0) {
            if (showList.get(j).getRatingSerial() != 0d) {
                message.append(showList.get(j).getTitle());
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
     * Metoda care compara lista de filme in mod descrescator dupa durata acestora,
     * iar apoi descrescator dupa nume
     * @param showList
     * lista ce va fi sortata
     * @param number
     * primele "number" seriale ce se doresc afisate
     */
    public String descLongest(final ArrayList<MySerialInput> showList, final int number) {

        int j;
        StringBuilder message;

        showList.sort((v1, v2) -> {
            if (v1.getSerialDuration() == v2.getSerialDuration()) {
                return v2.getTitle().compareTo(v1.getTitle());
            } else {
                return Integer.compare(v2.getSerialDuration(), v1.getSerialDuration());
            }
        });

        message = new StringBuilder("Query result: [");

        for (j = 0; j < showList.size(); j++) {
            if (j == (number)) {
                break;
            }
            message.append(showList.get(j).getTitle());
            if ((j != showList.size() - 1) && (j != (number - 1))) {
                message.append(", ");
            }
        }
        message.append("]");

        return message.toString();
    }

    /**
     * Metoda care compara lista de seriale in mod descrescator dupa numarul de favorite
     * ale acestora, iar apoi descrescator dupa nume.
     * @param showList
     * lista ce va fi sortata
     * @param number
     * primele "number" seriale ce se doresc afisate
     */
    public String descFavorite(final ArrayList<MySerialInput> showList, final int number) {

        int j;
        StringBuilder message;
        int count = number;

        showList.sort((v1, v2) -> {
            if (v1.getNoFavorite() == v2.getNoFavorite()) {
                return v2.getTitle().compareTo(v1.getTitle());
            } else {
                return Integer.compare(v2.getNoFavorite(), v1.getNoFavorite());
            }
        });


        int end;
        for (end = 0; end < showList.size(); end++) {
            if (showList.get(end).getNoFavorite() == 0) {
                break;
            }
        }

        message = new StringBuilder("Query result: [");

        j = 0;
        while (j < end && count != 0) {
            if (showList.get(j).getNoFavorite() != 0d) {
                message.append(showList.get(j).getTitle());
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
     * Metoda care compara lista de seriale in mod descrescator dupa numarul de vizionari
     * ale acestora, iar apoi descrescator dupa nume.
     * @param showList
     * lista ce va fi sortata
     * @param number
     * primele "number" seriale ce se doresc afisate
     */
    public String descViews(final ArrayList<MySerialInput> showList, final int number) {

        int j;
        StringBuilder message;
        int count = number;

        showList.sort((v1, v2) -> {
            if (v1.getNoViews() == v2.getNoViews()) {
                return v2.getTitle().compareTo(v1.getTitle());
            } else {
                return Double.compare(v2.getNoViews(), v1.getNoViews());
            }
        });

        int end;
        for (end = 0; end < showList.size(); end++) {
            if (showList.get(end).getNoViews() == 0) {
                break;
            }
        }

        message = new StringBuilder("Query result: [");

        j = 0;
        while (j < end && count != 0) {
            if (showList.get(j).getNoViews() != 0d) {
                message.append(showList.get(j).getTitle());
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

}
