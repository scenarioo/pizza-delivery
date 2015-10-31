package org.scenarioo.pizza.scenarioo;

/**
 * Holds the UseCaseContest so that we can access it whenever needed.
 *
 * The current design only works for linear test execution. Think about using a ThreadLocal
 * when you start running web tests in parallel.
 */
public enum UseCaseContextHolder {

    INSTANCE;

    private UseCaseContext useCaseContext;

    public void setUseCaseContext(UseCaseContext useCaseContext) {
        this.useCaseContext = useCaseContext;
    }

    public UseCaseContext getUseCaseContext() {
        return useCaseContext;
    }

}
