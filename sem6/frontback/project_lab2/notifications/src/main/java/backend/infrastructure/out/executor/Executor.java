package backend.infrastructure.out.executor;

import backend.application.async.api.Executable;
import jakarta.annotation.Resource;
import jakarta.enterprise.concurrent.ManagedExecutorService;

public class Executor implements Executable {

    @Resource
    private ManagedExecutorService executorService;

    @Override
    public void execute(Runnable thread) {
        executorService.execute(thread);
    }

}