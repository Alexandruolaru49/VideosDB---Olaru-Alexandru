package myclasses.Recommendations;


import myclasses.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Recommendation {

    public boolean isUser(ArrayList<MyUser> users, String username) {
        for (MyUser i : users) {
            if (i.getUsername().equals(username)) {
                return true;
            }
        }
        return  false;
    }

    public MyUser getUser(ArrayList<MyUser> users, String username) {
        MyUser j = users.get(0);
        for (MyUser i : users) {
            if (i.getUsername().equals(username)) {
                return i;
            }
        }
        return j;
    }

    public String Standard(ArrayList<MyUser> users, String username, ArrayList<MyMovie> movies, ArrayList<MySerialInput> serials) {

        String message = "";

        MyUser user = new MyUser(getUser(users, username));

        for (MyMovie i : movies) {
            if (user.wasWatched(i.title) == false) {
                message = "StandardRecommendation result: " + i.title;
                return message;
            }
        }

        for (MySerialInput i : serials) {
            if (user.wasWatched(i.title) == false) {
                message = "StandardRecommendation result: " + i.title;
                return message;
            }
        }

        return message;
    }

    public String BestUnseen(ArrayList<MyUser> users, String username, ArrayList<MyMovie> movies, ArrayList<MySerialInput> serials) {

        StringBuilder message;

        ArrayList<MyShowInput> videos = new ArrayList<MyShowInput>();
        for (MyMovie i : movies) {
            videos.add(i);
        }
        for (MySerialInput i : serials) {
            videos.add(i);
        }

        videos.sort((v1, v2) -> Double.compare(v2.rating, v1.rating));

        MyUser user = new MyUser(getUser(users, username));

        int i = 0;
        int ok = 0;
        while (i < videos.size() && ok == 0) {
            if(user.wasWatched(videos.get(i).getTitle()) == false) {
                ok = 1;
            }
            else {
                i++;
            }
        }

        if(ok == 1) {
            message = new StringBuilder("BestRatedUnseenRecommendation result: ");
            message.append(videos.get(i).getTitle());
        }
        else {
            message = new StringBuilder("BestRatedUnseenRecommendation cannot be applied!");
        }
        return message.toString();
    }




}
