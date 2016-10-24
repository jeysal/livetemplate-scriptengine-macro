package com.github.jeysal.intellij.plugin.livetemplate.scriptenginemacro.script.processing

import com.github.jeysal.intellij.plugin.livetemplate.scriptenginemacro.execution.data.Script

/**
 * @author seckinger
 * @since 10/24/16
 */
class DefaultScriptProcessor {
    Script call(Object obj, String lang) {
        throw new RuntimeException("""failed to parse script from $obj
please supply script of format type:source or [type:]path""")
    }
}
