package com.github.jeysal.intellij.plugin.livetemplate.scriptenginemacro.execution

import com.github.jeysal.intellij.plugin.livetemplate.scriptenginemacro.execution.data.Context
import com.github.jeysal.intellij.plugin.livetemplate.scriptenginemacro.execution.data.Execution
import com.github.jeysal.intellij.plugin.livetemplate.scriptenginemacro.execution.data.Goal
import com.github.jeysal.intellij.plugin.livetemplate.scriptenginemacro.execution.data.Script
import com.intellij.openapi.editor.Editor
import spock.lang.Specification

import javax.script.*

/**
 * @author seckinger
 * @since 11/29/16
 */
class ExecutorTest extends Specification {
    Executor exec = new Executor()

    ScriptEngineManager manager = Mock()
    ScriptEngineFactory factory = Mock()
    ScriptEngine engine = Mock()
    ScriptContext context = Mock()

    def setup() {
        exec.manager = manager
        factory.languageName >> 'lang'
        factory.scriptEngine >> engine
        engine.context >> context
        manager.engineFactories >> [factory]
    }

    def 'returns the script result'() {
        when:
        final res = exec.apply(new Execution(new Script('lang', 'src'), new Context([], Goal.RESULT, Mock(Editor))))

        then:
        1 * engine.eval('src') >> 'asdf'
        res == 'asdf'
    }

    def 'returns a thrown exception'() {
        setup:
        final ex = new ScriptException('')

        when:
        final res = exec.apply(new Execution(new Script('lang', 'src'), new Context([], Goal.RESULT, Mock(Editor))))

        then:
        1 * engine.eval('src') >> { throw ex }
        res == ex
    }

    def 'sets script variables'() {
        setup:
        Editor editor = Mock()

        when:
        exec.apply(new Execution(new Script('lang', 'src'), new Context(['arg0'], Goal.RESULT, editor)))

        then:
        1 * engine.put('_args', ['arg0'])
        1 * engine.put('_goal', 'RESULT')
        1 * engine.put('_editor', editor)
    }

    def 'sets the output writer'() {
        setup:
        Writer writer

        when:
        exec.apply(new Execution(new Script('lang', 'src'), new Context([], Goal.RESULT, Mock(Editor))))

        then:
        1 * context.setWriter({ writer = it })
        1 * engine.put('_out', { it == writer })
    }
}
