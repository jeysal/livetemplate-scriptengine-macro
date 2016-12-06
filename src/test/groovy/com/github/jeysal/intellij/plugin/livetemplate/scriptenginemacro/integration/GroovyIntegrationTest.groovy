package com.github.jeysal.intellij.plugin.livetemplate.scriptenginemacro.integration

import com.github.jeysal.intellij.plugin.livetemplate.scriptenginemacro.ScriptEngineMacro
import com.intellij.codeInsight.template.Expression
import com.intellij.codeInsight.template.ExpressionContext
import com.intellij.codeInsight.template.ListResult
import com.intellij.codeInsight.template.TextResult
import org.junit.Rule
import org.junit.rules.TemporaryFolder
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

    @Rule
    TemporaryFolder tmp = new TemporaryFolder()

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

    def 'script return depends on goal'() {
        given:
        src($/_goal == 'RESULT' ? 1 : 2/$)

        when:
        TextResult res = macro.calculateResult([scriptParam] as Expression[], ctx)

        then:
        res.text == '1'

        when:
        List elems = macro.calculateLookupItems([scriptParam] as Expression[], ctx).collect { it.lookupString }

        then:
        elems == ['2']
    }

    def 'script uses list arg'() {
        given:
        src($/_args[0][1]/$)

        final arg = Mock(Expression) {
            calculateResult(ctx) >> new ListResult([new TextResult('abc'), new TextResult('xyz')])
        }

        when:
        TextResult res = macro.calculateResult([scriptParam, arg] as Expression[], ctx)

        then:
        res.text == 'xyz'

        when:
        List elems = macro.calculateLookupItems([scriptParam, arg] as Expression[], ctx).collect { it.lookupString }

        then:
        elems == ['xyz']
    }

    def 'script returns _out'() {
        given:
        src($/println 'abc'
print 'xyz'
_out/$)

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

    def 'script from file'() {
        setup:
        final file = tmp.newFile('test.groovy')
        file.text = $/21 * 2/$

        scriptParam.calculateResult(ctx) >> new TextResult(file.absolutePath)

        when:
        TextResult res = macro.calculateResult([scriptParam] as Expression[], ctx)

        then:
        res.text == '42'

        when:
        List elems = macro.calculateLookupItems([scriptParam] as Expression[], ctx).collect { it.lookupString }

        then:
        elems == ['42']
    }

    def 'script from prefix and file'() {
        setup:
        final file = tmp.newFile('test')
        file.text = $/21 * 2/$

        scriptParam.calculateResult(ctx) >> new TextResult("groovy:$file.absolutePath")

        when:
        TextResult res = macro.calculateResult([scriptParam] as Expression[], ctx)

        then:
        res.text == '42'

        when:
        List elems = macro.calculateLookupItems([scriptParam] as Expression[], ctx).collect { it.lookupString }

        then:
        elems == ['42']
    }
}
