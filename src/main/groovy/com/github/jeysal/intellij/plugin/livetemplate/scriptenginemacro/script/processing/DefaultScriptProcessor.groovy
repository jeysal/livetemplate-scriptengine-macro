package com.github.jeysal.intellij.plugin.livetemplate.scriptenginemacro.script.processing

import com.github.jeysal.intellij.plugin.livetemplate.scriptenginemacro.execution.data.Script

import java.util.function.BiFunction

/**
 * @author seckinger
 * @since 10/24/16
 */
class DefaultScriptProcessor implements BiFunction<Object, String, Script> {
    Script apply(final obj, final String lang) {
        throw new RuntimeException("""failed to parse script from $obj
please supply script of format type:source or [type:]path""")
    }
}
