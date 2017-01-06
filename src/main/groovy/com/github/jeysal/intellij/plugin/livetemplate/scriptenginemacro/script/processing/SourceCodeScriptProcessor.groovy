package com.github.jeysal.intellij.plugin.livetemplate.scriptenginemacro.script.processing

import com.github.jeysal.intellij.plugin.livetemplate.scriptenginemacro.execution.data.Script

/**
 * @author seckinger
 * @since 11/8/16
 */
trait SourceCodeScriptProcessor implements ScriptProcessor {
    @Override
    Script process(final obj, final String lang) {
        lang ? new Script(lang, obj.toString()) : super.process(obj, lang)
    }
}
