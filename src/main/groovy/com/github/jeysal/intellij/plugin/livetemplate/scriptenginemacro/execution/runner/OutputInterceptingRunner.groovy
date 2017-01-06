package com.github.jeysal.intellij.plugin.livetemplate.scriptenginemacro.execution.runner

import com.github.jeysal.intellij.plugin.livetemplate.scriptenginemacro.execution.data.Execution

import javax.script.ScriptEngine

/**
 * @author seckinger
 * @since 1/1/17
 */
trait OutputInterceptingRunner implements Runner {
    @Override
    Object run(Execution execution, ScriptEngine scriptEngine) {
        final out = new StringWriter()

        scriptEngine.context.writer = out
        scriptEngine.put '_out', out

        super.run execution, scriptEngine
    }
}
