package org.scenarioo.pizza.scenarioo;

import org.scenarioo.api.ScenarioDocuWriter;
import org.scenarioo.api.util.IdentifierSanitizer;
import org.scenarioo.model.docu.entities.Branch;
import org.scenarioo.model.docu.entities.Build;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * Global context to setup basic scenarioo reporting for one scenarioo documentation build.
 *
 * Defines where the build is stored (root dir, branch and build name) and provides global access to get a writer for writing into this scenarioo build reports.
 */
public class ScenariooBuildContext {

    private static final String BRANCH_NAME = getBranchNameForCurrentBuildRun();
    private static final String BUILD_NAME = getBuildNameForCurrentBuildRun();
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

    /**
     * Get a scenarioo writer to write reports for this build.
     * You could use multiple writer instances (e.g. in a multithreaded setup)
     * to write into the same reports. Each writer can only be used once
     * until `flush()` is called to finish writing (which the writer does asynch).
     */
    public static ScenarioDocuWriter getNewWriter() {
        return new ScenarioDocuWriter(DOCUMENTATION_ROOT, BRANCH_NAME, BUILD_NAME);
    }
    
    /**    
     * Define a branch name to identify which branch of your software under test is tested and documented in the scenarioo reports.     
     *
     * If you do not want to support documentation for multiple branches, you could of course simply use a constant here for a start.
     */    
    private static String getBranchNameForCurrentBuildRun() {
        // Define your branch name, as you wish, e.g. by reading current branch name from build environment        
        String branchName = getEnvironmentVariableOrDefault("BRANCH_NAME", "local");
        // Since scenarioo can currently not handle special characters like `/` in identifiers
        // we have to currently sanitize those (to be able to handle branch names like for example `feature/my-branch-name`).
        String sanitizedBranchName = IdentifierSanitizer.sanitize(branchName);        
        return "pizza-delivery-" + sanitizedBranchName;
    } 
    
    /**
     * Define a unique build identifier name to be used to identify the generated documentation build in scenarioo.
     */
    private static String getBuildNameForCurrentBuildRun() {
        return "build-" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy-hhmmss"));
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
