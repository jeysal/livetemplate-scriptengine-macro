package com.github.jeysal.intellij.plugin.livetemplate.scriptenginemacro.script.processing

import com.github.jeysal.intellij.plugin.livetemplate.scriptenginemacro.execution.data.Script

import java.util.function.Function

/**
 * @author seckinger
 * @since 10/24/16
 */
class ScriptProcessor extends DefaultScriptProcessor implements Function<Object, Script>,
        PrefixScriptProcessor,
        FileScriptProcessor {
    Script apply(final Object obj) {
        apply(obj, '')
    }
}
