package net.adiherzog.pizza.scenarioo;

/**
 * Holds the UseCaseContest so that we can access it in the @AfterClass method of each webtest.
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
