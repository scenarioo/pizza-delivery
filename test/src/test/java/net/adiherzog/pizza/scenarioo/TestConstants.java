package net.adiherzog.pizza.scenarioo;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TestConstants {

    public static final String BRANCH = "master";
    public static final String BUILD = LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME);
    public static final File DOCU_FOLDER = new File("scenariooDocumentation");

}
