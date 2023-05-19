package backend.infrastructure.builder;

import backend.application.in.async.api.Notificationable;
import backend.application.out.Executable;
import backend.application.out.Updatable;
import jakarta.enterprise.inject.Default;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Inject;

public class Builder {
    @Inject
    @Default
    private Notificationable app;

    @Inject
    @Default
    private Executable executor;

    @Inject
    @Default
    private Updatable updater;

    @Produces
    @Built
    public Notificationable build() {
        app.assignExecutor(executor);
        app.assignUpdater(updater);
        return app;
    }

}
