package com.github.jeysal.intellij.plugin.livetemplate.scriptenginemacro.execution

import com.github.jeysal.intellij.plugin.livetemplate.scriptenginemacro.execution.data.Context
import com.github.jeysal.intellij.plugin.livetemplate.scriptenginemacro.execution.data.Execution
import com.github.jeysal.intellij.plugin.livetemplate.scriptenginemacro.execution.data.Goal
import com.github.jeysal.intellij.plugin.livetemplate.scriptenginemacro.execution.data.Script
import com.github.jeysal.intellij.plugin.livetemplate.scriptenginemacro.execution.runner.Runner
import com.intellij.openapi.editor.Editor
import spock.lang.Specification

import javax.script.ScriptEngine
import javax.script.ScriptEngineFactory
import javax.script.ScriptEngineManager

/**
 * @author seckinger
 * @since 11/29/16
 */
class ExecutorImplTest extends Specification {
    ExecutorImpl exec = new ExecutorImpl()
    ScriptEngineManager manager = Mock()
    Runner runner = Mock()

    def setup() {
        exec.manager = manager
        exec.runner = runner
    }

    def 'calls the runner with the correct ScriptEngine'() {
        given:
        ScriptEngineFactory correctFactory = Mock()
        ScriptEngine correctEngine = Mock()
        correctFactory.languageName >> 'lang'
        correctFactory.scriptEngine >> correctEngine

        ScriptEngineFactory wrongFactory = Mock()
        wrongFactory.languageName >> 'wrongLang'

        manager.engineFactories >> [correctFactory, wrongFactory]

        final execution = new Execution(new Script('lang', 'src'), new Context([], Goal.RESULT, Mock(Editor)))

        when:
        final res = exec.execute(execution)

        then:
        1 * runner.apply(execution, correctEngine) >> 'asdf'
        res == 'asdf'
    }
}
