package com.github.jeysal.intellij.plugin.livetemplate.scriptenginemacro.script.processing

import com.github.jeysal.intellij.plugin.livetemplate.scriptenginemacro.execution.data.Script

import javax.script.ScriptEngineManager

/**
 * @author seckinger
 * @since 11/7/16
 */
trait PrefixScriptProcessor implements ScriptProcessor {
    private final manager = new ScriptEngineManager()

    @Override
    Script process(final obj, final String lang) {
        if (!lang && obj instanceof CharSequence) {
            final param = obj.toString()
            final firstColonIndex = param.indexOf(':')

            if (firstColonIndex >= 0) {
                final prefix = param[0..<firstColonIndex]
                final newLang = (manager.getEngineByName(prefix) ?:
                        manager.getEngineByMimeType(prefix) ?:
                                manager.getEngineByExtension(prefix))?.factory?.languageName

                if (newLang)
                    return process(param[(firstColonIndex + 1)..-1], newLang)
            }
        }
        super.process(obj, lang)
    }
}
