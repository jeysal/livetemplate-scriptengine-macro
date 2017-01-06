package com.github.jeysal.intellij.plugin.livetemplate.scriptenginemacro.execution.runner

import com.github.jeysal.intellij.plugin.livetemplate.scriptenginemacro.execution.data.Execution

import javax.script.ScriptEngine
import javax.script.ScriptException

/**
 * @author seckinger
 * @since 1/1/17
 */
trait ExceptionHandlingRunner implements Runner {
    @Override
    Object run(Execution execution, ScriptEngine scriptEngine) {
        try {
            super.run execution, scriptEngine
        } catch (ScriptException e) {
            e
        }
    }
}
