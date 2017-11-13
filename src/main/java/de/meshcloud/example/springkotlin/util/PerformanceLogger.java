package de.meshcloud.example.springkotlin.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.function.Supplier;

public class PerformanceLogger {

    private static Logger log = LoggerFactory.getLogger(PerformanceLogger.class);

    // In Java there are Suppliers, Consumers, Predicates, BiConsumers, etc
    // see https://docs.oracle.com/javase/8/docs/api/java/util/function/package-summary.html
    public static <T> T logPerformance(Supplier<T> fn, String name) {
        log.info(String.format("starting '%s' at %tc", name, new Date()));
        T result = fn.get();
        log.info(String.format("finished '%s' at %tc", name, new Date()));
        return result;
    }
}
