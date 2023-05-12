package backend.infrastructure.builder;

import backend.application.async.api.Executable;
import backend.application.async.api.Notificationable;
import backend.application.async.api.Updatable;
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
