package com.github.jeysal.intellij.plugin.livetemplate.scriptenginemacro.execution.runner

import com.github.jeysal.intellij.plugin.livetemplate.scriptenginemacro.execution.data.Execution

import javax.script.ScriptEngine
import java.util.function.BiFunction

/**
 * @author seckinger
 * @since 1/1/17
 */
trait ContextBindingRunner implements BiFunction<Execution, ScriptEngine, Object> {
    @Override
    Object apply(Execution execution, ScriptEngine scriptEngine) {
        scriptEngine.put '_args', execution.context.args
        scriptEngine.put '_goal', execution.context.goal.toString()
        scriptEngine.put '_editor', execution.context.editor
        super.apply(execution, scriptEngine)
    }
}
