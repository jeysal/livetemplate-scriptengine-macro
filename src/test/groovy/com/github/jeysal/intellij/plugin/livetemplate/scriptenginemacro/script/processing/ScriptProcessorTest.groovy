package com.github.jeysal.intellij.plugin.livetemplate.scriptenginemacro.script.processing

import com.github.jeysal.intellij.plugin.livetemplate.scriptenginemacro.execution.data.Script
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
    private static final List<ScriptEngineFactory> FACTORIES = new ScriptEngineManager().engineFactories

    final proc = new ScriptProcessor()

    @Rule
    TemporaryFolder tmp = new TemporaryFolder()
    @Rule
    TemporaryFolder homeTmp = new TemporaryFolder(new File(System.getProperty('user.home')))

    def 'reads an absolute path and finds a ScriptEngine for its extension'() {
        setup:
        final file = tmp.newFile(FILENAME_WITHOUT_EXT + factory.extensions[0])
        file.text = FILE_CONTENTS

        expect:
        proc.apply(file.absolutePath) == new Script(factory.languageName, FILE_CONTENTS)

        where:
        factory << FACTORIES.findAll { it.extensions }
    }

    def 'reads a home directory path and finds a ScriptEngine for its extension'() {
        setup:
        final file = homeTmp.newFile(FILENAME_WITHOUT_EXT + factory.extensions[0])
        file.text = FILE_CONTENTS

        expect:
        proc.apply(file.path) == new Script(factory.languageName, FILE_CONTENTS)

        where:
        factory << FACTORIES.findAll { it.extensions }
    }

    def 'throws when passed any Object'() {
        when:
        proc.apply(new Object())

        then:
        thrown(RuntimeException)
    }
}
