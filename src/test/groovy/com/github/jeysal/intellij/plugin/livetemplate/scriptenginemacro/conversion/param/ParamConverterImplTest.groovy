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
class ParamConverterImplTest extends Specification {
    final conv = new ParamConverterImpl()

    def 'converts each element of a ListResult into a List'() {
        given:
        final Runnable noop = { -> }
        final res = Mock(Result)

        expect:
        conv.convert(new ListResult([
                new TextResult('asdf'),
                new InvokeActionResult(noop),
                res
        ])) == ['asdf', noop, res.toString()]
    }

    def 'converts an empty ListResult to an empty List'() {
        expect:
        conv.convert(new ListResult([])) == []
    }

    def 'unwraps a TextResult'() {
        expect:
        conv.convert(new TextResult('asdf')) == 'asdf'
    }

    def 'unwraps an InvokeActionsResult'() {
        given:
        final Runnable noop = { -> }

        expect:
        conv.convert(new InvokeActionResult(noop)) == noop
    }

    def 'toStrings any Result'() {
        given:
        final res = Mock(Result)

        expect:
        conv.convert(res) == res.toString()
    }

    def 'toStrings null'() {
        expect:
        conv.convert(null) == 'null'
    }
}
