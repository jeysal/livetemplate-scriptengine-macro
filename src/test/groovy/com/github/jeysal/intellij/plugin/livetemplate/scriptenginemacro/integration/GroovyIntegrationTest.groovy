package com.github.jeysal.intellij.plugin.livetemplate.scriptenginemacro.integration

import com.github.jeysal.intellij.plugin.livetemplate.scriptenginemacro.ScriptEngineMacro
import com.intellij.codeInsight.template.Expression
import com.intellij.codeInsight.template.ExpressionContext
import com.intellij.codeInsight.template.TextResult
import spock.lang.Specification

import javax.script.ScriptEngineManager

import static org.junit.Assume.assumeNotNull

/**
 * @author seckinger
 * @since 11/29/16
 */
class GroovyIntegrationTest extends Specification {
    final macro = new ScriptEngineMacro()
    final Expression scriptParam = Mock()
    final ExpressionContext ctx = Mock()

    def setupSpec() {
        assumeNotNull new ScriptEngineManager().getEngineByName('groovy')
    }

    private src(String src) {
        scriptParam.calculateResult(ctx) >> new TextResult("groovy:$src")
    }

    private expr(String text) {
        Mock(Expression) {
            calculateResult(ctx) >> new TextResult(text)
        }
    }

    def 'script returns a string'() {
        given:
        src($/"asdf"/$)

        when:
        TextResult res = macro.calculateResult([scriptParam] as Expression[], ctx)

        then:
        res.text == 'asdf'

        when:
        List elems = macro.calculateLookupItems([scriptParam] as Expression[], ctx).collect { it.lookupString }

        then:
        elems == ['asdf']
    }

    def 'script returns some args'() {
        given:
        src($/_args.tail()/$)

        when:
        TextResult res = macro.calculateResult([scriptParam, expr('a'), expr('b'), expr('c')] as Expression[], ctx)

        then:
        res.text == 'b'

        when:
        List elems = macro.calculateLookupItems([scriptParam, expr('a'), expr('b'), expr('c')] as Expression[], ctx)
                .collect { it.lookupString }

        then:
        elems == ['b', 'c']
    }
}
