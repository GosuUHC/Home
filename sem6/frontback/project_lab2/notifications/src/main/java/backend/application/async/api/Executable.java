package backend.application.async.api;

public interface Executable {
    public void execute(Runnable thread);
}
