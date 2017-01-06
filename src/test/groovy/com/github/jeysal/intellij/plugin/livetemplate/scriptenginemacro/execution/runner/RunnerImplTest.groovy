package com.github.jeysal.intellij.plugin.livetemplate.scriptenginemacro.execution.runner

import com.github.jeysal.intellij.plugin.livetemplate.scriptenginemacro.execution.data.Context
import com.github.jeysal.intellij.plugin.livetemplate.scriptenginemacro.execution.data.Execution
import com.github.jeysal.intellij.plugin.livetemplate.scriptenginemacro.execution.data.Goal
import com.github.jeysal.intellij.plugin.livetemplate.scriptenginemacro.execution.data.Script
import com.intellij.openapi.editor.Editor
import spock.lang.Specification

import javax.script.ScriptContext
import javax.script.ScriptEngine
import javax.script.ScriptException

/**
 * @author seckinger
 * @since 1/2/17
 */
class RunnerImplTest extends Specification {
    RunnerImpl runner = new RunnerImpl()

    ScriptEngine engine = Mock()
    ScriptContext context = Mock()

    def setup() {
        engine.context >> context
    }

    def 'returns the script result'() {
        when:
        final res = runner.run(
                new Execution(new Script('lang', 'src'), new Context([], Goal.RESULT, Mock(Editor))),
                engine
        )

        then:
        1 * engine.eval('src') >> 'asdf'
        res == 'asdf'
    }

    def 'returns a thrown exception'() {
        setup:
        final ex = new ScriptException('')

        when:
        final res = runner.run(
                new Execution(new Script('lang', 'src'), new Context([], Goal.RESULT, Mock(Editor))),
                engine
        )

        then:
        1 * engine.eval('src') >> { throw ex }
        res == ex
    }

    def 'sets script variables'() {
        setup:
        Editor editor = Mock()

        when:
        runner.run(
                new Execution(new Script('lang', 'src'), new Context(['arg0'], Goal.RESULT, editor)),
                engine
        )

        then:
        1 * engine.put('_args', ['arg0'])
        1 * engine.put('_goal', 'RESULT')
        1 * engine.put('_editor', editor)
    }

    def 'sets the output writer'() {
        setup:
        Writer writer

        when:
        runner.run(
                new Execution(new Script('lang', 'src'), new Context([], Goal.RESULT, Mock(Editor))),
                engine
        )

        then:
        1 * context.setWriter({ writer = it })
        1 * engine.put('_out', { it == writer })
    }
}
