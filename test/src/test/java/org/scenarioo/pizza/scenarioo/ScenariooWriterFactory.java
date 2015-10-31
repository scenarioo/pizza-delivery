package org.scenarioo.pizza.scenarioo;

import org.scenarioo.api.ScenarioDocuWriter;

public class ScenariooWriterFactory {

    public static ScenarioDocuWriter getNewWriter() {
        return new ScenarioDocuWriter(TestConstants.DOCU_FOLDER, TestConstants.BRANCH, TestConstants.BUILD);
    }

}
