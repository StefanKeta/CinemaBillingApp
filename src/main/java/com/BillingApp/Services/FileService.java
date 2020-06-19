package com.BillingApp.Services;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileService {
    public static String APP_FOLDER = ".BillingApp";
    public static final String USER_FOLDER = "user.home";
    public static final Path APPLICATION_HOME_PATH = Paths.get(USER_FOLDER,APP_FOLDER);

    public static Path getPathToFile(String... path) {
        return APPLICATION_HOME_PATH.resolve(Paths.get(".", path));
    }

    public static Path getApplicationHomePath() {
        return Paths.get(USER_FOLDER, APP_FOLDER);
    }

    public static void initApplicationHomeDirIfNeeded() {
        if (!Files.exists(getApplicationHomePath()))
            getApplicationHomePath().toFile().mkdirs();
    }
}
