package com.github.jeysal.intellij.plugin.livetemplate.scriptenginemacro.execution.runner

import com.github.jeysal.intellij.plugin.livetemplate.scriptenginemacro.execution.data.Execution

import javax.script.ScriptEngine

/**
 * @author seckinger
 * @since 1/1/17
 */
class SimpleRunner implements Runner {
    @Override
    Object run(Execution execution, ScriptEngine scriptEngine) {
        scriptEngine.eval execution.script.source
    }
}
