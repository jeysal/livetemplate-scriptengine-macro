package com.github.jeysal.intellij.plugin.livetemplate.scriptenginemacro.execution.runner

import com.github.jeysal.intellij.plugin.livetemplate.scriptenginemacro.execution.data.Execution

import javax.script.ScriptEngine
import java.util.function.BiFunction

/**
 * @author seckinger
 * @since 1/1/17
 */
trait OutputInterceptingRunner implements BiFunction<Execution, ScriptEngine, Object> {
    @Override
    Object apply(Execution execution, ScriptEngine scriptEngine) {
        final out = new StringWriter()

        scriptEngine.context.writer = out
        scriptEngine.put '_out', out
        super.apply(execution, scriptEngine)
    }
}
