package cn.enn.bigdata.dataanalysis.concurrent;

import javafx.util.Builder;
import lombok.Data;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

@Component
@Data
@Lazy
public class TaskThreadFactory implements Builder<ThreadFactory> {
    private String threadPrefixName;

    public static TaskThreadFactory getInstance() {
        return new TaskThreadFactory();
    }

    public TaskThreadFactory setThreadPrefixName(String name) {
        this.threadPrefixName = name;
        return this;
    }

    /**
     * Builds and returns the object.
     */
    @Override
    public ThreadFactory build() {
        final ThreadFactory innerThreadFactory = Executors.defaultThreadFactory();
        AtomicInteger index = new AtomicInteger();
        return new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                Thread thread = innerThreadFactory.newThread(r);
                if (!threadPrefixName.isEmpty()) {
                    thread.setName(threadPrefixName + "-" + index.getAndIncrement());
                }
                return thread;
            }
        };
    }

}
