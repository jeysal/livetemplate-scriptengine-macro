package com.github.jeysal.intellij.plugin.livetemplate.scriptenginemacro.execution

import com.github.jeysal.intellij.plugin.livetemplate.scriptenginemacro.execution.data.Execution
import com.github.jeysal.intellij.plugin.livetemplate.scriptenginemacro.execution.runner.Runner

import javax.script.ScriptEngineManager
import java.util.function.Function

/**
 * @author seckinger
 * @since 11/22/16
 */
class Executor implements Function<Execution, Object> {
    ScriptEngineManager manager = new ScriptEngineManager()
    Runner runner = new Runner()

    @Override
    Object apply(Execution execution) {
        // find engine
        final lang = execution.script.language
        final engine = manager.engineFactories.find {
            it.languageName == lang
        }.scriptEngine

        if (!engine)
            throw new RuntimeException("Failed to find ScriptEngine for language $lang")

        // run it
        runner.apply(execution, engine)
    }
}
