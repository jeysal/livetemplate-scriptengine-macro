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

    private static final SCRIPT_SOURCE = 'asdf'

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

    def 'reads a path prefixed with a mime type'() {
        setup:
        final file = tmp.newFile()
        file.text = FILE_CONTENTS

        expect:
        proc.apply(factory.mimeTypes[0] + ':' + file.absolutePath) == new Script(factory.languageName, FILE_CONTENTS)

        where:
        factory << FACTORIES.findAll { it.mimeTypes }
    }

    def 'reads a path prefixed with a name'() {
        setup:
        final file = tmp.newFile()
        file.text = FILE_CONTENTS

        expect:
        proc.apply(factory.names[0] + ':' + file.absolutePath) == new Script(factory.languageName, FILE_CONTENTS)

        where:
        factory << FACTORIES.findAll { it.names }
    }

    def 'throws when passed a prefixed directory path'() {
        setup:
        final dir = tmp.newFolder()

        when:
        proc.apply(factory.names[0] + ':' + dir.absolutePath)

        then:
        thrown(FileNotFoundException)

        where:
        factory << FACTORIES.findAll { it.names }
    }

    def 'throws when passed a directory path with an extension'() {
        setup:
        final dir = tmp.newFolder(FILENAME_WITHOUT_EXT + factory.extensions[0])

        when:
        proc.apply(dir.absolutePath)

        then:
        thrown(FileNotFoundException)

        where:
        factory << FACTORIES.findAll { it.extensions }
    }

    def 'reads source code prefixed with a name'() {
        expect:
        proc.apply(factory.names[0] + ':' + SCRIPT_SOURCE) == new Script(factory.languageName, SCRIPT_SOURCE)

        where:
        factory << FACTORIES.findAll { it.names }
    }

    def 'reads source code prefixed with a mime type'() {
        expect:
        proc.apply(factory.mimeTypes[0] + ':' + SCRIPT_SOURCE) == new Script(factory.languageName, SCRIPT_SOURCE)

        where:
        factory << FACTORIES.findAll { it.mimeTypes }
    }

    def 'throws when passed any Object'() {
        when:
        proc.apply(new Object())

        then:
        thrown(RuntimeException)
    }
}
