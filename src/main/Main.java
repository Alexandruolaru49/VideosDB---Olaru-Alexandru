package main;

import checker.Checkstyle;
import checker.Checker;
import common.Constants;
import fileio.Input;
import fileio.InputLoader;
import fileio.Writer;
import myclasses.Commands.Command;
import myclasses.CopyClasses.MySerialInput;
import myclasses.CopyClasses.MyActor;
import myclasses.CopyClasses.MyMovie;
import myclasses.MyRead.ReadData;
import myclasses.CopyClasses.MyUser;
import myclasses.Query.QueryActors;
import myclasses.Query.QueryMovies;
import myclasses.Query.QueryShows;
import myclasses.Query.QueryUsers;
import myclasses.Recommendations.Recommendation;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Objects;

import fileio.ActionInputData;
import java.util.List;

/**
 * The entry point to this homework. It runs the checker that tests your implentation.
 */
public final class Main {
    /**
     * for coding style
     */
    private Main() {
    }

    /**
     * Call the main checker and the coding style checker
     * @param args from command line
     * @throws IOException in case of exceptions to reading / writing
     */
    public static void main(final String[] args) throws IOException {
        File directory = new File(Constants.TESTS_PATH);
        Path path = Paths.get(Constants.RESULT_PATH);
        if (!Files.exists(path)) {
            Files.createDirectories(path);
        }

        File outputDirectory = new File(Constants.RESULT_PATH);

        Checker checker = new Checker();
        checker.deleteFiles(outputDirectory.listFiles());

        for (File file : Objects.requireNonNull(directory.listFiles())) {

            String filepath = Constants.OUT_PATH + file.getName();
            File out = new File(filepath);
            boolean isCreated = out.createNewFile();
            if (isCreated) {
                action(file.getAbsolutePath(), filepath);
            }
        }

        checker.iterateFiles(Constants.RESULT_PATH, Constants.REF_PATH, Constants.TESTS_PATH);
        Checkstyle test = new Checkstyle();
        test.testCheckstyle();
    }

