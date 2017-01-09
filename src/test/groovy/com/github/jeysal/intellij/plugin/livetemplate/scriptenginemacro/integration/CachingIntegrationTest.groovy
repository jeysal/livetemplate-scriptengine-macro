package com.github.jeysal.intellij.plugin.livetemplate.scriptenginemacro.integration

import com.github.jeysal.intellij.plugin.livetemplate.scriptenginemacro.ScriptEngineMacro
import com.intellij.codeInsight.template.Expression
import com.intellij.codeInsight.template.ExpressionContext
import com.intellij.codeInsight.template.TextResult
import com.intellij.openapi.editor.Editor
import spock.lang.Specification

import javax.script.ScriptEngineManager

import static org.junit.Assume.assumeNotNull

/**
 * @author seckinger
 * @since 1/9/17
 */
class CachingIntegrationTest extends Specification {
    final macro = new ScriptEngineMacro()
    final ExpressionContext ctx = Mock()

    def setupSpec() {
        assumeNotNull new ScriptEngineManager().getEngineByName('groovy')
    }

    private expr(String text) {
        Mock(Expression) {
            calculateResult(ctx) >> new TextResult(text)
        }
    }

    def 'auto-loads cached value for same execution'() {
        given:
        final script = expr 'groovy:_cache.store(System.nanoTime())'

        expect:
        // This line tho...
        (macro.calculateResult([script] as Expression[], ctx) as TextResult).text ==
                (macro.calculateResult([script] as Expression[], ctx) as TextResult).text
    }

    def 'can cache null'() {
        given:
        final script = expr 'groovy:_cache.store(null)'

        expect:
        (macro.calculateResult([script] as Expression[], ctx) as TextResult).text == 'null'
    }

    def 'does not auto-load for a different script'() {
        given:
        final script1 = expr 'groovy:_cache.store(1)'
        final script2 = expr 'groovy:_cache.store(2)'

        expect:
        (macro.calculateResult([script1] as Expression[], ctx) as TextResult).text == '1'
        (macro.calculateResult([script2] as Expression[], ctx) as TextResult).text == '2'
    }

    def 'does not auto-load for different args'() {
        given:
        final script = expr 'groovy:_cache.store(_args[0])'

        final arg1 = expr '1'
        final arg2 = expr '2'

        expect:
        (macro.calculateResult([script, arg1] as Expression[], ctx) as TextResult).text == '1'
        (macro.calculateResult([script, arg2] as Expression[], ctx) as TextResult).text == '2'
    }

    def 'does not auto-load for different goal'() {
        given:
        final script = expr 'groovy:_cache.store(_goal)'

        expect:
        (macro.calculateResult([script] as Expression[], ctx) as TextResult).text == 'RESULT'

        macro.calculateLookupItems([script] as Expression[], ctx).collect {
            it.lookupString
        } as List == ['LOOKUP_ELEMENTS']
    }

    def 'does not auto-load for different editor'() {
        given:
        final script = expr 'groovy:_cache.store(_editor)'

        when:
        final res1 = (macro.calculateResult([script] as Expression[], ctx) as TextResult).text

        then:
        ctx.editor >> Mock(name: '1', type: Editor)
        res1 == "Mock for type 'Editor' named '1'"

        when:
        final res2 = (macro.calculateResult([script] as Expression[], ctx) as TextResult).text

        then:
        ctx.editor >> Mock(name: '2', type: Editor)
        res2 == "Mock for type 'Editor' named '2'"
    }

    def 'allows storing and retrieving metadata-afflicted cache entries'() {
        given:
        final script = expr "groovy:_cache.store(_cache.load('meta').orElse(1), 'meta')"

        expect:
        (macro.calculateResult([script] as Expression[], ctx) as TextResult).text == '1'
        (macro.calculateResult([script] as Expression[], ctx) as TextResult).text == '1'
    }

    def 'does not auto-load metadata-afflicted cache entries'() {
        given:
        final script = expr "groovy:_cache.store(_cache.load('meta').orElse(0) + 1, 'meta')"

        expect:
        (macro.calculateResult([script] as Expression[], ctx) as TextResult).text == '1'
        (macro.calculateResult([script] as Expression[], ctx) as TextResult).text == '2' // would be 1 if auto-loaded
    }

    def 'stores metadata-afflicted cache entries per script'() {
        given:
        final script1 = expr "groovy:_cache.store(1, 'meta')"
        final script2 = expr "groovy:_cache.load('meta').orElse(2)"

        expect:
        (macro.calculateResult([script1] as Expression[], ctx) as TextResult).text == '1'
        (macro.calculateResult([script2] as Expression[], ctx) as TextResult).text == '2'
    }

    def 'stores metadata-afflicted cache entries independent of args'() {
        given:
        final script = expr "groovy:_cache.store(_cache.load('meta').orElse(_args[0]), 'meta')"

        final arg1 = expr "1"
        final arg2 = expr "2"

        expect:
        (macro.calculateResult([script, arg1] as Expression[], ctx) as TextResult).text == '1'

        // would be 2 if cache entry was unavailable
        (macro.calculateResult([script, arg2] as Expression[], ctx) as TextResult).text == '1'
    }

    def 'stores metadata-afflicted cache entries independent of goal'() {
        given:
        final script = expr "groovy:_cache.store(_cache.load('meta').orElse(_goal), 'meta')"

        expect:
        (macro.calculateResult([script] as Expression[], ctx) as TextResult).text == 'RESULT'

        // would be 'LOOKUP_ELEMENTS' if cache entry was unavailable
        macro.calculateLookupItems([script] as Expression[], ctx).collect { it.lookupString } as List == ['RESULT']
    }

    def 'stores metadata-afflicted cache entries independent of editor'() {
        given:
        final script = expr "groovy:_cache.store(_cache.load('meta').orElse(_editor), 'meta')"

        when:
        final res1 = (macro.calculateResult([script] as Expression[], ctx) as TextResult).text

        then:
        ctx.editor >> Mock(name: '1', type: Editor)
        res1 == "Mock for type 'Editor' named '1'"

        when:
        final res2 = (macro.calculateResult([script] as Expression[], ctx) as TextResult).text

        then:
        ctx.editor >> Mock(name: '2', type: Editor)

        // would be 2 if cache entry was unavailable
        res2 == "Mock for type 'Editor' named '1'"
    }
}
