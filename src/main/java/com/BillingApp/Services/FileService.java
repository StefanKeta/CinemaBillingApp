package com.BillingApp.Services;

import java.nio.file.Path;
import java.nio.file.Paths;

public class FileService {
    private static final String APP_FOLDER = ".BillingApp";
    private static final String USER_FOLDER = "user.home";
    private static final String MOVIE_FOLDER = "Movies";
    public static final Path APPLICATION_HOME_PATH = Paths.get(USER_FOLDER,APP_FOLDER);
    public static final Path MOVIES_PATH = Paths.get(MOVIE_FOLDER,APP_FOLDER);

    public static Path getPathToFile(String... path) {
        return APPLICATION_HOME_PATH.resolve(Paths.get(".", path));
    }
    public static Path getMoviesPath(String ... path) { return MOVIES_PATH.resolve(Paths.get(".",path));}
}
