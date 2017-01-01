package com.github.jeysal.intellij.plugin.livetemplate.scriptenginemacro.execution.runner

import com.github.jeysal.intellij.plugin.livetemplate.scriptenginemacro.execution.data.Execution

import javax.script.ScriptEngine
import javax.script.ScriptException
import java.util.function.BiFunction

/**
 * @author seckinger
 * @since 1/1/17
 */
trait ExceptionHandlingRunner implements BiFunction<Execution, ScriptEngine, Object> {
    @Override
    Object apply(Execution execution, ScriptEngine scriptEngine) {
        try {
            super.apply(execution, scriptEngine)
        } catch (ScriptException e) {
            e
        }
    }
}
