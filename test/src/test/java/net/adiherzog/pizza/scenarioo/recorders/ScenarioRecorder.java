package net.adiherzog.pizza.scenarioo.recorders;

import net.adiherzog.pizza.scenarioo.ScenariooWriterFactory;
import net.adiherzog.pizza.scenarioo.UseCaseContext;
import org.scenarioo.api.ScenarioDocuWriter;
import org.scenarioo.model.docu.entities.Scenario;
import org.scenarioo.model.docu.entities.Status;

public class ScenarioRecorder {

    private UseCaseContext useCaseContext;

    public ScenarioRecorder(UseCaseContext useCaseContext) {
        this.useCaseContext = useCaseContext;
    }

    public void recordScenario(Status scenarioStatus) {
        ScenarioDocuWriter scenarioDocuWriter = ScenariooWriterFactory.getNewWriter();
        scenarioDocuWriter.saveScenario(useCaseContext.getUseCaseName(), createScenario(scenarioStatus));
        scenarioDocuWriter.flush();
    }

    private Scenario createScenario(Status scenarioStatus) {
        Scenario scenario = new Scenario();
        scenario.setName(useCaseContext.getScenarioName());
        scenario.setStatus(scenarioStatus);
        scenario.setLabels(useCaseContext.getScenarioLabels());
        scenario.setDescription(useCaseContext.getScenarioDescription());
        return scenario;
    }

}
