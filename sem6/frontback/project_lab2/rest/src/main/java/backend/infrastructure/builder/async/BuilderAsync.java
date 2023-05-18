package backend.infrastructure.builder.async;

import backend.application.interfaces.async.Executable;
import backend.application.interfaces.async.timer.TimerServiceable;
import backend.application.interfaces.async.timer.TimerStorable;
import backend.application.interfaces.async.timer.Timerable;
import backend.infrastructure.builder.Built;
import jakarta.enterprise.inject.Default;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Inject;

public class BuilderAsync {

    @Inject
    @Default
    private Timerable timer;

    @Inject
    @Default
    private TimerServiceable timerService;

    @Inject
    @Default
    private Executable executor;

    @Inject
    @Default
    private TimerStorable store;

    @Produces
    @Built
    public Timerable buildTimer() {
        timer.assignExecutor(executor);
        timer.assignTimerService(timerService);
        timer.assignStore(store);
        return timer;
    }
}
