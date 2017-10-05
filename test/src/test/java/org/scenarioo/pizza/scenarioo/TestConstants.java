package org.scenarioo.pizza.scenarioo;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TestConstants {

    public static final String BRANCH = System.getenv("BRANCH_NAME");
    public static final LocalDateTime DATE = LocalDateTime.now();
    public static final String BUILD = System.getenv("BUILD_NUMBER");
    public static final File DOCU_FOLDER;

    static {
        DOCU_FOLDER = new File("build/scenariooDocumentation");
        DOCU_FOLDER.mkdirs(); // ensure folder exists
    }

}
