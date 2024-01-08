package builder;

public class ThreadBuilder {
    private final Runnable target;
    private final String name;
    private ThreadGroup group;
    private long stackSize = 0;
    private boolean inheritThreadLocals = true;
    private Boolean daemon;
    private Integer priority;
    private Thread.UncaughtExceptionHandler uncaughtExceptionHandler;

    public ThreadBuilder(Runnable target, String name) {
        this.target = target;
        this.name = name;
    }

    public Thread build() {
        Thread thread = new Thread(group, target, name, stackSize, inheritThreadLocals);
        if (daemon != null) thread.setDaemon(daemon);
        if (priority != null) thread.setPriority(priority);
        if (uncaughtExceptionHandler != null) thread.setUncaughtExceptionHandler(uncaughtExceptionHandler);
        return thread;
    }

    public ThreadBuilder threadGroup(ThreadGroup threadGroup) {
        this.group = threadGroup;
        return this;
    }

    public ThreadBuilder stackSize(long stackSize) {
        this.stackSize = stackSize;
        return this;
    }

    public ThreadBuilder inheritThreadLocals(boolean inheritThreadLocals) {
        this.inheritThreadLocals = inheritThreadLocals;
        return this;
    }

    public ThreadBuilder daemon(boolean daemon) {
        this.daemon = daemon;
        return this;
    }

    public ThreadBuilder priority(int priority) {
        this.priority = priority;
        return this;
    }

    public ThreadBuilder uncaughtExceptionHandler(Thread.UncaughtExceptionHandler uncaughtExceptionHandler) {
        this.uncaughtExceptionHandler = uncaughtExceptionHandler;
        return this;
    }
}

