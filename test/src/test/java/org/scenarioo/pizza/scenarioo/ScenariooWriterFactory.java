package org.scenarioo.pizza.scenarioo;

import org.scenarioo.api.ScenarioDocuWriter;

import java.io.File;

public class ScenariooWriterFactory {

    public static ScenarioDocuWriter getNewWriter() {
        TestConstants.DOCU_FOLDER.mkdirs();
        return new ScenarioDocuWriter(TestConstants.DOCU_FOLDER, TestConstants.BRANCH, TestConstants.BUILD);
    }

}
