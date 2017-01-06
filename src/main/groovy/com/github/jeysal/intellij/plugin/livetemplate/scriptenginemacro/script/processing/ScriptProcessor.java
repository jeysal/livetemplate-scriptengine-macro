package com.github.jeysal.intellij.plugin.livetemplate.scriptenginemacro.script.processing;

import com.github.jeysal.intellij.plugin.livetemplate.scriptenginemacro.execution.data.Script;

import java.util.function.Function;

/**
 * @author seckinger
 * @since 1/6/17
 */
@FunctionalInterface
public interface ScriptProcessor extends Function<Object, Script> {
    Script process(Object obj, String lang);

    default Script process(Object obj) {
        return process(obj, "");
    }

    @Override
    default Script apply(Object obj) {
        return process(obj);
    }
}
