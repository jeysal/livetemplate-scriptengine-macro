package com.github.jeysal.intellij.plugin.livetemplate.scriptenginemacro.conversion.script.processing

import com.github.jeysal.intellij.plugin.livetemplate.scriptenginemacro.execution.data.Script
import com.github.jeysal.intellij.plugin.livetemplate.scriptenginemacro.script.processing.ScriptProcessor
import org.junit.Rule
import org.junit.rules.TemporaryFolder
import spock.lang.Specification

import javax.script.ScriptEngineFactory
import javax.script.ScriptEngineManager

/**
 * @author seckinger
 * @since 10/24/16
 */
class ScriptProcessorTest extends Specification {
    private static final FILENAME_WITHOUT_EXT = 'test.'
    private static final FILE_CONTENTS = 'abc\nDeF\nXYZ'
    private static final List<ScriptEngineFactory> FACTORIES =
            new ScriptEngineManager().engineFactories.findAll { it.extensions }

    def proc = new ScriptProcessor()

    @Rule
    TemporaryFolder tmp = new TemporaryFolder()

    def 'reads an absolute path and finds a ScriptEngine for its extension'() {
        setup:
        final file = tmp.newFile(FILENAME_WITHOUT_EXT + engine.extensions[0])
        file.text = FILE_CONTENTS

        expect:
        proc.call(file.absolutePath, '') == new Script(engine.languageName, FILE_CONTENTS)

        where:
        engine << FACTORIES
    }

    def 'throws when passed any Object'() {
        when:
        proc.call(new Object(), null)

        then:
        thrown(RuntimeException)
    }
}
