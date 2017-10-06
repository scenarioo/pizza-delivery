package org.scenarioo.pizza.scenarioo;

import org.scenarioo.api.ScenarioDocuWriter;
import org.scenarioo.model.docu.entities.Branch;
import org.scenarioo.model.docu.entities.Build;

import java.io.File;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

/**
 * Initializes a scenarioo-build by ensuring that the documentation-root exists and that the 'branch.xml'
 * and 'build.xml' files are created.
 */
public class ScenariooBuildInitializer {

    private final File documentationRoot;
    private final String branchName;
    private final String buildName;

    public ScenariooBuildInitializer(File documentationRoot, String branchName, String buildName) {
        this.documentationRoot = documentationRoot;
        this.branchName = branchName;
        this.buildName = buildName;
        ensureRootFolderExists();
    }

    public void initialize(ScenarioDocuWriter writer) {
        createBranchAndBuildFile(writer);
    }

    private void ensureRootFolderExists() {
        documentationRoot.mkdirs();
    }


    private void createBranchAndBuildFile(ScenarioDocuWriter writer) {
        writer.saveBranchDescription(createBranch());
        writer.saveBuildDescription(createBuild());
        writer.flush();
    }

    private Branch createBranch() {
        Branch branch = new Branch();
        branch.setName(branchName);
        return branch;
    }

    private Build createBuild() {
        Build build = new Build();
        build.setName(buildName);
        build.setDate(getCurrentDate());
        build.setRevision("unknown");
        return build;
    }

    private Date getCurrentDate() {
        LocalDateTime localDateTime = LocalDateTime.now();
        ZonedDateTime zonedDateTime = localDateTime.atZone(ZoneId.systemDefault());
        return Date.from(zonedDateTime.toInstant());
    }
}
