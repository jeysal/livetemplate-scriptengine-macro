package com.github.jeysal.intellij.plugin.livetemplate.scriptenginemacro.script.processing

import com.github.jeysal.intellij.plugin.livetemplate.scriptenginemacro.execution.data.Script
import org.apache.commons.io.FilenameUtils

import javax.script.ScriptEngineManager

/**
 * @author seckinger
 * @since 10/24/16
 */
trait FileScriptProcessor implements Processor<Object, String, Script> {
    private static final BASE_DIRS = ['', System.getProperty('user.home')]
    private final manager = new ScriptEngineManager()

    Script apply(final obj, final String lang) {
        if (obj instanceof CharSequence) {
            final param = obj.toString()
            final file = BASE_DIRS.collect { new File(it, param) }.find { it.exists() }

            if (file) {
                final newLang = lang ?:
                        manager.getEngineByExtension(FilenameUtils.getExtension(file.name))?.factory?.languageName

                if (newLang)
                    return new Script(newLang, file.text)
            }
        }
        super.call(obj, lang)
    }
}
