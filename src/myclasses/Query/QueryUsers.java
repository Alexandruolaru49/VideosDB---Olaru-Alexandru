package myclasses.Query;

import myclasses.MyMovie;
import myclasses.MySerialInput;
import myclasses.MyUser;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class QueryUsers {

    public void InitializeUsersList(ArrayList<MyUser> users, ArrayList<MyUser> userList) {
        for(MyUser j : users) {
            userList.add(j);
        }
    }

    public String AscSortByRatingsGiven(ArrayList<MyUser> userList, int number) {
        int j, k;
        MyUser aux;
        StringBuilder message;

        Collections.sort(userList, new Comparator<MyUser>() {
            public int compare(MyUser v1, MyUser v2) {
                return v1.getUsername().compareTo(v2.getUsername());
            }
        });

        Collections.sort(userList, Comparator.comparing(MyUser::getUsername));

        for (j = 0; j < userList.size() - 1; j++) {
            for (k = j + 1; k < userList.size(); k++) {
                if (userList.get(j).ratingsGiven > userList.get(k).ratingsGiven) {
                    aux = new MyUser(userList.get(j));
                    userList.set(j, userList.get(k));
                    userList.set(k, aux);
                }
            }
        }

        message = new StringBuilder("Query result: [");
        j = 0;
        while(j < userList.size() && number != 0) {
            if(userList.get(j).ratingsGiven != 0) {
                message.append(userList.get(j).username);
                if ((j != userList.size() - 1) && (number > 1)) { //  AICI!!!!!
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

    public String DescSortByRatingsGiven(ArrayList<MyUser> userList, int number) {
        int j, k;
        MyUser aux;
        StringBuilder message;

        Collections.sort(userList, new Comparator<MyUser>() {
            public int compare(MyUser v1, MyUser v2) {
                return v1.getUsername().compareTo(v2.getUsername());
            }
        });

        Collections.sort(userList, Comparator.comparing(MyUser::getUsername));
        Collections.reverse(userList);

        for (j = 0; j < userList.size() - 1; j++) {
            for (k = j + 1; k < userList.size(); k++) {
                if (userList.get(j).ratingsGiven < userList.get(k).ratingsGiven) {
                    aux = new MyUser(userList.get(j));
                    userList.set(j, userList.get(k));
                    userList.set(k, aux);
                }
            }
        }

        message = new StringBuilder("Query result: [");
        j = 0;
        while(j < userList.size() && number != 0) {
            if(userList.get(j).ratingsGiven != 0) {
                message.append(userList.get(j).username);
                if ((j != userList.size() - 1) && (number > 1)) { //  AICI!!!!!
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
