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


        userList.sort((v1, v2) -> {
            if (v1.ratingsGiven == v2.ratingsGiven) {
                return v1.getUsername().compareTo(v2.getUsername());
            } else {
                return Integer.compare(v1.ratingsGiven, v2.ratingsGiven);
            }
        });
//        Collections.sort(userList, new Comparator<MyUser>() {
//            public int compare(MyUser v1, MyUser v2) {
//                return v1.getUsername().compareTo(v2.getUsername());
//            }
//        });
//
//        Collections.sort(userList, Comparator.comparing(MyUser::getUsername));

//        for (j = 0; j < userList.size() - 1; j++) {
//            for (k = j + 1; k < userList.size(); k++) {
//                if (userList.get(j).ratingsGiven > userList.get(k).ratingsGiven) {
//                    aux = new MyUser(userList.get(j));
//                    userList.set(j, userList.get(k));
//                    userList.set(k, aux);
//                }
//            }
//        }

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

        userList.sort((v1, v2) -> {
            if (v1.ratingsGiven == v2.ratingsGiven) {
                return v2.getUsername().compareTo(v1.getUsername());
            } else {
                return Integer.compare(v2.ratingsGiven, v1.ratingsGiven);
            }
        });

//        Collections.sort(userList, new Comparator<MyUser>() {
//            public int compare(MyUser v1, MyUser v2) {
//                return v1.getUsername().compareTo(v2.getUsername());
//            }
//        });
//
//        Collections.sort(userList, Comparator.comparing(MyUser::getUsername));
//        Collections.reverse(userList);

//        for (j = 0; j < userList.size() - 1; j++) {
//            for (k = j + 1; k < userList.size(); k++) {
//                if (userList.get(j).ratingsGiven < userList.get(k).ratingsGiven) {
//                    aux = new MyUser(userList.get(j));
//                    userList.set(j, userList.get(k));
//                    userList.set(k, aux);
//                }
//            }
//        }


        message = new StringBuilder("Query result: [");

        int end; //!!!!!!!!!!!!!!!!!!!
        for (end = 0; end < userList.size(); end++) {
            if(userList.get(end).ratingsGiven == 0) {
                break;
            }
        }

        j = 0;
        while(j < end && number != 0) {
            if(userList.get(j).ratingsGiven != 0) {
                message.append(userList.get(j).username);
                if ((j != end - 1) && (number > 1)) { //  AICI!!!!!
                    message.append(", ");
                }
                j++;
                number--;
            }
            else {
                j++;
            }
        }

//        message = new StringBuilder(message.substring(0, message.length() - 1));
//        message = new StringBuilder(message.substring(0, message.length() - 1));

        message.append("]");

        return message.toString();
    }

}
