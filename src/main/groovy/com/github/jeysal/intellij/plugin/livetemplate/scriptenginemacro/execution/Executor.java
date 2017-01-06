package com.github.jeysal.intellij.plugin.livetemplate.scriptenginemacro.execution;

import com.github.jeysal.intellij.plugin.livetemplate.scriptenginemacro.execution.data.Execution;

import java.util.function.Function;

/**
 * @author seckinger
 * @since 1/6/17
 */
@FunctionalInterface
public interface Executor extends Function<Execution, Object> {
    Object execute(Execution execution);

    @Override
    default Object apply(Execution execution) {
        return execute(execution);
    }
}
