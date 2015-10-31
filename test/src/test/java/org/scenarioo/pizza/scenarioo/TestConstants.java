package org.scenarioo.pizza.scenarioo;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TestConstants {

    public static final String BRANCH = "gh-pages";
    public static final LocalDateTime DATE = LocalDateTime.now();
    public static final String BUILD = DATE.format(DateTimeFormatter.ISO_DATE_TIME);
    public static final File DOCU_FOLDER = new File("scenariooDocumentation");

}
