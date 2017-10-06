package org.scenarioo.pizza.scenarioo;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

public class TestConstants {

    public static final String BRANCH = getEnvOrDefault("BRANCH_NAME", "gh-pages");
    public static final LocalDateTime DATE = LocalDateTime.now();
    public static final String BUILD = getEnvOrDefault("BUILD_NUMBER", DATE.format(DateTimeFormatter.ISO_DATE_TIME));
    public static final File DOCU_FOLDER;

    static {
        DOCU_FOLDER = new File("build/scenariooDocumentation");
        DOCU_FOLDER.mkdirs(); // ensure folder exists
    }

    private static String getEnvOrDefault(String name, String defaultValue) {
        return Optional.ofNullable(System.getenv(name)).orElse(defaultValue);
    }

}
