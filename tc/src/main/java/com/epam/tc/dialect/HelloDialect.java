package com.epam.tc.dialect;

import java.util.HashSet;
import java.util.Set;

import org.thymeleaf.dialect.AbstractProcessorDialect;
import org.thymeleaf.processor.IProcessor;

public class HelloDialect extends AbstractProcessorDialect {

    public HelloDialect() {
        super("Hello Dialect", "hello", 1000);
    }

    /*
     * The processors.
     */
    @Override
    public Set<IProcessor> getProcessors(final String dialectPrefix) {
        final Set<IProcessor> processors = new HashSet<>();
        processors.add(new SayToAttributeTagProcessor(dialectPrefix));
        return processors;
    }

}
