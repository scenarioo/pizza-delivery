package net.adiherzog.pizza.scenarioo;

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
 * to create the Scenarioo branch.xml and build.xml files.
 */
public class BranchAndBuildFileWriterTest {

    @Test
    public void createBranchAndBuildFile() {
        TestConstants.DOCU_FOLDER.mkdirs();

        ScenarioDocuWriter scenarioDocuWriter = ScenariooWriterFactory.getNewWriter();

        Branch branch = createBranch();
        scenarioDocuWriter.saveBranchDescription(branch);

        Build build = createBuild();
        scenarioDocuWriter.saveBuildDescription(build);

        scenarioDocuWriter.flush();
    }

    private Branch createBranch() {
        Branch branch = new Branch();
        branch.setName(TestConstants.BRANCH);
        return branch;
    }

    private Build createBuild() {
        Build build = new Build();
        build.setName(TestConstants.BUILD);
        build.setStatus(Status.SUCCESS);
        build.setDate(Date.from(TestConstants.DATE.atZone(ZoneId.systemDefault()).toInstant()));
        build.setRevision("unknown");
        return build;
    }

}
