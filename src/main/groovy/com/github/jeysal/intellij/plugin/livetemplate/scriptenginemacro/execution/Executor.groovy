package com.github.jeysal.intellij.plugin.livetemplate.scriptenginemacro.execution

import com.github.jeysal.intellij.plugin.livetemplate.scriptenginemacro.execution.data.Execution

import javax.script.ScriptEngineManager
import java.util.function.Function

/**
 * @author seckinger
 * @since 11/22/16
 */
class Executor implements Function<Execution, Object> {
    ScriptEngineManager manager = new ScriptEngineManager()

    @Override
    Object apply(Execution execution) {
        null
    }
}
