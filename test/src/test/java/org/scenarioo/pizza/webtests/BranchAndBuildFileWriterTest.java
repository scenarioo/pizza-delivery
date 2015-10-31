package org.scenarioo.pizza.webtests;

import org.scenarioo.pizza.scenarioo.ScenariooWriterFactory;
import org.scenarioo.pizza.scenarioo.TestConstants;
import org.junit.Test;
import org.scenarioo.api.ScenarioDocuWriter;
import org.scenarioo.model.docu.entities.Branch;
import org.scenarioo.model.docu.entities.Build;
import org.scenarioo.model.docu.entities.Status;

import java.time.ZoneId;
import java.util.Date;

/**
 * This is not actually a test. We just use the fact that a test class
 * is run exactly once during the build so this is a good moment
 * to create the Scenarioo branch.xml and build.xml files. But there's also
 * a problem that the status is not written correctly. So we could either
 * create a test suite and run this at the end of the suite or then just write
 * the branch.xml and build.xml in the build script.
 */
public class BranchAndBuildFileWriterTest {

    @Test
    public void createBranchAndBuildFile() {
        TestConstants.DOCU_FOLDER.mkdirs();

        ScenarioDocuWriter writer = ScenariooWriterFactory.getNewWriter();

        writer.saveBranchDescription(createBranch());
        writer.saveBuildDescription(createBuild());

        writer.flush();
    }

    private Branch createBranch() {
        Branch branch = new Branch();
        branch.setName(TestConstants.BRANCH);
        return branch;
    }

    private Build createBuild() {
        Build build = new Build();
        build.setName(TestConstants.BUILD);
        // Here we should write the actual status of the entire build.
        build.setStatus(Status.SUCCESS);
        build.setDate(Date.from(TestConstants.DATE.atZone(ZoneId.systemDefault()).toInstant()));
        build.setRevision("unknown");
        return build;
    }

}
