package com.github.jeysal.intellij.plugin.livetemplate.scriptenginemacro.execution

import spock.lang.Specification

import javax.script.ScriptContext
import javax.script.ScriptEngine
import javax.script.ScriptEngineFactory
import javax.script.ScriptEngineManager
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
}
