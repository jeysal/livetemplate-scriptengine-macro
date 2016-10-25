package com.github.jeysal.intellij.plugin.livetemplate.scriptenginemacro.conversion.param

import com.intellij.codeInsight.template.InvokeActionResult
import com.intellij.codeInsight.template.ListResult
import com.intellij.codeInsight.template.Result
import com.intellij.codeInsight.template.TextResult
import spock.lang.Specification

/**
 * @author seckinger
 * @since 10/24/16
 */
class ParamConverterTest extends Specification {
    def conv = new ParamConverter()

    def 'converts each element of a ListResult into a List'() {
        given:
        Runnable noop = { -> }
        def res = Mock(Result)

        expect:
        conv.apply(new ListResult([
                new TextResult('asdf'),
                new InvokeActionResult(noop),
                res
        ])) == ['asdf', noop, res.toString()]
    }

    def 'converts an empty ListResult to an empty List'() {
        expect:
        conv.apply(new ListResult([])) == []
    }

    def 'unwraps a TextResult'() {
        expect:
        conv.apply(new TextResult('asdf')) == 'asdf'
    }

    def 'unwraps an InvokeActionsResult'() {
        given:
        Runnable noop = { -> }

        expect:
        conv.apply(new InvokeActionResult(noop)) == noop
    }

    def 'toStrings any Result'() {
        given:
        def res = Mock(Result)

        expect:
        conv.apply(res) == res.toString()
    }

    def 'toStrings null'() {
        expect:
        conv.apply(null) == 'null'
    }
}
