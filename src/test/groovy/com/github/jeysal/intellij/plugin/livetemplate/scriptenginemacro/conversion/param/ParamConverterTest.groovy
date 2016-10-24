package com.github.jeysal.intellij.plugin.livetemplate.scriptenginemacro.conversion.param

import com.intellij.codeInsight.template.InvokeActionResult
import com.intellij.codeInsight.template.Result
import spock.lang.Specification

/**
 * @author seckinger
 * @since 10/24/16
 */
class ParamConverterTest extends Specification {
    def conv = new ParamConverter()

    def 'unwraps an InvokeActionsResult'() {
        given:
        Runnable noop = { -> }

        expect:
        conv.call(new InvokeActionResult(noop)) == noop
    }

    def 'toStrings any Result'() {
        given:
        def res = Mock(Result)

        expect:
        conv.call(res) == res.toString()
    }

    def 'toStrings null'() {
        expect:
        conv.call(null) == 'null'
    }
}
