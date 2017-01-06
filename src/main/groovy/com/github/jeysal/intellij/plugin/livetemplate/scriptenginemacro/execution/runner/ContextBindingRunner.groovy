package com.github.jeysal.intellij.plugin.livetemplate.scriptenginemacro.execution.runner

import com.github.jeysal.intellij.plugin.livetemplate.scriptenginemacro.execution.data.Execution

import javax.script.ScriptEngine

/**
 * @author seckinger
 * @since 1/1/17
 */
trait ContextBindingRunner implements Runner {
    @Override
    Object run(Execution execution, ScriptEngine scriptEngine) {
        scriptEngine.put '_args', execution.context.args
        scriptEngine.put '_goal', execution.context.goal.toString()
        scriptEngine.put '_editor', execution.context.editor

        super.run execution, scriptEngine
    }
}
