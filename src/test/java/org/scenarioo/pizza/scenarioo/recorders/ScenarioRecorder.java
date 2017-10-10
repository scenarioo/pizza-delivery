package org.scenarioo.pizza.scenarioo.recorders;

import org.scenarioo.pizza.scenarioo.UseCaseContext;
import org.scenarioo.model.docu.entities.Scenario;
import org.scenarioo.model.docu.entities.Status;

public class ScenarioRecorder {

    private UseCaseContext useCaseContext;

    public ScenarioRecorder(UseCaseContext useCaseContext) {
        this.useCaseContext = useCaseContext;
    }

    public void recordScenario(Status scenarioStatus) {
        useCaseContext.getWriter().saveScenario(useCaseContext.getUseCaseName(), createScenario(scenarioStatus));
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
