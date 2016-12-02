package org.vaadin.artur.tbparameterized.runner;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.junit.runners.Parameterized;

import com.vaadin.testbench.parallel.ParallelScheduler;

public class ParallelParameterized extends Parameterized {

    private static final ExecutorService THREAD_POOL = Executors
            .newFixedThreadPool(10);

    public ParallelParameterized(Class<?> klass) throws Throwable {
        super(klass);
        setScheduler(new ParallelScheduler(THREAD_POOL));
    }

}