    /**
     * @param filePath1 for input file
     * @param filePath2 for output file
     * @throws IOException in case of exceptions to reading / writing
     */
    public static void action(final String filePath1,
                              final String filePath2) throws IOException {
        InputLoader inputLoader = new InputLoader(filePath1);
        Input input = inputLoader.readData();

        Writer fileWriter = new Writer(filePath2);
        JSONArray arrayResult = new JSONArray();

        //TODO add here the entry point to your implementation
        JSONObject obj = new JSONObject();

        ReadData readdata = new ReadData();
        ArrayList<MyActor> actors = readdata.readActors(input);
        ArrayList<MyMovie> movies = readdata.readMovies(input);
        ArrayList<MySerialInput> serials = readdata.readSerials(input);
        ArrayList<MyUser> users = readdata.readUsers(input);

        readdata.setViewsAndFavoritesMovies(movies, users);
        readdata.setViewsAndFavoritesSerials(serials, users);


        //PARCURG LISTA DE ACTIUNI
        for (ActionInputData i : input.getCommands()) {
            String message = "";

            //  COMMAND
            if (i.getActionType().equals("command")) {
                String title = i.getTitle();
                String name = i.getUsername();
                int contor;
                for (contor = 0; contor < users.size(); contor++) {
                    if (users.get(contor).getUsername().equals(name)) {
                        break;
                    }
                }

                Command command = new Command();

                // COMANDA FAVORITE
                if (i.getType().equals("favorite")) {
                    message = command.favorite(users, contor, title, movies, serials);
                }
                // COMANDA VIEW
                if (i.getType().equals("view")) {
                    message = command.view(users, contor, title, movies, serials);
                }
                //COMANDA RATING
                if (i.getType().equals("rating")) {
                    Double grade = i.getGrade();
                    int seasonNumber = i.getSeasonNumber();
                    message = command.rating(users, contor,
                            title, name, grade, seasonNumber, movies, serials);
                }
            }

            //  QUERY
            if (i.getActionType().equals("query")) {
                message = "";
                List<List<String>> filters = i.getFilters();
                int count = 0;
                List<String> year = filters.get(count);
                count++;
                List<String> genre = filters.get(count);
                count++;
                List<String> words = filters.get(count);
                count++;
                List<String> awards = filters.get(count);
                String sortType = i.getSortType();
                String criteria = i.getCriteria();
                int number = i.getNumber();

                if (i.getObjectType().equals("movies")) {

                    QueryMovies queryMovies = new QueryMovies();

                    ArrayList<MyMovie> movieList;
                    movieList = queryMovies.initializeMovies(genre, year, movies);

                    if (movieList.size() == 0) {
                        message = "Query result: []";
                    } else {
                        if (sortType.equals("asc")) {

                            if (criteria.equals("ratings")) {
                                message = queryMovies.ascRating(movieList, number);
                            }

                            if (criteria.equals("longest")) {
                                message = queryMovies.ascLongest(movieList, number);
                            }

                            if (criteria.equals("favorite")) {
                                message = queryMovies.ascFavorite(movieList, number);
                            }

                            if (criteria.equals("most_viewed")) {
                                message = queryMovies.ascViews(movieList, number);
                            }
                        }

                            if (sortType.equals("desc")) {

                                if (criteria.equals("ratings")) {
                                    message = queryMovies.descRating(movieList, number);
                                }

                                if (criteria.equals("longest")) {
                                    message = queryMovies.descLongest(movieList, number);
                                }

                                if (criteria.equals("favorite")) {
                                    message = queryMovies.descFavorite(movieList, number);
                                }

                                if (criteria.equals("most_viewed")) {
                                    message = queryMovies.descViews(movieList, number);
                                }
                            }
                    }
                }

                if (i.getObjectType().equals("shows")) {

                    ArrayList<MySerialInput> showList = new ArrayList<MySerialInput>();
                    QueryShows queryShows = new QueryShows();

                    queryShows.initializeShows(genre, year, serials, showList);

                    if (showList.size() == 0) {
                        message = "Query result: []";
                    } else {
                        if (sortType.equals("asc")) {

                            if (criteria.equals("ratings")) {
                                message = queryShows.ascRating(showList, number);
                            }

                            if (criteria.equals("longest")) {
                                message = queryShows.ascLongest(showList, number);
                            }

                            if (criteria.equals("favorite")) {
                                message = queryShows.ascFavorite(showList, number);
                            }

                            if (criteria.equals("most_viewed")) {
                                message = queryShows.ascViews(showList, number);
                            }
                        }

                        if (sortType.equals("desc")) {

                            if (criteria.equals("ratings")) {
                                message = queryShows.descRating(showList, number);
                            }

                            if (criteria.equals("longest")) {
                                message = queryShows.descLongest(showList, number);
                            }

                            if (criteria.equals("favorite")) {
                                message = queryShows.descFavorite(showList, number);
                            }

                            if (criteria.equals("most_viewed")) {
                                message = queryShows.descViews(showList, number);
                            }
                        }
                    }
                }

                if (i.getObjectType().equals("users")) {

                    ArrayList<MyUser> userList = new ArrayList<MyUser>();
                    QueryUsers queryUsers = new QueryUsers();

                    queryUsers.initializeUserslist(users, userList);

                    if (userList.size() == 0) {
                        message = "Query result: []";
                    } else {
                        if (sortType.equals("asc")) {
                            message = queryUsers.ascRatingsgiven(userList, number);

                        }

                        if (sortType.equals("desc")) {
                            message = queryUsers.descRatingsgiven(userList, number);
                        }

                    }
                }

                if (i.getObjectType().equals("actors")) {

                    ArrayList<MyActor> actorList = new ArrayList<MyActor>();
                    QueryActors queryActors = new QueryActors();

                    queryActors.initializeActors(actors, actorList);

                    if (actorList.size() == 0) {
                        message = "Query result: []";
                    } else {
                        if (sortType.equals("asc")) {

                            if (criteria.equals("average")) {
                                message = queryActors.ascAverage(actorList, number,
                                        movies, serials);
                            }

                            if (criteria.equals("awards")) {
                                message = queryActors.ascAwards(actors, awards);
                            }

                            if (criteria.equals("filter_description")) {
                                message = queryActors.ascDescription(actors, words);
                            }
                        }

                        if (sortType.equals("desc")) {

                            if (criteria.equals("average")) {
                                message = queryActors.descAverage(actorList, number,
                                        movies, serials);
                            }

                            if (criteria.equals("awards")) {
                                message = queryActors.descAwards(actors, awards);
                            }

                            if (criteria.equals("filter_description")) {
                                message = queryActors.descDescription(actors, words);
                            }
                        }

                    }
                }
            }

            // RECOMMENDATIONS
            if (i.getActionType().equals("recommendation")) {

                String genre = i.getGenre();

                String username = i.getUsername();
                Recommendation recommendation = new Recommendation();

                if (i.getType().equals("standard")) {
                    message = recommendation.recommendationStandard(users, username,
                            movies, serials);
                }

                if (i.getType().equals("best_unseen")) {
                    message = recommendation.bestUnseen(users, username, movies, serials);
                }

                if (i.getType().equals("search")) {
                    message = recommendation.recommendationSearch(users, genre,
                            username, movies, serials);
                }

                if (i.getType().equals("favorite")) {
                    message = recommendation.recommendationFavorite(users, username,
                            movies, serials);
                }

                if (i.getType().equals("popular")) {
                    message = recommendation.recommendationPopular(users, username,
                            movies, serials);
                }

            }

            obj = fileWriter.writeFile(i.getActionId(), "", message);
            arrayResult.add(obj);
        }
        fileWriter.closeJSON(arrayResult);
    }
}
