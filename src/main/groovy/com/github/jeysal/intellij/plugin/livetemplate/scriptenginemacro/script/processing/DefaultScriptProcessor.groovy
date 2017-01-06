package com.github.jeysal.intellij.plugin.livetemplate.scriptenginemacro.script.processing

import com.github.jeysal.intellij.plugin.livetemplate.scriptenginemacro.execution.data.Script

/**
 * @author seckinger
 * @since 10/24/16
 */
class DefaultScriptProcessor implements ScriptProcessor {
    @Override
    Script process(final obj, final String lang) {
        throw new RuntimeException("""failed to parse script from $obj
please consult the documentation for usage instructions""")
    }
}
