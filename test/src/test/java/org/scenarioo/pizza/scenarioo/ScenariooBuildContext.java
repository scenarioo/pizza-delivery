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

    private static final String BRANCH_NAME = "pizza-delivery-" + getEnvironmentVariableOrDefault("BRANCH_NAME", "master");
    private static final String BUILD_NAME = "build-" + getEnvironmentVariableOrDefault("BUILD_NUMBER", "undefined");
    private static final File DOCUMENTATION_ROOT = new File("build/scenariooDocumentation");

    static {
        // on startup - once per test run:
        initScenariooReportForCurrentBuildRun();
    }

    /**
     * Create directory to store the scenarioo report files inside
     * and store the needed description files for the branch and the build.
     * This has to be done at least once for a full scenarioo report build.
     */
    private static void initScenariooReportForCurrentBuildRun() {
        DOCUMENTATION_ROOT.mkdirs();
        ScenarioDocuWriter writer = getNewWriter();
        writer.saveBranchDescription(createBranch(BRANCH_NAME));
        writer.saveBuildDescription(createBuild(BUILD_NAME));
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
        build.setDate(new Date());
        build.setRevision(getEnvironmentVariableOrDefault("GIT_COMMIT", "undefined"));
        return build;
    }

    private static String getEnvironmentVariableOrDefault(String name, String defaultValue) {
        String environmentVariable = System.getenv(name);
        return environmentVariable != null ? environmentVariable : defaultValue;
    }
}
