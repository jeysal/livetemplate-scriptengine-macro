package com.github.jeysal.intellij.plugin.livetemplate.scriptenginemacro.script.processing

import com.github.jeysal.intellij.plugin.livetemplate.scriptenginemacro.execution.data.Script

import java.util.function.BiFunction

/**
 * @author seckinger
 * @since 11/8/16
 */
trait SourceCodeScriptProcessor implements BiFunction<Object, String, Script> {
    Script apply(final obj, final String lang) {
        lang ? new Script(lang, obj.toString()) : super.apply(obj, lang)
    }
}
