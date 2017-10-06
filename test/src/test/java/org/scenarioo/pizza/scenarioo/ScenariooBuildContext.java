package org.scenarioo.pizza.scenarioo;

import org.scenarioo.api.ScenarioDocuWriter;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

public class ScenariooBuildContext {

    private static final String BRANCH_NAME = createBranchName();
    private static final String BUILD_NAME = createBuildName();
    private static final File DOCUMENTATION_ROOT = new File("build/scenariooDocumentation");


    static {
        // Initialize the build at start-up. In real life use cases, this task would typically be performed
        // by the build environment.
        ScenariooBuildInitializer buildInitializer = new ScenariooBuildInitializer(DOCUMENTATION_ROOT, BRANCH_NAME, BUILD_NAME);
        buildInitializer.initialize(getNewWriter());
    }

    public static ScenarioDocuWriter getNewWriter() {
        return new ScenarioDocuWriter(DOCUMENTATION_ROOT, BRANCH_NAME, BUILD_NAME);
    }


    private static String createBranchName() {
        String name = getFromEnvironment("BRANCH_NAME").orElse("master");
        return "pizza-delivery-" + name;
    }

    private static String createBuildName() {
        return getFromEnvironment("BUILD_NUMBER")
                .orElseGet(() -> {
                    LocalDateTime now = LocalDateTime.now();
                    return now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                });
    }

    private static Optional<String> getFromEnvironment(String name) {
        String environmentVariable = System.getenv(name);
        return Optional.ofNullable(environmentVariable);
    }

}
