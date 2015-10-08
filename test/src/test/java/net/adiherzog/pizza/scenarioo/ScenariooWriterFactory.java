package net.adiherzog.pizza.scenarioo;

import org.scenarioo.api.ScenarioDocuWriter;

import static net.adiherzog.pizza.scenarioo.TestConstants.*;

public class ScenariooWriterFactory {

    public static ScenarioDocuWriter getNewWriter() {
        return new ScenarioDocuWriter(DOCU_FOLDER, BRANCH, BUILD);
    }

}
