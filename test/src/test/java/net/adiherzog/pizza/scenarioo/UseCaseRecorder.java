package net.adiherzog.pizza.scenarioo;

import org.scenarioo.api.ScenarioDocuWriter;
import org.scenarioo.model.docu.entities.Status;
import org.scenarioo.model.docu.entities.UseCase;

public class UseCaseRecorder {

    private UseCaseContext useCaseContext;

    public UseCaseRecorder(UseCaseContext useCaseContext) {
        this.useCaseContext = useCaseContext;
    }

    public void recordUseCase() {
        ScenarioDocuWriter scenarioDocuWriter = ScenariooWriterFactory.getNewWriter();
        scenarioDocuWriter.saveUseCase(createUseCase());
        scenarioDocuWriter.flush();
    }

    private UseCase createUseCase() {
        UseCase useCase = new UseCase();
        useCase.setName(useCaseContext.getUseCaseName());
        useCase.addLabel("important");
        useCase.setStatus(Status.SUCCESS);
        return useCase;
    }

}
