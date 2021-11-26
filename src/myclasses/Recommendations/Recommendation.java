package myclasses.Recommendations;

import myclasses.*;

import java.util.*;

public class Recommendation {

    public boolean isUser(ArrayList<MyUser> users, String username) {
        for (MyUser i : users) {
            if (i.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }

    public MyUser getUser(final ArrayList<MyUser> users, final String username) {
        MyUser j = users.get(0);
        for (MyUser i : users) {
            if (i.getUsername().equals(username)) {
                return i;
            }
        }
        return j;
    }

    public HashMap<String, Integer> getGenremap(ArrayList<MyShowInput> videos) {
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        for (MyShowInput i : videos) {
            for (String j : i.getGenres()) {
                if (!map.containsKey(j)) {
                    map.put(j, i.noViews);
                } else {
                    map.put(j, map.get(j) + i.noViews);
                }
            }
        }

        List<HashMap.Entry<String, Integer> > list =
                new LinkedList<HashMap.Entry<String, Integer> >(map.entrySet());

        Collections.sort(list, new Comparator<HashMap.Entry<String, Integer>>() {
            public int compare(HashMap.Entry<String, Integer> v1,
                               HashMap.Entry<String, Integer> v2)
            {
                if(v2.getValue().equals(v1.getValue())) {
                    return v2.getKey().compareTo(v1.getKey());
                } else {
                    return Integer.compare(v2.getValue(), v1.getValue());
                }
            }
        });

        HashMap<String, Integer> newMap = new LinkedHashMap<String, Integer>();
        for(Map.Entry<String, Integer> i : list) {
            newMap.put(i.getKey(), i.getValue());
        }

        return newMap;
    }

    public String Standard(final ArrayList<MyUser> users,
                           final String username,
                           final ArrayList<MyMovie> movies,
                           final ArrayList<MySerialInput> serials) {

        String message = "";

        MyUser user = new MyUser(getUser(users, username));

        for (MyMovie i : movies) {
            if (!user.wasWatched(i.title)) {
                message = "StandardRecommendation result: " + i.title;
                return message;
            }
        }

        for (MySerialInput i : serials) {
            if (!user.wasWatched(i.title)) {
                message = "StandardRecommendation result: " + i.title;
                return message;
            }
        }

        message = "StandardRecommendation cannot be applied!";
        return message;
    }

    public String BestUnseen(final ArrayList<MyUser> users,
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

        videos.sort((v1, v2) -> Double.compare(v2.rating, v1.rating));

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

        if(videos.size() == 0) {
            message = new StringBuilder("SearchRecommendation cannot be applied!");
            return message.toString();
        }

        if (user.getSubscriptionType().equals("BASIC")) {
            message = new StringBuilder("SearchRecommendation cannot be applied!");
            return message.toString();
        }

        message = new StringBuilder("SearchRecommendation result: [");

        videos.sort((v1, v2) -> {
            if (v1.rating.equals(v2.rating)) {
                return v1.getTitle().compareTo(v2.getTitle());
            } else {
                return Double.compare(v1.rating, v2.rating);
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

        if(videos.size() == 0) {
            message = new StringBuilder("FavoriteRecommendation cannot be applied!");
            return message.toString();
        }

        if (user.getSubscriptionType().equals("BASIC")) {
            message = new StringBuilder("FavoriteRecommendation cannot be applied!");
            return message.toString();
        }

        message = new StringBuilder("FavoriteRecommendation result: ");

        videos.sort((v1, v2) -> Double.compare(v2.noFavorite, v1.noFavorite));

        message.append(videos.get(0).getTitle());
        return message.toString();
    }


    public String Popular(final ArrayList<MyUser> users,
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

        if(videos.size() == 0) {
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

