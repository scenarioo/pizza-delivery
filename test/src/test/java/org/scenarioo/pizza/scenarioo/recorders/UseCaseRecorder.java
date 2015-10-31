package org.scenarioo.pizza.scenarioo.recorders;

import org.scenarioo.pizza.scenarioo.UseCaseContext;
import org.scenarioo.model.docu.entities.UseCase;

public class UseCaseRecorder {

    private UseCaseContext useCaseContext;

    public UseCaseRecorder(UseCaseContext useCaseContext) {
        this.useCaseContext = useCaseContext;
    }

    public void recordUseCase() {
        useCaseContext.getWriter().saveUseCase(createUseCase());
    }

    private UseCase createUseCase() {
        UseCase useCase = new UseCase();
        useCase.setName(useCaseContext.getUseCaseName());
        useCase.setDescription(useCaseContext.getUseCaseDescription());
        // We don't need to set the status here. It is automatically calculated
        // from all the child scenario's statuses.
        return useCase;
    }

}
