package com.github.jeysal.intellij.plugin.livetemplate.scriptenginemacro.execution.runner;

import com.github.jeysal.intellij.plugin.livetemplate.scriptenginemacro.execution.data.Execution;

import javax.script.ScriptEngine;
import java.util.function.BiFunction;

/**
 * @author seckinger
 * @since 1/6/17
 */
@FunctionalInterface
public interface Runner extends BiFunction<Execution, ScriptEngine, Object> {
    Object run(Execution execution, ScriptEngine scriptEngine);

    @Override
    default Object apply(Execution execution, ScriptEngine scriptEngine) {
        return run(execution, scriptEngine);
    }
}
