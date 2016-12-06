package com.github.jeysal.intellij.plugin.livetemplate.scriptenginemacro.integration

import com.github.jeysal.intellij.plugin.livetemplate.scriptenginemacro.ScriptEngineMacro
import com.intellij.codeInsight.template.Expression
import com.intellij.codeInsight.template.ExpressionContext
import com.intellij.codeInsight.template.TextResult
import org.junit.Rule
import org.junit.rules.TemporaryFolder
import spock.lang.Specification

import javax.script.ScriptEngineManager

import static org.junit.Assume.assumeNotNull

/**
 * @author seckinger
 * @since 12/6/16
 */
class JavascriptIntegrationTest extends Specification {
    final macro = new ScriptEngineMacro()
    final Expression scriptParam = Mock()
    final ExpressionContext ctx = Mock()

    @Rule
    TemporaryFolder tmp = new TemporaryFolder()

    def setupSpec() {
        assumeNotNull new ScriptEngineManager().getEngineByName('javascript')
    }

    private src(String src) {
        scriptParam.calculateResult(ctx) >> new TextResult("javascript:$src")
    }

    private expr(String text) {
        Mock(Expression) {
            calculateResult(ctx) >> new TextResult(text)
        }
    }

    def 'script returns a string'() {
        given:
        src($/(function() { return 'asdf'; })()/$)

        when:
        TextResult res = macro.calculateResult([scriptParam] as Expression[], ctx)

        then:
        res.text == 'asdf'

        when:
        List elems = macro.calculateLookupItems([scriptParam] as Expression[], ctx).collect { it.lookupString }

        then:
        elems == ['asdf']
    }

    def 'script omits an iife'() {
        given:
        src($/'asdf'/$)

        when:
        TextResult res = macro.calculateResult([scriptParam] as Expression[], ctx)

        then:
        res.text == 'asdf'

        when:
        List elems = macro.calculateLookupItems([scriptParam] as Expression[], ctx).collect { it.lookupString }

        then:
        elems == ['asdf']
    }

    def 'script uses native js array'() {
        given:
        src($/(function() {
var arr = Java.from(_args);
arr.reverse();
return arr;
})()/$)

        when:
        TextResult res = macro.calculateResult([scriptParam, expr('a'), expr('b'), expr('c')] as Expression[], ctx)

        then:
        res.text == 'c'

        when:
        List elems = macro.calculateLookupItems([scriptParam, expr('a'), expr('b'), expr('c')] as Expression[], ctx)
                .collect { it.lookupString }

        then:
        elems == ['c', 'b', 'a']
    }

    def 'script return depends on goal'() {
        given:
        src($/(function() {
return _goal === 'RESULT' ? 1 : 2;
})()/$)

        when:
        TextResult res = macro.calculateResult([scriptParam] as Expression[], ctx)

        then:
        res.text == '1'

        when:
        List elems = macro.calculateLookupItems([scriptParam] as Expression[], ctx).collect { it.lookupString }

        then:
        elems == ['2']
    }

    def 'script returns _out'() {
        given:
        src($/(function() {
_out.write('abc\nxyz');
return _out;
})()/$)

        when:
        TextResult res = macro.calculateResult([scriptParam] as Expression[], ctx)

        then:
        res.text == '''abc
xyz'''

        when:
        List elems = macro.calculateLookupItems([scriptParam] as Expression[], ctx).collect { it.lookupString }

        then:
        elems == ['''abc
xyz''']
    }
}
