package org.scenarioo.pizza.scenarioo;

import org.scenarioo.api.ScenarioDocuWriter;
import org.scenarioo.model.docu.entities.Branch;
import org.scenarioo.model.docu.entities.Build;

import java.io.File;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;


public class ScenariooBuildContext {

    private static final String BRANCH_NAME = "pizza-delivery-" + envOrDefault("BRANCH_NAME", "master");
    private static final String BUILD_NAME = envOrDefault("BUILD_NUMBER", "undefined");
    private static final File DOCUMENTATION_ROOT = new File("build/scenariooDocumentation");

    static {
        DOCUMENTATION_ROOT.mkdirs();    // Create folder for generated documentation
        ScenarioDocuWriter writer = getNewWriter();
        writer.saveBranchDescription(createBranch(BRANCH_NAME));    // Create 'branch.xml'
        writer.saveBuildDescription(createBuild(BUILD_NAME));       // Create 'build.xml'
        writer.flush();
    }

    public static ScenarioDocuWriter getNewWriter() {
        return new ScenarioDocuWriter(DOCUMENTATION_ROOT, BRANCH_NAME, BUILD_NAME);
    }

    private static Branch createBranch(String name) {
        Branch branch = new Branch();
        branch.setName(name);
        return branch;
    }

    private static Build createBuild(String name) {
        Build build = new Build();
        build.setName(name);
        build.setDate(getCurrentDate());
        build.setRevision(envOrDefault("GIT_COMMIT", "undefined"));
        return build;
    }

    private static String envOrDefault(String name, String defaultValue) {
        String environmentVariable = System.getenv(name);
        return environmentVariable != null ? environmentVariable : defaultValue;
    }

    private static Date getCurrentDate() {
        ZonedDateTime zonedDateTime = LocalDateTime.now().atZone(ZoneId.systemDefault());
        return Date.from(zonedDateTime.toInstant());
    }

}
