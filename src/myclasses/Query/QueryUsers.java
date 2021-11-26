package myclasses.Query;

import myclasses.CopyClasses.MyUser;
import java.util.ArrayList;

public class QueryUsers {

    /**
     * Metoda care initializeaza lista de utilizatori ce urmeaza
     * a fi mai apoi sortata
     * @param users
     * lista cu utilizatorii din database
     * @param userList
     * lista noua ce urmeaza a fi sortata
     */
    public void initializeUserslist(final ArrayList<MyUser> users,
                                    final ArrayList<MyUser> userList) {
        for (MyUser j : users) {
            userList.add(j);
        }
    }

    /**
     * Metoda care sorteaza lista de utilizatori crescator dupa
     * numarul de ratinguri facute, iar apoi crescator dupa nume
     * @param userList
     * lista ce va fi sortata
     * @param number
     * primii "number" utilizatori ce se doresc afisati
     */
    public String ascRatingsgiven(final ArrayList<MyUser> userList, final int number) {
        int j;
        StringBuilder message;
        int count = number;

        userList.sort((v1, v2) -> {
            if (v1.getRatingsGiven() == v2.getRatingsGiven()) {
                return v1.getUsername().compareTo(v2.getUsername());
            } else {
                return Integer.compare(v1.getRatingsGiven(), v2.getRatingsGiven());
            }
        });

        message = new StringBuilder("Query result: [");
        j = 0;
        while (j < userList.size() && count != 0) {
            if (userList.get(j).getRatingsGiven() != 0) {
                message.append(userList.get(j).getUsername());
                if ((j != userList.size() - 1) && (count > 1)) {
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
     * Metoda care sorteaza lista de utilizatori descrescator dupa
     * numarul de ratinguri facute, iar apoi descrescator dupa nume
     * @param userList
     * lista ce va fi sortata
     * @param number
     * primii "number" utilizatori ce se doresc afisati
     */
    public String descRatingsgiven(final ArrayList<MyUser> userList, final int number) {
        int j;
        StringBuilder message;
        int count = number;

        userList.sort((v1, v2) -> {
            if (v1.getRatingsGiven() == v2.getRatingsGiven()) {
                return v2.getUsername().compareTo(v1.getUsername());
            } else {
                return Integer.compare(v2.getRatingsGiven(), v1.getRatingsGiven());
            }
        });

        message = new StringBuilder("Query result: [");

        int end;
        for (end = 0; end < userList.size(); end++) {
            if (userList.get(end).getRatingsGiven() == 0) {
                break;
            }
        }

        j = 0;
        while (j < end && count != 0) {
            if (userList.get(j).getRatingsGiven() != 0) {
                message.append(userList.get(j).getUsername());
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
